package com.jaychis.memo.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.ResponseEntity
import com.jaychis.memo.model.Memo
import com.jaychis.memo.service.MemoService
import com.jaychis.memo.dto.MemoRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.Parameter
import java.util.UUID
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("/memos")
class MemoController(private val memoService: MemoService) {

    @Operation(
        summary = "메모 목록 조회",
        description = "모든 메모 목록을 반환합니다."
    )
    @ApiResponse(responseCode = "200", description = "요청 성공")
    @GetMapping
    fun getAllMemos(): ResponseEntity<List<Memo>> =
        ResponseEntity.ok(memoService.getAllMemos())

    @Operation(
        summary = "메모 내용 조회",
        description = "draftId에 해당하는 메모의 내용을 반환하고, 메모가 없으면 빈 문자열을 반환합니다."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "요청 성공 (메모가 없으면 빈 문자열)")
        ]
    )
    @GetMapping("/{draftId}")
    fun getContentByDraftId(
        @Parameter(description = "조회할 메모의 draftId", required = true)
        @PathVariable draftId: UUID
    ): ResponseEntity<String> {
        val content: String = memoService.getByDraftId(draftId)
        return ResponseEntity.ok(content)
    }

    @Operation(
        summary = "메모 생성/수정",
        description = "draftId 기준으로 메모가 있으면 업데이트하고, 없으면 새로 생성합니다."
    )
    @ApiResponse(responseCode = "200", description = "메모 생성 또는 업데이트 성공")
    @PostMapping
    fun upsert(
        @Parameter(description = "메모 생성/수정 요청 데이터", required = true)
        @RequestBody request: MemoRequest
    ): ResponseEntity<Memo> =
        ResponseEntity.ok(memoService.upsert(request))
}

package com.jaychis.memo.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.http.ResponseEntity
import com.jaychis.memo.model.Memo
import com.jaychis.memo.service.MemoService
import com.jaychis.memo.dto.MemoRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses

@RestController
@RequestMapping("/memos")
@Tag(name = "Memo API", description = "메모 관리를 위한 API")
class MemoController(private val memoService: MemoService) {

    @Operation(summary = "메모 생성", description = "새로운 메모를 생성합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "메모 생성 성공"),
            ApiResponse(responseCode = "400", description = "잘못된 요청")
        ]
    )
    @PostMapping
    fun create(@RequestBody request: MemoRequest): ResponseEntity<Memo> {
        val saved = memoService.createMemo(request)
        return ResponseEntity.ok(saved)
    }

    @Operation(summary = "메모 목록 조회", description = "모든 메모 목록을 조회합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "메모 목록 조회 성공")
        ]
    )
    @GetMapping
    fun getAll(): List<Memo> = memoService.getAllMemos()
}

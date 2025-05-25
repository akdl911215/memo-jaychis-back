package com.jaychis.memo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.http.MediaType
import com.fasterxml.jackson.databind.ObjectMapper
import com.jaychis.memo.dto.MemoRequest
import java.util.UUID
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MemoApiTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `메모 생성 테스트`() {
        val id = UUID.randomUUID()
        val request = MemoRequest(
            draftId = id,
            content = "테스트 메모"
        )
        mockMvc.perform(
            post("/memos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isOk)
    }

    @Test
    fun `메모 목록 조회 테스트`() {
        mockMvc.perform(get("/memos"))
            .andExpect(status().isOk)
    }
}

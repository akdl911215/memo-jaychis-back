package com.jaychis.memo

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@OpenAPIDefinition(
    info = Info(
        title = "Memo API",
        version = "v1",
        description = "메모장 백엔드 API 문서"
    )
)

@SpringBootApplication
class MemoApplication

fun main(args: Array<String>) {
    runApplication<MemoApplication>(*args)
}
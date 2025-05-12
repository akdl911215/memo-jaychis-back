package com.jaychis.memo.dto

import java.util.UUID

data class MemoRequest(
    val draftId: UUID,
    val content: String
)

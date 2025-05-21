package com.jaychis.memo.service

import com.jaychis.memo.model.Memo
import com.jaychis.memo.dto.MemoRequest
import java.util.UUID
import java.util.List

interface MemoService {
    fun getByDraftId(draftId: UUID): Memo?
    fun upsert(request: MemoRequest): Memo
    fun getAllMemos(): List<Memo>
}

package com.jaychis.memo.service

import com.jaychis.memo.model.Memo
import com.jaychis.memo.dto.MemoRequest

interface MemoService {
    fun createMemo(request: MemoRequest): Memo
    fun getAllMemos(): List<Memo>
}

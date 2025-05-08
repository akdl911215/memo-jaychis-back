package com.jaychis.memo.service

import org.springframework.stereotype.Service
import com.jaychis.memo.model.Memo
import com.jaychis.memo.repository.MemoRepository
import com.jaychis.memo.dto.MemoRequest

@Service
class MemoServiceImpl(private val memoRepository: MemoRepository) : MemoService {
    override fun createMemo(request: MemoRequest): Memo {
        return memoRepository.save(Memo(content = request.content))
    }

    override fun getAllMemos(): List<Memo> = memoRepository.findAll()
} 
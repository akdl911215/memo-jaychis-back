package com.jaychis.memo.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.jaychis.memo.model.Memo
import com.jaychis.memo.repository.MemoRepository
import com.jaychis.memo.dto.MemoRequest
import java.util.UUID
import java.time.LocalDateTime

@Service
class MemoServiceImpl(private val memoRepository: MemoRepository) : MemoService {
    override fun getByDraftId(draftId: UUID): Memo? =
     memoRepository.findByDraftId(draftId)
    
    @Transactional
    override fun upsert(request: MemoRequest): Memo {
        
        val existing: Memo? = memoRepository.findByDraftId(request.draftId)
        return if (existing != null) {

            if (existing.content != request.content) {
                existing.content = request.content
                existing.updatedAt = LocalDateTime.now()
                memoRepository.save(existing)
            } else {
                existing
            }
        } else {
            val newMemo = Memo(
                draftId = request.draftId,
                content = request.content
            )
            memoRepository.save(newMemo)
        }
        
    }
    
    override fun getAllMemos(): List<Memo> = memoRepository.findAll()
}
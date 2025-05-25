package com.jaychis.memo.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.jaychis.memo.model.Memo
import com.jaychis.memo.repository.MemoRepository
import com.jaychis.memo.dto.MemoRequest
import java.util.UUID
import kotlin.collections.List
import java.time.LocalDateTime

@Service
class MemoServiceImpl(private val memoRepository: MemoRepository) : MemoService {
    override fun getByDraftId(draftId: UUID): String {
        println("=== Start getByDraftId in Service ===")
        try {
            println("draftId: $draftId")
            
            val memo = memoRepository.findByDraftId(draftId)
            println("Found memo: $memo")
            
            val result = memo?.content ?: ""
            println("Returning content: $result")
            
            println("=== End getByDraftId in Service ===")
            return result
        } catch (e: Exception) {
            println("=== Error in getByDraftId Service ===")
            println("Error type: ${e.javaClass.name}")
            println("Error message: ${e.message}")
            e.printStackTrace()
            println("=== End Error ===")
            throw e  // 예외를 다시 던져서 컨트롤러에서 처리하도록 함
        }
    }
        
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
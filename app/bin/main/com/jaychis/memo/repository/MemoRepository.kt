package com.jaychis.memo.repository

import com.jaychis.memo.model.Memo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MemoRepository : JpaRepository<Memo, UUID> {
  fun findByDraftId(draftId: UUID): Memo?
}

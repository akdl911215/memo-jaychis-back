@Service
class MemoService(private val memoRepository: MemoRepository) {

    fun createMemo(request: MemoRequest): Memo {
        return memoRepository.save(Memo(content = request.content))
    }

    fun getAllMemos(): List<Memo> = memoRepository.findAll()
}

@RestController
@RequestMapping("/memos")
class MemoController(private val memoService: MemoService) {

    @PostMapping
    fun create(@RequestBody request: MemoRequest): ResponseEntity<Memo> {
        val saved = memoService.createMemo(request)
        return ResponseEntity.ok(saved)
    }

    @GetMapping
    fun getAll(): List<Memo> = memoService.getAllMemos()
}

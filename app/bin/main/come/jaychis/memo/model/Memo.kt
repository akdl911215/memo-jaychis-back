@Entity
@Table(name = "memos")
data class Memo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val content: String,

    val createdAt: LocalDateTime = LocalDateTime.now()
)

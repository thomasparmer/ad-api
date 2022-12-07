package fullstack.api.ad

data class PaginatedAdData(
    val content: List<Ad>,
    val totalElements: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val totalPages: Int,
    val first: Boolean,
    val last: Boolean,
    val sort: String
)
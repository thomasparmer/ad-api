package fullstack.api.ad

data class Ad(
    val applicationDue: String?,
    val applicationUrl: Any?,
    val description: String,
    val employer: Employer?,
    val engagementtype: String?,
    val expires: String?,
    val extent: String?,
    val jobtitle: String?,
    val link: String?,
    val occupationCategories: List<OccupationCategory>?,
    val positioncount: String?,
    val published: String,
    val sector: String?,
    val source: String?,
    val sourceurl: Any?,
    val starttime: String?,
    val title: String?,
    val updated: String?,
    val uuid: String?,
    val workLocations: List<WorkLocation>?
)

data class Employer(
    val description: String?,
    val homepage: String?,
    val name: String?,
    val orgnr: String?
)

data class OccupationCategory(
    val level1: String?,
    val level2: String?
)

data class WorkLocation(
    val address: String?,
    val city: String?,
    val country: String?,
    val county: String?,
    val municipal: String?,
    val postalCode: String?
)
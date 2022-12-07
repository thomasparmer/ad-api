package fullstack.api.ad

import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Service
class AdService(private val adConsumer: AdConsumer) {

    fun getPublishedJavaKotlinAdsInLastSixMonths(): List<Ad> {
        val ads = mutableListOf<Ad>()
        var startDate: LocalDateTime = LocalDateTime.now()
        val sixMonthsAgo: LocalDateTime = startDate.minusMonths(6)

        while (startDate.isAfter(sixMonthsAgo)) {
            val publishedFilter = getPublishedFilter(sixMonthsAgo, startDate)
            val paginatedAdData = adConsumer.getAds(publishedFilter)
            if (paginatedAdData.content.isEmpty()) {
                break
            }

            val javaAndKotlinAds = filterForJavaAndKotlinAds(paginatedAdData.content)
            ads.addAll(javaAndKotlinAds)
            startDate = ZonedDateTime.parse(paginatedAdData.content.last().published).toLocalDateTime()
        }

        return ads
    }

    fun getPublishedFilter(earliest: LocalDateTime, latest: LocalDateTime): String {
        val from = earliest.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val to = latest.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        return "[$from,$to)"
    }

    fun filterForJavaAndKotlinAds(ads: List<Ad>): List<Ad> {
        return ads.filter {
            it.description.contains("java", ignoreCase = true) ||
            it.description.contains("kotlin", ignoreCase = true)
        }
    }
}
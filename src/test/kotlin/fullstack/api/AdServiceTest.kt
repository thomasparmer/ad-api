package fullstack.api

import fullstack.api.ad.Ad
import fullstack.api.ad.AdConsumer
import fullstack.api.ad.AdService
import fullstack.api.ad.PaginatedAdData
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import java.time.LocalDateTime

class AdServiceTest {
    private val adConsumer: AdConsumer = mock()
    private val adService: AdService = AdService(adConsumer)

    @Test
    fun `getPublishedAdsInLastSixMonths returns published ads in the last six months`() {
        // given a Mock of adConsumer that return a list of 5 ads when getAds is called the first time, then an empyt ad list.
        val ads = getTestAds()
        `when`(adConsumer.getAds(any()))
            .thenReturn(PaginatedAdData(ads, 5, 1, 5, 1, true, true, "desc"))
            .thenReturn(PaginatedAdData(listOf(), 0, 1, 0, 1, true, true, "desc"))

        // when
        val javaKotlinAds = adService.getPublishedJavaKotlinAdsInLastSixMonths()

        // then assert that the call to getPublishedAdsInLastSixMonths returned 3 ads.
        assertEquals(3, javaKotlinAds.size)
    }

    @Test
    fun `getPublishedFilter should return date filter corresponding to input dates`() {
        // given
        val earliest = LocalDateTime.of(2022, 10, 1, 12, 0, 0)
        val latest = LocalDateTime.of(2022, 10, 31, 12, 0, 0)
        val expected = "[2022-10-01T12:00:00,2022-10-31T12:00:00)"

        // when
        val result = adService.getPublishedFilter(earliest, latest)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `filterForJavaAndKotlinAds should filter out ads not containing Java and Kotlin`() {
        // given
        val ads = getTestAds()

        // when
        val filteredAds = adService.filterForJavaAndKotlinAds(ads)

        // then
        assertEquals(3, filteredAds.size)
    }

    private fun getTestAds(): List<Ad> {
        val ad1 = getTestAd("Java vs. Kotlin: Which is the best language? Work with us and find out!")
        val ad2 = getTestAd("Work with UPPERCASED JAVA today!")
        val ad3 = getTestAd("Master lowercased kotlin programmer wanted")
        val ad4 = getTestAd("Developer job")
        val ad5 = getTestAd("")
        return listOf(ad1, ad2, ad3, ad4, ad5)
    }

    private fun getTestAd(description: String): Ad {
        return Ad(null, null, description,null,null,null, null, null, null, null, null, "2022-10-01T12:00:00Z", null, null, null, null, null, null, null, null)
    }
}

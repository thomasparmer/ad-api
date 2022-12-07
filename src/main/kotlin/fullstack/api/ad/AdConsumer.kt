package fullstack.api.ad

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class AdConsumer(private val webClient: WebClient) {

    fun getAds(published: String): PaginatedAdData {

        val paginatedData = webClient.get()
            .uri {
                it.path("/public-feed/api/v1/ads")
                    .queryParam("size", "100")
                    .queryParam("published", published)
                .build()
            }
            .retrieve()
            .bodyToMono(PaginatedAdData::class.java)
            .block()

        return paginatedData!!
    }

}
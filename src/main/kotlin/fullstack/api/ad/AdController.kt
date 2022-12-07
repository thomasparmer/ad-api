package fullstack.api.ad

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AdController(private val adService: AdService) {
    @GetMapping("/ads")
    fun getKotlinAndJavaAds(): ResponseEntity<List<Ad>> {
        return ResponseEntity.ok().body(adService.getPublishedJavaKotlinAdsInLastSixMonths())
    }
}
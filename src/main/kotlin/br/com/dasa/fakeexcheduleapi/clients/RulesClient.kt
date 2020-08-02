package br.com.dasa.fakeexcheduleapi.clients

import br.com.dasa.fakeexcheduleapi.models.Rule
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    name = "rules-service",
    url = "\${microservices.rules-api.url}",
    configuration = [RulesClientConfig::class]
)
interface RulesClient {

    @GetMapping("/api/rules/{id}")
    fun getRules(@PathVariable("id") id: Int): Rule
}
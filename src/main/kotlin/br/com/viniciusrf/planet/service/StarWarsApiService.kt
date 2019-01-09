package br.com.viniciusrf.planet.service

import br.com.viniciusrf.planet.model.ResultVO
import br.com.viniciusrf.planet.model.SWApiDto
import org.springframework.web.client.RestTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import java.util.Arrays.asList
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType.*


@Service
class StarWarsApiService{

    @Value("\${services.swapi.url}")
    lateinit var SWAPI_URL: String

    @Value("\${services.swapi.operation.planets}")
    lateinit var PLANETS_RESOURCE: String

    @Autowired
    lateinit var templateBuilder: RestTemplateBuilder

    lateinit var restTemplate: RestTemplate

    lateinit var entity: HttpEntity<String>

    val userAgent: String = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36"

    init{

        val headers = HttpHeaders()
        headers.contentType = APPLICATION_JSON
        headers.add("user-agent", userAgent)
        entity = HttpEntity("parameters", headers)

    }

    fun getFilms(planetName: String): List<String> {

        restTemplate = templateBuilder.rootUri(SWAPI_URL).build()

        val response = restTemplate.exchange(PLANETS_RESOURCE, HttpMethod.GET, entity, ResultVO::class.java, planetName)

        val list : List<SWApiDto> = response.body?.results ?: asList()

        return if (!list.isEmpty())
            list.first().films
        else {
            asList()
        }
    }
}
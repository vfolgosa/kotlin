package br.com.viniciusrf.planet.service

import org.springframework.web.client.RestTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Arrays.asList

private class SWApiDto (val name: String, val films: List<String>)
private class ResultVO (val results: List<SWApiDto>)

@Service
class StarWarsApiService(
        private val restTemplate: RestTemplate,
        @Value("\${services.swapi.url}") private val SWAPI_URL: String,
        @Value("\${services.swapi.operation.planets}") val PLANETS_RESOURCE: String
){
    fun getFilms(planetName: String): List<String> {

        val response = restTemplate.getForObject(SWAPI_URL + PLANETS_RESOURCE, ResultVO::class.java, planetName)

        val list : List<SWApiDto> = response?.results ?: asList()

        return if (!list.isEmpty())
            list.first().films
        else {
            asList()
        }
    }
}
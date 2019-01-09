package br.com.viniciusrf.planet.repository

import br.com.viniciusrf.planet.model.Planet
import org.springframework.data.repository.CrudRepository

interface PlanetRepository : CrudRepository<Planet, String> {
    fun findAllByName(name:String): List<Planet>
}
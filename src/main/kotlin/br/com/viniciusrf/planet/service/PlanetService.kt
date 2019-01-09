package br.com.viniciusrf.planet.service

import br.com.viniciusrf.planet.exception.PlanetNotFoundException
import br.com.viniciusrf.planet.exception.PlanetSaveException
import br.com.viniciusrf.planet.model.Planet
import br.com.viniciusrf.planet.model.PlanetDto
import br.com.viniciusrf.planet.repository.PlanetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlanetService {



    @Autowired
    lateinit var planetRepository: PlanetRepository

    @Autowired
    lateinit var starWarsApiService: StarWarsApiService

    fun list(): List<PlanetDto> {

        val planets: MutableList<PlanetDto> = mutableListOf()

        planetRepository.findAll().forEach {
            planets.add(it.toDto())
        }

        return planets
    }

    fun add(planetDto: PlanetDto): PlanetDto {

        try{

            val films: List<String> = starWarsApiService.getFilms(planetDto.name)

            if(!films.isEmpty()) planetDto.films = films

            val planetSaved = planetRepository.save(Planet.fromDto(planetDto))
            return planetSaved.toDto()
        }catch(e:Exception){
            throw PlanetSaveException(e.message!!)
        }

    }

    fun delete(planetId: String) {
        val planet: Optional<Planet> = planetRepository.findById(planetId)

        if (!planet.isPresent) {
            throw PlanetNotFoundException("Planet with Id [$planetId] not found.")
        }

        planetRepository.delete(planet.get())
    }

    fun search(name: String): List<PlanetDto> {
        val planets: MutableList<PlanetDto> = mutableListOf()

        planetRepository.findAllByName(name).forEach {
            planets.add(it.toDto())
        }

        return planets
    }

}
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
class PlanetService (
        val planetRepository: PlanetRepository,
        val starWarsApiService: StarWarsApiService
) {

    fun list() = planetRepository.findAll().map {it.toDto()}

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

    fun remove(planetId: String) {
        val planet: Optional<Planet> = planetRepository.findById(planetId)

        if (!planet.isPresent) {
            throw PlanetNotFoundException("Planet with Id [$planetId] not found.")
        }

        planetRepository.delete(planet.get())
    }

    fun search(name: String) = planetRepository.findAllByName(name).map {it.toDto()}

    fun findPlanetById(id: String): Optional<PlanetDto> = planetRepository.findById(id).map { it.toDto() }

}
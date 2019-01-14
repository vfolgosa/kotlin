package br.com.viniciusrf.planet.controller

import br.com.viniciusrf.planet.model.PlanetDto
import br.com.viniciusrf.planet.service.PlanetService
import io.swagger.annotations.Api
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("planets")
@Api(value = "Planets", description = "Rest API for Star Wars API", tags = arrayOf("Star Wars API"))
class PlanetController(
        val planetService: PlanetService
){

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun list() = ResponseEntity.ok(planetService.list())

    @PostMapping
    fun add(@Valid @RequestBody planetDto: PlanetDto) = ResponseEntity.ok(planetService.add(planetDto))

    @DeleteMapping("{planetId}")
    fun remove(@Valid @PathVariable(required = true) planetId: String): ResponseEntity<List<PlanetDto>> {
        planetService.remove(planetId)
        return ResponseEntity.ok().build()
    }

    @GetMapping(value = ["/search/{name}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun search(@Valid @PathVariable(required = true) name: String) = ResponseEntity.ok(planetService.search(name))

    @GetMapping("{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findPlanet(@PathVariable id: String): ResponseEntity<PlanetDto> {
        val optionalPlanet = planetService.findPlanetById(id)

        return if (optionalPlanet.isPresent) ResponseEntity.ok(optionalPlanet.get())
        else ResponseEntity.notFound().build()
    }

}
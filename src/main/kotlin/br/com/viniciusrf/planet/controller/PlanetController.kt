package br.com.viniciusrf.planet.controller

import br.com.viniciusrf.planet.model.PlanetDto
import br.com.viniciusrf.planet.service.PlanetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@ControllerAdvice
@RestController(value = "planets")
class PlanetController{

    @Autowired
    lateinit var planetService: PlanetService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun list(): ResponseEntity<List<PlanetDto>> {
        return ResponseEntity.ok(planetService.list())
    }

    @PostMapping
    fun add(@Valid @RequestBody(required = true) planetDto: PlanetDto): ResponseEntity<PlanetDto> {
        return ResponseEntity.ok(planetService.add(planetDto))
    }

    @DeleteMapping(value = ["/{planetId}"])
    fun delete(@Valid @PathVariable(required = true) planetId: String): ResponseEntity<List<PlanetDto>> {
        planetService.delete(planetId)
        return ResponseEntity.ok().build()
    }

    @GetMapping(value = ["/search/{name}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun search(@Valid @PathVariable(required = true) name: String): ResponseEntity<List<PlanetDto>> {
        return ResponseEntity.ok(planetService.search(name))
    }

}
package br.com.viniciusrf.planet.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class PlanetSaveException(message: String) : RuntimeException(message)
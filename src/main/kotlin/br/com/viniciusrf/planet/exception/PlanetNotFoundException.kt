package br.com.viniciusrf.planet.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(HttpStatus.NOT_FOUND)
class PlanetNotFoundException(message: String) : RuntimeException(message)


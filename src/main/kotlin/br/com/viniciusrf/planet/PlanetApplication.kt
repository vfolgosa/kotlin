package br.com.viniciusrf.planet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlanetApplication

fun main(args: Array<String>) {
	runApplication<PlanetApplication>(*args)
}


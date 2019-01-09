package br.com.viniciusrf.planet.model

import org.jetbrains.annotations.NotNull


class PlanetDto (

    @NotNull
    val name: String,
    val id: String?,
    val terrain: String?,
    val climate: String?,
    var films: List<String>?

)
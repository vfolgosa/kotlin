package br.com.viniciusrf.planet.model

import org.jetbrains.annotations.NotNull
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Id

@Document(collection = "planets")
data class Planet(

        @Id
        val _id: String? = null,
        @Indexed(unique = true)
        @NotNull
        val name: String,
        val terrain: String?,
        val climate: String?,
        val films: List<String>?

) {

    fun toDto(): PlanetDto = PlanetDto(
            id = this._id,
            name = this.name,
            terrain = this.terrain,
            climate = this.climate,
            films = this.films
    )

    companion object {
        open fun fromDto(planetDto: PlanetDto): Planet {
            return Planet(
                    name = planetDto.name,
                    terrain = planetDto.terrain,
                    climate = planetDto.climate,
                    films = planetDto.films
            )
        }
    }
}
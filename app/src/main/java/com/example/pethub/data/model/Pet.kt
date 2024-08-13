package com.example.pethub.data.model

data class Pet(
    val id: String,
    val name: String,
    val age: String,
    val gender: String,
    val location: Int,
    val image: String,
    val postTime: Int,
    val description: String,
    val color: String,
    val weight: String,
    val owner: Owner,
) {
    fun toPetEntity(): PetEntity {
        return PetEntity(
            id = this.id,
            name = this.name,
            age = this.age,
            gender = this.gender,
            location = this.location,
            image = this.image,
        )
    }
}

data class Owner(
    val id: String,
    val name: String,
    val role: String,
    val number: String,
    val image: Int,
)
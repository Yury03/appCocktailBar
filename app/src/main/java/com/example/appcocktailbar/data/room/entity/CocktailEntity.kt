package com.example.appcocktailbar.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CocktailEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String,
    val ingredients: List<String>,
    val recipe: String,
    val photoPath: String?,
)

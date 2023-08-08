package com.example.appcocktailbar.domain.models

data class CocktailModel(
    val id: Long,
    val name: String,
    val description: String,
    val ingredients: List<String>,
    val recipe: String,
    val photoPath: String?,
)

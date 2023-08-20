package com.example.appcocktailbar.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CocktailModel(
    val id: Long,
    val name: String,
    val description: String,
    val ingredients: List<String>,
    val recipe: String,
    val photoPath: String?,
) : Parcelable

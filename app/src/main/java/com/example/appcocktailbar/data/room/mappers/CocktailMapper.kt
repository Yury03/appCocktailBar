package com.example.appcocktailbar.data.room.mappers

import com.example.appcocktailbar.data.room.entity.CocktailEntity
import com.example.appcocktailbar.domain.models.CocktailModel

object CocktailMapper {
    fun entityToModel(entity: CocktailEntity) = CocktailModel(
        id = entity.id,
        name = entity.name,
        description = entity.description,
        ingredients = entity.ingredients,
        recipe = entity.recipe,
        photoPath = entity.photoPath,
    )

    fun modelToEntity(model: CocktailModel) = CocktailEntity(
        id = model.id,
        name = model.name,
        description = model.description,
        ingredients = model.ingredients,
        recipe = model.recipe,
        photoPath = model.photoPath,
    )
}

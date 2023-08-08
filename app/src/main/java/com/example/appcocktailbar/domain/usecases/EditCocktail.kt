package com.example.appcocktailbar.domain.usecases

import com.example.appcocktailbar.domain.Repository
import com.example.appcocktailbar.domain.models.CocktailModel

class EditCocktail(private val repository: Repository.AddCocktailFragmentContract) {
    fun invoke(cocktail: CocktailModel) = repository.editCocktail(cocktail)

}
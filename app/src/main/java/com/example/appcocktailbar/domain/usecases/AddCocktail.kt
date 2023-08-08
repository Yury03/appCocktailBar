package com.example.appcocktailbar.domain.usecases

import com.example.appcocktailbar.domain.Repository
import com.example.appcocktailbar.domain.models.CocktailModel

class AddCocktail(private val repository: Repository.AddCocktailFragmentContract) {
    fun invoke(cocktail: CocktailModel) = repository.addCocktail(cocktail)
}
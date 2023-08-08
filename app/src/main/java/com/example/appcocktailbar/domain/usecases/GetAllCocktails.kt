package com.example.appcocktailbar.domain.usecases

import com.example.appcocktailbar.domain.Repository
import com.example.appcocktailbar.domain.models.CocktailModel

class GetAllCocktails(private val repository: Repository.CocktailsFragmentContract) {
    fun invoke(): List<CocktailModel> = repository.getCocktailsList()
}
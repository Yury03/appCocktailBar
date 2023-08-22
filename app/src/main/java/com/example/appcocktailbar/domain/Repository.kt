package com.example.appcocktailbar.domain

import com.example.appcocktailbar.domain.models.CocktailModel

interface Repository {
    interface CocktailsFragmentContract {
        fun getCocktailsList(): List<CocktailModel>
        fun deleteCocktail(id: Long)
    }

    interface AddCocktailFragmentContract {
        fun addCocktail(cocktail: CocktailModel)
        fun editCocktail(cocktail: CocktailModel)
    }

    interface DetailsFragmentContract {
        fun deleteCocktail(id: Long)
    }
}
package com.example.appcocktailbar.domain.usecases

import com.example.appcocktailbar.domain.Repository


class DeleteCocktail(private val repository: Repository.CocktailsFragmentContract) {
    fun invoke(id: Long) = repository.deleteCocktail(id)

}
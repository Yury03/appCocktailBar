package com.example.appcocktailbar.di

import com.example.appcocktailbar.domain.usecases.AddCocktail
import com.example.appcocktailbar.domain.usecases.DeleteCocktail
import com.example.appcocktailbar.domain.usecases.EditCocktail
import com.example.appcocktailbar.domain.usecases.GetAllCocktails
import org.koin.dsl.module

val domainModule = module {
    factory<AddCocktail> {
        AddCocktail(repository = get())
    }
    factory<DeleteCocktail> {
        DeleteCocktail(repository = get())
    }
    factory<EditCocktail> {
        EditCocktail(repository = get())
    }
    factory<GetAllCocktails> {
        GetAllCocktails(repository = get())
    }

}
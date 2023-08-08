package com.example.appcocktailbar.di

import com.example.appcocktailbar.data.AddCocktailsContractImpl
import com.example.appcocktailbar.data.CocktailsFragmentContractImpl
import com.example.appcocktailbar.domain.Repository
import org.koin.dsl.module

val dataModule = module {
    single<Repository.CocktailsFragmentContract> {
        CocktailsFragmentContractImpl(context = get())
    }
    single<Repository.AddCocktailFragmentContract> {
        AddCocktailsContractImpl(context = get())
    }
}
package com.example.appcocktailbar.di

import com.example.appcocktailbar.ui.add_cocktail.AddCocktailViewModel
import com.example.appcocktailbar.ui.cocktail_details.CocktailDetailsViewModel
import com.example.appcocktailbar.ui.cocktails.CocktailsViewModel
import com.example.appcocktailbar.ui.main_activity.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel<MainViewModel> {
        MainViewModel()
    }
    viewModel<CocktailsViewModel> {
        CocktailsViewModel(
            deleteCocktail = get(),
            getAllCocktails = get(),
        )
    }
    viewModel<AddCocktailViewModel> {
        AddCocktailViewModel(
            addCocktail = get(),
            editCocktail = get(),
        )
    }
    viewModel<CocktailDetailsViewModel> {
        CocktailDetailsViewModel()
    }
}
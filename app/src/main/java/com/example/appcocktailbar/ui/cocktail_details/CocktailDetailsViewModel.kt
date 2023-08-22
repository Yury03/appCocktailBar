package com.example.appcocktailbar.ui.cocktail_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcocktailbar.domain.usecases.DeleteCocktail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CocktailDetailsViewModel(
    private val deleteCocktail: DeleteCocktail
) : ViewModel() {
    fun removeCocktail(id: Long) =
        viewModelScope.launch(Dispatchers.IO) { deleteCocktail.invoke(id) }

}
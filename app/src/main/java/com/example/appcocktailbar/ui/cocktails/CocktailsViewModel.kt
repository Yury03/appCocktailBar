package com.example.appcocktailbar.ui.cocktails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcocktailbar.domain.models.CocktailModel
import com.example.appcocktailbar.domain.usecases.DeleteCocktail
import com.example.appcocktailbar.domain.usecases.GetAllCocktails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CocktailsViewModel(
    private val deleteCocktail: DeleteCocktail,
    private val getAllCocktails: GetAllCocktails,
) : ViewModel() {
    private val _cocktailsList: MutableLiveData<List<CocktailModel>> =
        MutableLiveData()
    val cocktailsList: LiveData<List<CocktailModel>> get() = _cocktailsList
    init {
        viewModelScope.launch(Dispatchers.IO) { _cocktailsList.postValue(getAllCocktails.invoke()) }
    }

}
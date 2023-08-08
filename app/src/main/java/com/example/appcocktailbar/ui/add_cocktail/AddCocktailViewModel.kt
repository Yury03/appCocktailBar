package com.example.appcocktailbar.ui.add_cocktail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcocktailbar.domain.models.CocktailModel
import com.example.appcocktailbar.domain.usecases.AddCocktail
import com.example.appcocktailbar.domain.usecases.EditCocktail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCocktailViewModel(
    private val addCocktail: AddCocktail,
    private val editCocktail: EditCocktail,
) : ViewModel() {

    private val _newIngredientName: MutableLiveData<String> =
        MutableLiveData()
    val newIngredientName: LiveData<String> get() = _newIngredientName
    fun addCocktail(model: CocktailModel) =
        viewModelScope.launch(Dispatchers.IO) { addCocktail.invoke(model) }

    fun editCocktail(model: CocktailModel) =
        viewModelScope.launch(Dispatchers.IO) { editCocktail.invoke(model) }

    fun saveNewIngredient(name: String) = _newIngredientName.postValue(name)

}
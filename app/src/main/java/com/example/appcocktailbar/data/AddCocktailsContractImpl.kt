package com.example.appcocktailbar.data

import android.content.Context
import com.example.appcocktailbar.data.room.CocktailDatabase
import com.example.appcocktailbar.data.room.dao.CocktailsDatabaseDao
import com.example.appcocktailbar.data.room.mappers.CocktailMapper
import com.example.appcocktailbar.domain.Repository
import com.example.appcocktailbar.domain.models.CocktailModel

class AddCocktailsContractImpl(private val context: Context) :
    Repository.AddCocktailFragmentContract {

    private val database: CocktailDatabase by lazy {
        CocktailDatabase.getDatabase(context)
    }

    private val cocktailsDao: CocktailsDatabaseDao by lazy {
        database.cocktailsDao()
    }

    override fun addCocktail(cocktail: CocktailModel) {
        cocktailsDao.insert(CocktailMapper.modelToEntity(cocktail))
    }

    override fun editCocktail(cocktail: CocktailModel) {
        cocktailsDao.deleteItemById(cocktail.id)
        addCocktail(cocktail)
    }
}
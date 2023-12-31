package com.example.appcocktailbar.data

import android.content.Context
import com.example.appcocktailbar.data.room.CocktailDatabase
import com.example.appcocktailbar.data.room.dao.CocktailsDatabaseDao
import com.example.appcocktailbar.data.room.mappers.CocktailMapper
import com.example.appcocktailbar.domain.Repository
import com.example.appcocktailbar.domain.models.CocktailModel

class CocktailsFragmentContractImpl(context: Context) :
    Repository.CocktailsFragmentContract {

    private val database: CocktailDatabase by lazy {
        CocktailDatabase.getDatabase(context)
    }

    private val cocktailsDao: CocktailsDatabaseDao by lazy {
        database.cocktailsDao()
    }

    override fun getCocktailsList(): List<CocktailModel> =
        cocktailsDao.getAll().map { CocktailMapper.entityToModel(it) }

    override fun deleteCocktail(id: Long) = cocktailsDao.deleteItemById(id)
}

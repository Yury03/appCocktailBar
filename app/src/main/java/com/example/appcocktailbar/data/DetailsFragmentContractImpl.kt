package com.example.appcocktailbar.data

import android.content.Context
import com.example.appcocktailbar.data.room.CocktailDatabase
import com.example.appcocktailbar.data.room.dao.CocktailsDatabaseDao
import com.example.appcocktailbar.domain.Repository

class DetailsFragmentContractImpl(
    context: Context,
): Repository.DetailsFragmentContract {
    private val database: CocktailDatabase by lazy {
        CocktailDatabase.getDatabase(context)
    }

    private val cocktailsDao: CocktailsDatabaseDao by lazy {
        database.cocktailsDao()
    }
    override fun deleteCocktail(id: Long) = cocktailsDao.deleteItemById(id)
}
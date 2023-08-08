package com.example.appcocktailbar.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.appcocktailbar.data.room.entity.CocktailEntity


@Dao
interface CocktailsDatabaseDao {
    @Query("SELECT * FROM cocktailentity")
    fun getAll(): List<CocktailEntity>

    @Insert
    fun insert(cocktail: CocktailEntity)

    @Delete
    fun delete(cocktail: CocktailEntity)

    @Query("DELETE FROM cocktailentity WHERE id = :itemId")
    fun deleteItemById(itemId: Long)
}
package com.example.appcocktailbar.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appcocktailbar.data.room.dao.CocktailsDatabaseDao
import com.example.appcocktailbar.data.room.entity.CocktailEntity


@Database(entities = [CocktailEntity::class], version = 1)
abstract class CocktailDatabase : RoomDatabase() {
    abstract fun cocktailsDao(): CocktailsDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: CocktailDatabase? = null

        fun getDatabase(context: Context): CocktailDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    CocktailDatabase::class.java, "cocktail_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}



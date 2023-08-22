package com.example.appcocktailbar.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity
data class CocktailEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String,
    @TypeConverters(ConverterList::class) val ingredients: List<String>,
    val recipe: String,
    val photoPath: String?,
) {
    class ConverterList {

        @TypeConverter
        fun fromList(list: List<String>?): String? {
            return list?.joinToString(",")
        }

        @TypeConverter
        fun toList(value: String?): List<String>? {
            return value?.split(",")?.map { it.trim() }
        }
    }
}



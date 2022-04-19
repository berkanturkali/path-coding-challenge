package com.example.pathchallenge.core.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pathchallenge.core.BuildConfig
import com.example.pathchallenge.core.cache.dao.CharactersDao
import com.example.pathchallenge.core.cache.model.CharacterEntity

@Database(
    entities = [
        CharacterEntity::class
    ],
    version = BuildConfig.databaseVersion,
    exportSchema = false
)
abstract class MarvelDb : RoomDatabase() {

    abstract val charactersDao: CharactersDao

    companion object {
        fun build(context: Context): MarvelDb = Room.databaseBuilder(
            context.applicationContext,
            MarvelDb::class.java,
            BuildConfig.databaseName
        )
            .fallbackToDestructiveMigration().build()
    }
}
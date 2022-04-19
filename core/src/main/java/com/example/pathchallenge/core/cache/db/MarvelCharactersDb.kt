package com.example.pathchallenge.core.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pathchallenge.core.BuildConfig
import com.example.pathchallenge.core.cache.dao.ComicsDao
import com.example.pathchallenge.core.cache.model.CharacterEntity
import com.example.pathchallenge.core.cache.model.ComicEntity

@Database(
    entities = [
        ComicEntity::class,
        CharacterEntity::class
    ],
    version = BuildConfig.databaseVersion,
    exportSchema = false

)
abstract class MarvelCharactersDb : RoomDatabase() {

    abstract val comicsDao: ComicsDao

    companion object {
        fun build(context: Context): MarvelCharactersDb = Room.databaseBuilder(
            context.applicationContext,
            MarvelCharactersDb::class.java,
            BuildConfig.databaseName
        )
            .fallbackToDestructiveMigration().build()
    }
}
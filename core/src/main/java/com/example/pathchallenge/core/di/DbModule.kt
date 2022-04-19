package com.example.pathchallenge.core.di

import android.content.Context
import com.example.pathchallenge.core.cache.dao.CharactersDao
import com.example.pathchallenge.core.cache.db.MarvelDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@[Module InstallIn(SingletonComponent::class)]
object DbModule {

    @[Provides Singleton]
    fun provideDb(@ApplicationContext context: Context): MarvelDb {
        return MarvelDb.build(context = context)
    }

    @[Provides Singleton]
    fun providePlatformsDao(db: MarvelDb): CharactersDao = db.charactersDao
}
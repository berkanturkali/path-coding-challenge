package com.example.pathchallenge.core.di

import android.content.Context
import com.example.pathchallenge.core.cache.dao.ComicsDao
import com.example.pathchallenge.core.cache.db.MarvelCharactersDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object DbModule {

    @[Provides Singleton]
    fun provideDb(@ApplicationContext context: Context): MarvelCharactersDb =
        MarvelCharactersDb.build(context)


    @[Provides Singleton]
    fun provideComicsDao(db: MarvelCharactersDb): ComicsDao = db.comicsDao
}
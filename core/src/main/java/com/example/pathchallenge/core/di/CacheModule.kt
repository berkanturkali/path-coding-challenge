package com.example.pathchallenge.core.di

import com.example.pathchallenge.core.cache.abstraction.CharactersCache
import com.example.pathchallenge.core.cache.implementation.CharactersCacheImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@[Module InstallIn(SingletonComponent::class)]
interface CacheModule {

    @get:Binds
    val CharactersCacheImpl.charactersCache: CharactersCache
}
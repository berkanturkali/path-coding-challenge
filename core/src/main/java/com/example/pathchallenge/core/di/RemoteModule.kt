package com.example.pathchallenge.core.di

import com.example.pathchallenge.core.BuildConfig
import com.example.pathchallenge.core.remote.abstraction.CharactersRemote
import com.example.pathchallenge.core.remote.implementation.CharactersRemoteImpl
import com.example.pathchallenge.core.remote.service.ApiService
import com.example.pathchallenge.factory.RemoteFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface RemoteModule {

    @get:Binds
    val CharactersRemoteImpl.charactersRemote: CharactersRemote

    companion object {
        @[Provides Singleton]
        fun provideApiService(remoteFactory: RemoteFactory): ApiService =
            remoteFactory.createRetrofit(
                url = BuildConfig.BASE_URL,
                isDebug = BuildConfig.DEBUG
            )
                .create(ApiService::class.java)
    }
}
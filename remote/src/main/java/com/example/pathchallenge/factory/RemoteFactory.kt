package com.example.pathchallenge.factory

import com.example.pathchallenge.Constants.HASH_FORMAT
import com.example.pathchallenge.Constants.PRIVATE_API_KEY
import com.example.pathchallenge.Constants.PUBLIC_API_KEY
import com.example.pathchallenge.extensions.toMD5
import com.example.pathchallenge.interceptor.NoInternetInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

public class RemoteFactory @Inject constructor(
    private val moshi: Moshi,
) {
    public fun createRetrofit(url: String, isDebug: Boolean): Retrofit {
        val client: OkHttpClient = makeOkHttpClient(
            makeLoggingInterceptor((isDebug))
        )
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    private fun makeOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NoInternetInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val timestamp = System.currentTimeMillis().toString()
                val chainRequest = chain.request()
                val originalUrl = chainRequest.url
                val httpUrl = originalUrl.newBuilder()
                    .addQueryParameter("apikey", PUBLIC_API_KEY)
                    .addQueryParameter("ts", System.currentTimeMillis().toString())
                    .addQueryParameter(
                        "hash", HASH_FORMAT.format(
                            timestamp, PRIVATE_API_KEY,
                            PUBLIC_API_KEY
                        ).toMD5()
                    )
                    .build()
                chain.proceed(chainRequest.newBuilder().url(httpUrl).build())
            }
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }
}
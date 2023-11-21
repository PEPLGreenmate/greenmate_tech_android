package com.pepl.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pepl.data.api.GreenmateApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideConverterFactory(
        json: Json,
    ): Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    @Singleton
    fun provideGithubApi(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): GreenmateApi {
        return Retrofit.Builder()
            .baseUrl("http://34.64.221.211:8080")
            .addConverterFactory(converterFactory)
            .client(okHttpClient).build()
            .create(GreenmateApi::class.java)
    }
}
package com.pepl.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pepl.data.api.AIApi
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
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(90, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
        .build()

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
    fun provideGreenmateApi(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): GreenmateApi {
        return Retrofit.Builder()
            .baseUrl("http://34.22.102.212")
            .addConverterFactory(converterFactory)
            .client(okHttpClient).build()
            .create(GreenmateApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAIApi(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): AIApi {
        return Retrofit.Builder()
            .baseUrl("https://asia-northeast3-greenmate-4343a.cloudfunctions.net")
            .addConverterFactory(converterFactory)
            .client(okHttpClient).build()
            .create(AIApi::class.java)
    }
}
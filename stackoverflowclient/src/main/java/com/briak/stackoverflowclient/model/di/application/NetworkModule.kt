package com.briak.stackoverflowclient.model.di.application

import android.content.Context
import com.briak.stackoverflowclient.BuildConfig
import com.briak.stackoverflowclient.model.data.server.StackOverflowApi
import com.briak.stackoverflowclient.model.data.server.adapters.DateAdapter
import com.briak.stackoverflowclient.model.system.ResourceManager
import com.briak.stackoverflowclient.presentation.base.ErrorHandler
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context): OkHttpClient {
        return with(OkHttpClient.Builder()) {
            cache(Cache(context.cacheDir, 20 * 1024))
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                addNetworkInterceptor(
                        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
                )
            }
            build()
        }
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
            .add(Date::class.java, DateAdapter().nullSafe())
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
            okHttpClient: OkHttpClient,
            moshi: Moshi
    ): Retrofit =
            with(Retrofit.Builder()) {
                addCallAdapterFactory(CoroutineCallAdapterFactory())
                addConverterFactory(MoshiConverterFactory.create(moshi))
                client(okHttpClient)
                baseUrl("https://api.stackexchange.com")
                build()
            }

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): StackOverflowApi =
            retrofit.create(StackOverflowApi::class.java)

    @Provides
    @Singleton
    fun provideErrorHandler(resourceManager: ResourceManager) =
            ErrorHandler(resourceManager)

}
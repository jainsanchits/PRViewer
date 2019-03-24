package com.sjain.prviewer.di.module

import android.content.Context
import com.google.gson.Gson
import com.sjain.prviewer.BuildConfig
import com.sjain.prviewer.di.qualifier.BaseUrl
import com.sjain.prviewer.di.qualifier.HeaderInterceptor
import com.sjain.prviewer.service.PullRequestService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetModule {

    @JvmStatic
    private val commonHeaderMap = mutableMapOf<String, String>().apply {
        put("Accept", "application/vnd.github.v3+json")
    }

    @Provides
    @BaseUrl
    @Reusable
    @JvmStatic
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Reusable
    @JvmStatic
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.NONE }

    @Provides
    @Named("cacheSize")
    @Reusable
    @JvmStatic
    fun getCacheSize(): Long = 10 * 1024 * 1024L

    @Provides
    @HeaderInterceptor
    @Reusable
    @JvmStatic
    fun provideInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
            .newBuilder()
        commonHeaderMap.forEach { (header, value) -> request.addHeader(header, value) }
        chain.proceed(request.build())
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideHttpCache(
        context: Context,
        @Named("cacheSize") cacheSize: Long
    ): Cache = Cache(context.cacheDir, cacheSize)

    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor, httpCache: Cache,
        @HeaderInterceptor headerInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .cache(httpCache)
            .addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofit(@BaseUrl baseUrl: String, okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    @JvmStatic
    fun providePullRequestApi(retrofit: Retrofit): PullRequestService.PullRequestApi =
        retrofit.create(PullRequestService.PullRequestApi::class.java)
}

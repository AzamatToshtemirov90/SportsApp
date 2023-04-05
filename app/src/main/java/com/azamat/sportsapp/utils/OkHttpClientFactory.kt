package com.azamat.sportsapp.utils

import com.azamat.sportsapp.model.Constants.OkHttp_TIMEOUT
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import com.azamat.sportsapp.BuildConfig


class OkHttpClientFactory {

    fun create(): OkHttpClient.Builder = OkHttpClient.Builder().apply {
        connectTimeout(OkHttp_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(OkHttp_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(OkHttp_TIMEOUT, TimeUnit.SECONDS)
        addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
        addInterceptor(Interceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader("\$limit", "10")
                    .build()
            )
        })
    }
}
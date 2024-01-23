package com.example.notesapp.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetfofitBuilder {

    private const val BASE_URL = "http://13.229.182.118:5000"

    val retrofit: Retrofit by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()
            )
            .build()
    }
//    fun build(context: Context): Retrofit {
//        val okhttpBuilder = OkHttpClient.Builder()
//        okhttpBuilder.connectTimeout(60, TimeUnit.SECONDS)
//        okhttpBuilder.cache(Cache(context.cacheDir, 10 * 1024 * 1024))
//        okhttpBuilder.writeTimeout(60, TimeUnit.SECONDS)
//        okhttpBuilder.readTimeout(60, TimeUnit.SECONDS)
//
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        okhttpBuilder.addInterceptor(interceptor)
//
//        val gson = GsonBuilder().setLenient().create()
//        return Retrofit.Builder().baseUrl("http://13.229.182.118:5000")
//            .client(okhttpBuilder.build()).addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//    }
}
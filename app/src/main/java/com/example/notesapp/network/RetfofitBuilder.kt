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

//    private const val BASE_URL = "http://13.229.182.118:5000"
    private const val BASE_URL = "http://10.0.2.2:5000/"
    val retrofit: Retrofit by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()

        // Add the Authorization header with the JWT Bearer token
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Authorization", "Bearer YOUR_JWT_TOKEN")
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        val client = httpClient
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

//    val retrofit: Retrofit by lazy {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(
//                OkHttpClient.Builder()
//                    .addInterceptor(loggingInterceptor)
//                    .build()
//            )
//            .build()
//    }
}
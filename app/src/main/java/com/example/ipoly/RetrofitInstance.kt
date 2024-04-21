package com.example.ipoly

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitInstance {

    private val client = OkHttpClient.Builder()
        .connectTimeout(1000, TimeUnit.MILLISECONDS)
        .readTimeout(1000, TimeUnit.MILLISECONDS)
        .writeTimeout(1000, TimeUnit.MILLISECONDS)
        .build()

    private var gson = GsonBuilder()
        .setLenient()
        .create()
    val api:UsersApi by lazy{
        Retrofit.Builder()
            .baseUrl("https://7e4d-37-157-216-58.ngrok-free.app/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(UsersApi::class.java)
    }
}
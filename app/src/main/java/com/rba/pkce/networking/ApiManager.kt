package com.rba.pkce.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiManager {

    private var apiInterface: ApiInterface? = null

    internal val retrofit: Retrofit
        get() {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.HEADERS

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(HeaderInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    fun get(): ApiInterface {

        if (apiInterface == null) {
            apiInterface = retrofit.create(ApiInterface::class.java)
        }

        return apiInterface!!
    }
}
package com.example.webgados.utils

import com.example.webgados.api.AnnouncementService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    companion object {

        fun getRetrofit(): AnnouncementService {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl(AnnouncementConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(AnnouncementService::class.java)
        }
    }
}
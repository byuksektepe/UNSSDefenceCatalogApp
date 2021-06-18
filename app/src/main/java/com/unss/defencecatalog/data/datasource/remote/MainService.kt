package com.unss.defencecatalog.data.datasource.remote

import com.unss.defencecatalog.data.model.KatgoriResponseItem
import com.unss.defencecatalog.data.model.KullanicilarResponse
import com.unss.defencecatalog.util.Const
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MainService {
    @GET("kullanicilar/socket-unss-read.json")
    suspend fun getUsers(): Response<KullanicilarResponse>

    @GET("api.json")
    suspend fun getCategories(): Response<List<KatgoriResponseItem>>


    companion object{
        fun build(): MainService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Const.BASE_URL)
                .client(okHttpClient)
                .build()

            return retrofit.create(MainService::class.java)
        }
    }
}
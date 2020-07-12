package com.e.geomob.data.network

import com.e.geomob.data.network.model.WikiResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

const val SERVER_URL = "https://en.wikipedia.org/w/"

interface WikiApi {

    @GET("api.php")
    suspend fun getDescription(
        @Query("action") action : String = "query",
        @Query("prop") prop : String  = "extracts",
        @Query("format") format : String  ="json",
        @Query("exintro") exintro : String  ="",
        @Query("titles") countryName : String
    ) : Response<WikiResult>



    companion object {
        operator fun invoke() : WikiApi {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
                .callTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(1000, TimeUnit.SECONDS)
                .readTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(1000, TimeUnit.SECONDS)
                .addInterceptor(logging)


            return  Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build()
                .create(WikiApi::class.java)


        }
    }

}
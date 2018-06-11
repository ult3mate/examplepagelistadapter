package com.ult3mate.examplepagelistadapter.api

import com.ult3mate.examplepagelistadapter.dao.GithubUser
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by beer on 11/6/2018 AD.
on MAC ExamplePageListAdapter
 */
interface GithubApi {

    @GET("/users")
    fun getUsers(@Query("since") userId: Long, @Query("per_page") perPage: Int): Single<List<GithubUser>>

    companion object {
        private const val BASE_URL = "https://api.github.com/"
        fun create(): GithubApi {
            val client = OkHttpClient.Builder().build()
            return Retrofit.Builder()
                    .baseUrl(HttpUrl.parse(BASE_URL)!!)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(GithubApi::class.java)
        }
    }
}
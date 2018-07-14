package com.ult3mate.examplepagelistadapter.api

import com.perspective23.willing.modules.dao.BaseListResult
import com.perspective23.willing.modules.dao.feed.FeedResult
import io.reactivex.Observable
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Pattadon on 7/14/2018.
Work By ult3mate
 */
interface WillingApi {
    @GET("explore_feed")
    fun exploreFeed(@Query("page") page: String, @Query("perpage") perPage: String, @Query("user_id") userId: String): Observable<BaseListResult<FeedResult>>

    companion object {
        private const val BASE_URL = "http://api.willing.in.th/main/"
        fun create(): WillingApi {
            val client = OkHttpClient.Builder().build()
            return Retrofit.Builder()
                    .baseUrl(HttpUrl.parse(BASE_URL)!!)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(WillingApi::class.java)
        }
    }
}
package com.ult3mate.examplepagelistadapter.dao

import com.google.gson.annotations.SerializedName

/**
 * Created by beer on 11/6/2018 AD.
on MAC ExamplePageListAdapter
 */
data class GithubUser(
        @SerializedName("login")
        var login: String,
        @SerializedName("id")
        var id: Long,
        @SerializedName("avatar_url")
        var avatar_url: String,
        @SerializedName("html_url")
        var html_url: String
)
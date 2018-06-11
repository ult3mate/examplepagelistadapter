package com.ult3mate.examplepagelistadapter.dao

/**
 * Created by beer on 11/6/2018 AD.
on MAC ExamplePageListAdapter
 */

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

data class NetworkState private constructor(
        val status: Status,
        val message: String? = null) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}
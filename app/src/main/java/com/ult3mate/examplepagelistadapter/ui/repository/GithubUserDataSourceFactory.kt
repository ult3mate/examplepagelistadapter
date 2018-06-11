package com.ult3mate.examplepagelistadapter.ui.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.ult3mate.examplepagelistadapter.api.GithubApi
import com.ult3mate.examplepagelistadapter.dao.GithubUser
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by beer on 11/6/2018 AD.
on MAC ExamplePageListAdapter
 */
class GithubUserDataSourceFactory(private val compositeDisposable: CompositeDisposable,
                                  private val githubService: GithubApi) : DataSource.Factory<Long, GithubUser>() {

    val usersDataSourceLiveData = MutableLiveData<GithubUserDataSource>()


    override fun create(): DataSource<Long, GithubUser> {
        val usersDataSource = GithubUserDataSource(githubService, compositeDisposable)
        usersDataSourceLiveData.postValue(usersDataSource)
        return usersDataSource
    }
}
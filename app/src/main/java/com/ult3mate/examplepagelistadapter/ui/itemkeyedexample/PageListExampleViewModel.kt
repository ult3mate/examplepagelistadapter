package com.ult3mate.examplepagelistadapter.ui.itemkeyedexample

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.ult3mate.examplepagelistadapter.api.GithubApi
import com.ult3mate.examplepagelistadapter.dao.GithubUser
import com.ult3mate.examplepagelistadapter.dao.NetworkState
import com.ult3mate.examplepagelistadapter.ui.repository.GithubUserDataSource
import com.ult3mate.examplepagelistadapter.ui.repository.GithubUserDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class PageListExampleViewModel : ViewModel() {
    var userList: LiveData<PagedList<GithubUser>>? = null

    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 10

    val sourceFactory: GithubUserDataSourceFactory

    init {
        sourceFactory = GithubUserDataSourceFactory(compositeDisposable, GithubApi.create())
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build()
        userList = LivePagedListBuilder<Long, GithubUser>(sourceFactory, config).build()
    }

    fun retry() {
//        sourceFactory.usersDataSourceLiveData.value?.re
    }

    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap<GithubUserDataSource, NetworkState>(
            sourceFactory.usersDataSourceLiveData, { it.networkState })


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

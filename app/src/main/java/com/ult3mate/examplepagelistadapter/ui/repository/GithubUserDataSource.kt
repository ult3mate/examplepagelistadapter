package com.ult3mate.examplepagelistadapter.ui.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.ItemKeyedDataSource
import com.ult3mate.examplepagelistadapter.api.GithubApi
import com.ult3mate.examplepagelistadapter.dao.GithubUser
import com.ult3mate.examplepagelistadapter.dao.NetworkState
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by beer on 11/6/2018 AD.
on MAC ExamplePageListAdapter
 */
class GithubUserDataSource(
        private val githubService: GithubApi,
        private val compositeDisposable: CompositeDisposable)
    : ItemKeyedDataSource<Long, GithubUser>() {

    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<GithubUser>) {
        compositeDisposable.add(githubService.getUsers(1, params.requestedLoadSize).subscribe({ users ->
            callback.onResult(users)
        }, { throwable ->
            throwable.printStackTrace()

        }))
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<GithubUser>) {
        //get the users from the api after id
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(githubService.getUsers(params.key, params.requestedLoadSize).subscribe({ users ->
            networkState.postValue(NetworkState.LOADED)
            callback.onResult(users)
        }, { throwable ->
            networkState.postValue(NetworkState.error(throwable.message))
            throwable.printStackTrace()
        }))
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<GithubUser>) {
        // ignored, since we only ever append to our initial load
    }

    override fun getKey(item: GithubUser): Long {
        return item.id
    }
}
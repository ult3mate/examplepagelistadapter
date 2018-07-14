package com.ult3mate.examplepagelistadapter.ui.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.perspective23.willing.modules.dao.feed.FeedResult
import com.ult3mate.examplepagelistadapter.api.WillingApi
import com.ult3mate.examplepagelistadapter.dao.NetworkState
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Pattadon on 7/14/2018.
Work By ult3mate
 */
class WillingDataSource(
        private val willingApi: WillingApi,
        private val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<String, FeedResult>() {

    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, FeedResult>) {
        compositeDisposable.add(willingApi.exploreFeed(1.toString(), params.requestedLoadSize.toString(), "1").subscribe({ feedResult ->
            callback.onResult(feedResult.data as MutableList<FeedResult>, (feedResult.pagination?.nextPage?.minus(1)).toString(), feedResult.pagination?.nextPage.toString())
        }, { throwable ->
            throwable.printStackTrace()
        }))
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, FeedResult>) {
        compositeDisposable.add(willingApi.exploreFeed(params.key, params.requestedLoadSize.toString(), "1").subscribe({ feedResult ->
            callback.onResult(feedResult.data as MutableList<FeedResult>, (feedResult.pagination?.nextPage?.plus(1).toString()))
        }, { throwable ->
            throwable.printStackTrace()

        }))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, FeedResult>) {
        // do nothing
    }


}
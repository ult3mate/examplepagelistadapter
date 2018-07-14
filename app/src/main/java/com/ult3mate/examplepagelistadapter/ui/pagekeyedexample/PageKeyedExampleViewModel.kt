package com.ult3mate.examplepagelistadapter.ui.itemkeyedexample

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.perspective23.willing.modules.dao.feed.FeedResult
import com.ult3mate.examplepagelistadapter.api.WillingApi
import com.ult3mate.examplepagelistadapter.ui.repository.WillingDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class PageKeyedExampleViewModel : ViewModel() {
    var userList: LiveData<PagedList<FeedResult>>? = null

    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 10

    val sourceFactory: WillingDataSourceFactory

    init {
        sourceFactory = WillingDataSourceFactory(compositeDisposable, WillingApi.create())
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build()
        userList = LivePagedListBuilder<String, FeedResult>(sourceFactory, config).build()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

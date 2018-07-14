package com.ult3mate.examplepagelistadapter.ui.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.perspective23.willing.modules.dao.feed.FeedResult
import com.ult3mate.examplepagelistadapter.api.WillingApi
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Pattadon on 7/14/2018.
Work By ult3mate
 */
class WillingDataSourceFactory(private val compositeDisposable: CompositeDisposable,
                               private val willingApi: WillingApi) : DataSource.Factory<String, FeedResult>() {

    val usersDataSourceLiveData = MutableLiveData<WillingDataSource>()


    override fun create(): DataSource<String, FeedResult> {
        val willingDataSource = WillingDataSource(willingApi, compositeDisposable)
        usersDataSourceLiveData.postValue(willingDataSource)
        return willingDataSource
    }
}
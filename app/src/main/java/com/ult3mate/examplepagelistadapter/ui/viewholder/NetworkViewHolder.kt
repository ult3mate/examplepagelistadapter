package com.ult3mate.examplepagelistadapter.ui.viewholder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ult3mate.examplepagelistadapter.R
import com.ult3mate.examplepagelistadapter.dao.NetworkState
import com.ult3mate.examplepagelistadapter.dao.Status
import kotlinx.android.synthetic.main.page_list_example_loading_adapter.view.*

/**
 * Created by beer on 11/6/2018 AD.
on MAC ExamplePageListAdapter
 */
class NetworkViewHolder(view: View, private val retryCallback: () -> Unit) : RecyclerView.ViewHolder(view) {

    init {
        itemView.retryLoadingButton.setOnClickListener { retryCallback() }
    }

    fun bindTo(networkState: NetworkState?) {
        //error message
        itemView.errorMessageTextView.visibility = if (networkState?.message != null) View.VISIBLE else View.GONE
        if (networkState?.message != null) {
            itemView.errorMessageTextView.text = networkState.message
        }

        //loading and retry
        itemView.retryLoadingButton.visibility = if (networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
        itemView.loadingProgressBar.visibility = if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.page_list_example_loading_adapter, parent, false)
            return NetworkViewHolder(view, retryCallback)
        }
    }

}
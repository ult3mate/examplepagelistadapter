package com.ult3mate.examplepagelistadapter.ui.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.perspective23.willing.modules.dao.feed.FeedResult
import com.ult3mate.examplepagelistadapter.R
import com.ult3mate.examplepagelistadapter.dao.NetworkState
import com.ult3mate.examplepagelistadapter.ui.viewholder.NetworkViewHolder
import com.ult3mate.examplepagelistadapter.ui.viewholder.UserViewHolder

/**
 * Created by Pattadon on 7/14/2018.
Work By ult3mate
 */
class PageKeyedExampleAdapter(private val retryCallback: () -> Unit) : PagedListAdapter<FeedResult, RecyclerView.ViewHolder>(DiffCallback) {

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.page_list_example_adapter -> UserViewHolder.create(parent)
            R.layout.page_list_example_loading_adapter -> NetworkViewHolder.create(parent, retryCallback)
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.page_list_example_adapter -> (holder as UserViewHolder).bindTo(getItem(position))
            R.layout.page_list_example_loading_adapter -> (holder as NetworkViewHolder).bindTo(networkState)
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.page_list_example_loading_adapter
        } else {
            R.layout.page_list_example_adapter
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        if (currentList != null) {
            if (currentList?.size != 0) {
                val previousState = this.networkState
                val hadExtraRow = hasExtraRow()
                this.networkState = newNetworkState
                val hasExtraRow = hasExtraRow()
                if (hadExtraRow != hasExtraRow) {
                    if (hadExtraRow) {
                        notifyItemRemoved(super.getItemCount())
                    } else {
                        notifyItemInserted(super.getItemCount())
                    }
                } else if (hasExtraRow && previousState !== newNetworkState) {
                    notifyItemChanged(itemCount - 1)
                }
            }
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<FeedResult>() {
            override fun areItemsTheSame(oldItem: FeedResult?, newItem: FeedResult?): Boolean {
                return oldItem?.contentId == newItem?.contentId
            }

            override fun areContentsTheSame(oldItem: FeedResult?, newItem: FeedResult?): Boolean {
                return oldItem == newItem
            }
        }
    }

}
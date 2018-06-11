package com.ult3mate.examplepagelistadapter.ui.viewholder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.ult3mate.examplepagelistadapter.GlideApp
import com.ult3mate.examplepagelistadapter.R
import com.ult3mate.examplepagelistadapter.dao.GithubUser
import kotlinx.android.synthetic.main.page_list_example_adapter.view.*

/**
 * Created by beer on 11/6/2018 AD.
on MAC ExamplePageListAdapter
 */
class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(user: GithubUser?) {
        itemView.adapter_page_list_tv.text = user?.login
        GlideApp.with(itemView.context)
                .load(user?.avatar_url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .into(itemView.adapter_page_list_iv)

    }

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.page_list_example_adapter, parent, false)
            return UserViewHolder(view)
        }
    }

}
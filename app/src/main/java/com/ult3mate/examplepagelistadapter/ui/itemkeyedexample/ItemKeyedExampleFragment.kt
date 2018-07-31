package com.ult3mate.examplepagelistadapter.ui.itemkeyedexample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ult3mate.examplepagelistadapter.R
import com.ult3mate.examplepagelistadapter.api.GithubApi
import com.ult3mate.examplepagelistadapter.dao.GithubUser
import com.ult3mate.examplepagelistadapter.dao.NetworkState
import com.ult3mate.examplepagelistadapter.ui.adapter.PageListExampleAdapter
import kotlinx.android.synthetic.main.page_list_example_fragment.*

class ItemKeyedExampleFragment : Fragment() {

    private val api by lazy {
        GithubApi.create()
    }

    companion object {
        fun newInstance() = ItemKeyedExampleFragment()
    }
    private lateinit var githubAdapter: PageListExampleAdapter
    private lateinit var viewModel: ItemKeyedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.page_list_example_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ItemKeyedViewModel::class.java)
        initLayoutManager()
    }

    private fun initLayoutManager() {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        githubAdapter = PageListExampleAdapter {
            viewModel.retry()
        }
        page_list_rv.layoutManager = linearLayoutManager
        page_list_rv.adapter = githubAdapter

        viewModel.userList.observe(this, Observer<PagedList<GithubUser>> {
            githubAdapter.submitList(it)
        })

        viewModel.getNetworkState().observe(this, Observer<NetworkState> {
            githubAdapter.setNetworkState(it)
        })
    }
}

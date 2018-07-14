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
import com.perspective23.willing.modules.dao.feed.FeedResult
import com.ult3mate.examplepagelistadapter.R
import com.ult3mate.examplepagelistadapter.api.GithubApi
import com.ult3mate.examplepagelistadapter.ui.adapter.PageKeyedExampleAdapter
import kotlinx.android.synthetic.main.page_list_example_fragment.*

class PageKeyedExampleFragment : Fragment() {

    private val api by lazy {
        GithubApi.create()
    }

    companion object {
        fun newInstance() = PageKeyedExampleFragment()
    }
    private lateinit var willingAdapter: PageKeyedExampleAdapter
    private lateinit var viewModel: PageKeyedExampleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.page_list_example_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PageKeyedExampleViewModel::class.java)
        initLayoutManager()
    }

    private fun initLayoutManager() {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        willingAdapter = PageKeyedExampleAdapter {
        }
        page_list_rv.layoutManager = linearLayoutManager
        page_list_rv.adapter = willingAdapter
        viewModel.userList?.observe(this, Observer<PagedList<FeedResult>> {
            willingAdapter.submitList(it)
        })
    }
}

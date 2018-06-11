package com.ult3mate.examplepagelistadapter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ult3mate.examplepagelistadapter.api.GithubApi
import com.ult3mate.examplepagelistadapter.ui.pagelistexample.PageListExampleFragment

class PageListExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_list_example_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PageListExampleFragment.newInstance())
                    .commitNow()
        }
    }

}

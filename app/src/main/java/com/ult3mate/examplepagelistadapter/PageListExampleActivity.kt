package com.ult3mate.examplepagelistadapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ult3mate.examplepagelistadapter.ui.itemkeyedexample.PageListExampleFragment

class PageListExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_list_example_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PageListExampleFragment.newInstance())
                    .commitNow()
        }

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, PageKeyedExampleFragment.newInstance())
//                    .commitNow()
//        }
    }

}

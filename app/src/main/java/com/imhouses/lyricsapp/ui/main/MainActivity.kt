package com.imhouses.lyricsapp.ui.main

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.imhouses.lyricsapp.R
import com.imhouses.lyricsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mSearchView: SearchView
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.state = MainActivityState.DEFAULT
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        val searchMgr = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        menu?.also {
            mSearchView = (it.findItem(R.id.search).actionView as SearchView).apply {
                setSearchableInfo(searchMgr.getSearchableInfo(componentName))
                isIconifiedByDefault = false
                requestFocus()
            }
            mSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextChange(newText: String?): Boolean {
                    // TODO: Make HTTP Request
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean = false
            })
        }
        return true
    }
}
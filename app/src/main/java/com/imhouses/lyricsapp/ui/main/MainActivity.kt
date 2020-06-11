package com.imhouses.lyricsapp.ui.main

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.imhouses.lyricsapp.LyricsApp
import com.imhouses.lyricsapp.R
import com.imhouses.lyricsapp.databinding.ActivityMainBinding
import com.imhouses.lyricsapp.domain.ResultEntity
import com.imhouses.lyricsapp.domain.SongEntity
import com.imhouses.lyricsapp.domain.error.ErrorEntity
import com.imhouses.lyricsapp.ui.main.adapter.SuggestionsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    @Inject lateinit var mViewModel: MainActivityViewModel
    private val mSuggestions: ArrayList<SongEntity> = arrayListOf()
    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.state = MainActivityState.DEFAULT
        configureRecyclerView()
        (application as LyricsApp).appComponent.inject(this)
        mViewModel.suggestionsLiveData.observe(this, Observer { processResult(it) })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        val searchMgr = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        menu?.also {
            val searchView = (it.findItem(R.id.search).actionView as SearchView).apply {
                setSearchableInfo(searchMgr.getSearchableInfo(componentName))
                isIconifiedByDefault = false
                requestFocus()
            }
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextChange(newText: String?): Boolean {
                    job.cancelChildren()
                    if (!newText.isNullOrEmpty()) {
                        coroutineScope.launch {
                            delay(1_000)
                            mBinding.state = MainActivityState.LOADING
                            mViewModel.getSuggestions(newText)
                        }
                    }
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean = false
            })
        }
        return true
    }

    private fun configureRecyclerView() {
        recyclerViewSuggestions.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = SuggestionsAdapter(mSuggestions)
            setHasFixedSize(false)
        }
    }

    private fun processResult(result: ResultEntity<List<SongEntity>>) {
        when (result) {
            is ResultEntity.Success -> {
                mBinding.state = MainActivityState.SHOWING_RESULTS
                mSuggestions.clear()
                mSuggestions.addAll(result.data)
                recyclerViewSuggestions.adapter?.notifyDataSetChanged()
            }
            is ResultEntity.Error -> {
                mBinding.state = MainActivityState.NOTIFY
                processError(result.errorEntity)
            }
        }
    }

    private fun processError(errorEntity: ErrorEntity) {
        errorEntity.originalException.printStackTrace()
        when (errorEntity) {
            is ErrorEntity.Network -> {
                mBinding.notifyString = getString(R.string.main_connection_error)
            }
            else -> {
                mBinding.notifyString = getString(R.string.generic_error)
            }
        }
    }
}
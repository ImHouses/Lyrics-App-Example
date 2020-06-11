package com.imhouses.lyricsapp.ui.lyrics

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.imhouses.lyricsapp.LyricsApp
import com.imhouses.lyricsapp.R
import com.imhouses.lyricsapp.databinding.ActivityLyricsDetailBinding
import com.imhouses.lyricsapp.domain.LyricsEntity
import com.imhouses.lyricsapp.domain.ResultEntity
import com.imhouses.lyricsapp.domain.error.ErrorEntity
import com.imhouses.lyricsapp.ui.IntentConstants
import com.imhouses.lyricsapp.ui.model.Song
import javax.inject.Inject

class LyricsDetailActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityLyricsDetailBinding
    @Inject
    lateinit var mViewModel: LyricsDetailViewModel
    private lateinit var mSong: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_lyrics_detail)
        (application as LyricsApp).appComponent.inject(this)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24)
        }
        mSong = intent.extras?.let { extras ->
             extras.get(IntentConstants.TO_LYRICS_DETAIL_SONG_ENTITY_KEY) as? Song
        } ?: return
        mBinding.isLoading = true
        mBinding.song = mSong
        mViewModel.lyricsLiveData.observe(this@LyricsDetailActivity, Observer { processResult(it) })
        mViewModel.getLyrics(songTitle = mSong.songTitle, artistName = mSong.artistName)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun processResult(result: ResultEntity<LyricsEntity>) {
        mBinding.isLoading = false
        when (result) {
            is ResultEntity.Success -> mBinding.lyrics = result.data
            is ResultEntity.Error -> processError(result.errorEntity)
        }
    }

    private fun processError(errorEntity: ErrorEntity) {
        @StringRes
        val errorStringRes = when(errorEntity) {
            is ErrorEntity.Network -> R.string.connection_error
            else -> R.string.generic_error
        }
        Toast.makeText(
            this,
            errorStringRes,
            Toast.LENGTH_SHORT
        ).show()
    }
}
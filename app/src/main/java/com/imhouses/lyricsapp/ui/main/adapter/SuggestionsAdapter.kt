package com.imhouses.lyricsapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.imhouses.lyricsapp.databinding.LayoutLyricsSuggestionBinding
import com.imhouses.lyricsapp.domain.SongEntity

class SuggestionsAdapter(val suggestions: ArrayList<SongEntity>) : RecyclerView.Adapter<SuggestionsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: LayoutLyricsSuggestionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutLyricsSuggestionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = suggestions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val suggestion = suggestions[position]
        holder.binding.apply {
            artistName = suggestion.artistName
            imageUrl = suggestion.albumThumb
            songTitle = suggestion.songTitle
            albumThumbUrl = suggestion.albumThumb
        }.executePendingBindings()
    }
}
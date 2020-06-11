package com.imhouses.lyricsapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imhouses.lyricsapp.databinding.LayoutLyricsSuggestionBinding
import com.imhouses.lyricsapp.ui.model.Song

class SuggestionsAdapter(
    private val suggestions: ArrayList<Song>,
    private val onClick: (Song)->Unit
) : RecyclerView.Adapter<SuggestionsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: LayoutLyricsSuggestionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutLyricsSuggestionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ).apply {
        binding.root.setOnClickListener {
            onClick(binding.song!!)
        }
    }

    override fun getItemCount(): Int = suggestions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val suggestion = suggestions[position]
        holder.binding.apply { song = suggestion }.executePendingBindings()
    }
}
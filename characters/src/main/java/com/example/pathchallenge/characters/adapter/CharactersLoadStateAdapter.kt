package com.example.pathchallenge.characters.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.pathchallenge.characters.adapter.viewHolder.CharactersLoadStateViewHolder
import com.example.pathchallenge.characters.adapter.viewHolder.Retry

class CharactersLoadStateAdapter(private val retry: Retry) :
    LoadStateAdapter<CharactersLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: CharactersLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CharactersLoadStateViewHolder {
        return CharactersLoadStateViewHolder.create(parent, retry)
    }
}
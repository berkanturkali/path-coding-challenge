package com.example.pathchallenge.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.pathchallenge.characters.databinding.FavoritesItemBinding
import com.example.pathchallenge.common.exts.load
import com.example.pathchallenge.core.domain.model.characters.Character
import javax.inject.Inject

class FavoritesAdapter @Inject constructor(
    private val circularProgressDrawable: CircularProgressDrawable,
) : ListAdapter<Character, FavoritesAdapter.ViewHolder>(CHARACTER_COMPARATOR) {

    companion object {
        val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Character,
                newItem: Character,
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesAdapter.ViewHolder {
        return ViewHolder(
            FavoritesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.ViewHolder, position: Int) {
        val advert = getItem(position)
        advert?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: FavoritesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Character) {
            binding.apply {
                characterImage.load(
                    item.imageUrl,
                    circularProgressDrawable = circularProgressDrawable
                )
                characterName.text = item.name
                characterDescription.text = item.description
            }
        }
    }
}
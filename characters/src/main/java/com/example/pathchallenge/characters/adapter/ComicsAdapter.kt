package com.example.pathchallenge.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.pathchallenge.characters.databinding.ComicItemBinding
import com.example.pathchallenge.common.exts.load
import com.example.pathchallenge.core.domain.model.comics.Comic
import javax.inject.Inject

class ComicsAdapter @Inject constructor(
    private val circularProgressDrawable: CircularProgressDrawable
) : ListAdapter<Comic, ComicsAdapter.ComicViewHolder>(COMIC_COMPARATOR) {

    companion object {
        val COMIC_COMPARATOR = object : DiffUtil.ItemCallback<Comic>() {
            override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
                return oldItem == newItem
            }
        }
    }


    inner class ComicViewHolder(private val binding: ComicItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comic: Comic) {
            binding.apply {
                comicImage.load(
                    url = comic.image,
                    circularProgressDrawable = circularProgressDrawable
                )
                comicName.text = comic.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder =
        ComicViewHolder(
            ComicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }
}
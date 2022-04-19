package com.example.pathchallenge.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.pathchallenge.characters.databinding.CharacterItemBinding
import com.example.pathchallenge.common.ItemClickListener
import com.example.pathchallenge.common.exts.load
import com.example.pathchallenge.core.domain.model.characters.Character
import javax.inject.Inject

class CharactersAdapter @Inject constructor(
    private val circularProgressDrawable: CircularProgressDrawable
) :
    PagingDataAdapter<Character, CharactersAdapter.CharactersViewHolder>(CharacterComparator) {

    private lateinit var listener: ItemClickListener<Character>


    object CharacterComparator : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    fun setListener(listener: ItemClickListener<Character>) {
        this.listener = listener
    }

    inner class CharactersViewHolder(private val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    item?.let {
                        listener.onItemClick(it)
                    }
                }
            }
        }

        fun bind(item: Character) {
            binding.apply {
                characterImage.load(
                    item.imageUrl,
                    circularProgressDrawable = circularProgressDrawable
                )
                characterName.text = item.name
            }
        }
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder =
        CharactersViewHolder(
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
}


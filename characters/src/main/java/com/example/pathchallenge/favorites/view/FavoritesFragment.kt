package com.example.pathchallenge.favorites.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.pathchallenge.characters.databinding.FragmentFavoritesBinding
import com.example.pathchallenge.common.BaseFragment
import com.example.pathchallenge.favorites.adapter.FavoritesAdapter
import com.example.pathchallenge.favorites.viewModel.FavoritesFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate) {

    private val mViewModel by viewModels<FavoritesFragmentViewModel>()

    @Inject
    lateinit var mAdapter: FavoritesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSwipeToDelete()
        initRecyclerview()
        subscribeObservers()
    }

    private fun initRecyclerview() {
        binding.favoritesRv.apply {
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }


    private fun subscribeObservers() {
        mViewModel.favorites.observe(viewLifecycleOwner) {
            binding.favoritesRv.isVisible = it.isNotEmpty()
            binding.emptyView.isVisible = it.isEmpty()
            mAdapter.submitList(it)
        }
    }

    private fun initSwipeToDelete() {
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val advert = mAdapter.currentList[position]
                mViewModel.removeFromFav(advert)
            }
        }

        ItemTouchHelper(itemTouchHelper).apply {
            attachToRecyclerView(binding.favoritesRv)
        }
    }
}
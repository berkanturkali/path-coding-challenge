package com.example.pathchallenge.characters.view

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pathchallenge.characters.adapter.CharactersAdapter
import com.example.pathchallenge.characters.adapter.CharactersLoadStateAdapter
import com.example.pathchallenge.characters.databinding.FragmentCharactersBinding
import com.example.pathchallenge.characters.viewModel.CharactersFragmentViewModel
import com.example.pathchallenge.common.BaseFragment
import com.example.pathchallenge.common.ItemClickListener
import com.example.pathchallenge.common.exts.setItemDecorationSpacing
import com.example.pathchallenge.core.domain.model.characters.Character
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment :
    BaseFragment<FragmentCharactersBinding>(FragmentCharactersBinding::inflate),
    ItemClickListener<Character> {

    @Inject
    lateinit var mAdapter: CharactersAdapter

    private val mViewModel by viewModels<CharactersFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initSwipeToRefresh()
        binding.charactersList.apply {
            setItemDecorationSpacing(40)
        }
        subscribeObservers()
    }

    private fun initAdapter() {
        mAdapter.apply {
            setListener(this@CharactersFragment)
            binding.retryButton.setOnClickListener {
                retry()
            }
            val headerFooterAdapter = CharactersLoadStateAdapter(mAdapter::retry)

            val concatAdapter = mAdapter.withLoadStateFooter(
                footer = headerFooterAdapter
            )
            binding.charactersList.adapter = concatAdapter
            (binding.charactersList.layoutManager as GridLayoutManager).spanSizeLookup =
                object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position == mAdapter.itemCount && headerFooterAdapter.itemCount > 0) {
                            2
                        } else {
                            1
                        }
                    }
                }
            addLoadStateListener { loadState ->
                val isListEmpty = loadState.refresh is LoadState.NotLoading && itemCount == 0
                showList(!isListEmpty)
                showProgress(loadState.source.refresh is LoadState.Loading)
                binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error
            }
        }
    }

    private fun initSwipeToRefresh() {
        binding.swipeRefresh.apply {
            setOnRefreshListener {
                mAdapter.refresh()
            }
            setColorSchemeColors(
                ContextCompat.getColor(
                    requireContext(),
                    com.example.pathchallenge.common.R.color.primary
                ),
                ContextCompat.getColor(
                    requireContext(),
                    com.example.pathchallenge.common.R.color.on_primary
                )
            )
        }
    }

    private fun subscribeObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            mViewModel.characters.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }

    private fun showList(show: Boolean) {
        binding.charactersList.isVisible = show
    }

    private fun showProgress(isRefreshing: Boolean) {
        binding.swipeRefresh.isRefreshing = isRefreshing
    }

    override fun onItemClick(item: Character) {
        val action = CharactersFragmentDirections.actionCharactersFragmentToDetailsFragment(item)
        findNavController().navigate(action)
    }
}
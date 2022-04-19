package com.example.pathchallenge.characters.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
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
            adapter = mAdapter
            setItemDecorationSpacing(50)
        }
        subscribeObservers()
    }

    private fun initAdapter() {
        mAdapter.apply {
            setListener(this@CharactersFragment)
            binding.retryButton.setOnClickListener {
                retry()
            }
            withLoadStateHeaderAndFooter(
                header = CharactersLoadStateAdapter { mAdapter.retry() },
                footer = CharactersLoadStateAdapter { mAdapter.retry() },
            )
            addLoadStateListener { loadState ->
                val isListEmpty = loadState.refresh is LoadState.NotLoading && itemCount == 0
                showList(!isListEmpty)
                showProgress(loadState.source.refresh is LoadState.Loading)
                binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error
            }
        }
    }

    private fun initSwipeToRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            mAdapter.refresh()
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

    }
}
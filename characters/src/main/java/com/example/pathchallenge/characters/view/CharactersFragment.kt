package com.example.pathchallenge.characters.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.pathchallenge.characters.databinding.FragmentCharactersBinding
import com.example.pathchallenge.characters.viewModel.CharactersFragmentViewModel
import com.example.pathchallenge.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment :
    BaseFragment<FragmentCharactersBinding>(FragmentCharactersBinding::inflate) {

    private val mViewModel by viewModels<CharactersFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        mViewModel.characters.observe(viewLifecycleOwner){

        }
    }
}
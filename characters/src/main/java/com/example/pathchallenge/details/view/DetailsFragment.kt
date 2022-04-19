package com.example.pathchallenge.details.view

import android.os.Bundle
import android.view.View
import com.example.pathchallenge.characters.databinding.FragmentCharacterDetailBinding
import com.example.pathchallenge.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment :
    BaseFragment<FragmentCharacterDetailBinding>(FragmentCharacterDetailBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
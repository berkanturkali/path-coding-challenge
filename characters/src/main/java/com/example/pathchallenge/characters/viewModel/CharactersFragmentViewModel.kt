package com.example.pathchallenge.characters.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pathchallenge.core.data.repository.abstraction.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersFragmentViewModel @Inject constructor(
    private val repo: CharactersRepository
) : ViewModel() {

    val characters = repo.fetchCharacters().cachedIn(viewModelScope).asLiveData()
}
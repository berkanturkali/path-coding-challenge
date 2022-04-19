package com.example.pathchallenge.favorites.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathchallenge.core.data.repository.abstraction.CharactersRepository
import com.example.pathchallenge.core.domain.model.characters.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoritesFragmentViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _favorites = MutableLiveData<List<Character>>()

    val favorites: LiveData<List<Character>> get() = _favorites

    init {
        getFavorites()
    }

    private fun getFavorites() {
        charactersRepository.characters().onEach {
            _favorites.value = it
        }.launchIn(viewModelScope)
    }

    fun removeFromFav(character: Character) {
        viewModelScope.launch {
            charactersRepository.delete(character)
        }
    }
}
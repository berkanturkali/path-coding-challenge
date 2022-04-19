package com.example.pathchallenge.details.viewModel

import androidx.lifecycle.*
import com.example.pathchallenge.core.data.repository.abstraction.CharactersRepository
import com.example.pathchallenge.core.domain.model.characters.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val charactersRepo: CharactersRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _character = MutableLiveData<Character>()

    val character: LiveData<Character> get() = _character

//    val comics = Transformations.switchMap(_id) {
//        liveData {
//
//        }
//    }

    init {
        savedStateHandle.get<Character>("character")?.let {
            setCharacter(it)
        }
    }

    private fun setCharacter(character: Character) {
        _character.value = character
    }

    fun fetchComics(id: Int) {
        _id.value = id
    }
}
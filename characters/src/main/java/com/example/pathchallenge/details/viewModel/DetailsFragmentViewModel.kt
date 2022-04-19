package com.example.pathchallenge.details.viewModel

import androidx.lifecycle.*
import com.example.pathchallenge.core.data.repository.abstraction.CharactersRepository
import com.example.pathchallenge.core.domain.model.characters.Character
import com.example.pathchallenge.core.remote.util.Resource
import com.example.pathchallenge.core.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val charactersRepo: CharactersRepository,
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _character = MutableLiveData<Character>()

    val character: LiveData<Character> get() = _character

    private val _isCharacterInFavorites = MutableLiveData<Event<Boolean>>()

    val isCharacterInFavorites: LiveData<Event<Boolean>> get() = _isCharacterInFavorites

    val comics = Transformations.switchMap(_id) {
        liveData {
            emit(Resource.Loading())
            emit(charactersRepo.fetchComics(it))
        }
    }

    init {
        savedStateHandle.get<Character>("character")?.let {
            setCharacter(it)
            fetchComics(it.id)
            getCharacterFromFavorites(it.id)
        }
    }

    private fun setCharacter(character: Character) {
        _character.value = character
    }

    fun fetchComics(id: Int) {
        _id.value = id
    }

    fun addToFavorites(character: Character) {
        viewModelScope.launch {
            charactersRepo.upsert(character)
        }
    }

    fun remoteFromFavorites(character: Character) {
        viewModelScope.launch {
            charactersRepo.delete(character)
        }
    }

    private fun getCharacterFromFavorites(id: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _isCharacterInFavorites.value = Event(charactersRepo.character(id)?.id == id)
        }
    }
}
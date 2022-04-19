package com.example.pathchallenge.details.view

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.CompoundButton
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.pathchallenge.characters.adapter.ComicsAdapter
import com.example.pathchallenge.characters.databinding.FragmentCharacterDetailBinding
import com.example.pathchallenge.common.BaseFragment
import com.example.pathchallenge.common.exts.load
import com.example.pathchallenge.common.exts.showSnack
import com.example.pathchallenge.core.domain.model.characters.Character
import com.example.pathchallenge.core.domain.model.comics.Comic
import com.example.pathchallenge.core.remote.util.Resource
import com.example.pathchallenge.details.viewModel.DetailsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment :
    BaseFragment<FragmentCharacterDetailBinding>(FragmentCharacterDetailBinding::inflate),
    CompoundButton.OnCheckedChangeListener {

    private val mViewModel by viewModels<DetailsFragmentViewModel>()

    @Inject
    lateinit var circularProgressDrawable: CircularProgressDrawable

    @Inject
    lateinit var mAdapter: ComicsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initButtons()
    }


    private fun subscribeObservers() {
        mViewModel.character.observe(viewLifecycleOwner) {
            bindCharacter(it)
        }
        mViewModel.comics.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    showSnack(it.error!!)
                    showComicsRetryButton(true)
                }
                is Resource.Loading -> {
                    showComicsProgress(true)
                    showComicsRetryButton(false)
                }
                is Resource.Success -> {
                    val show = !it.data.isNullOrEmpty()
                    showComicList(show)
                    if (show) {
                        bindComics(it.data!!)
                    }
                }
            }
        }
        mViewModel.isCharacterInFavorites.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { characterInDb ->
                if (characterInDb) {
                    binding.favButton.setOnCheckedChangeListener(null)
                    binding.favButton.isChecked = true
                    binding.favButton.setOnCheckedChangeListener(this)
                }else{
                    binding.favButton.setOnCheckedChangeListener(this)
                }
            }
        }
    }

    private fun showComicsProgress(show: Boolean) {
        binding.comicsProgress.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun initButtons() {
        binding.retryCommicButton.setOnClickListener {
            mViewModel.fetchComics(mViewModel.character.value!!.id)
        }
    }

    private fun showComicList(show: Boolean) {
        binding.comicsSection.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun bindComics(data: List<Comic>) {
        showComicsProgress(false)
        showComicsRetryButton(false)
        binding.comicsRv.apply {
            adapter = mAdapter
            setHasFixedSize(true)
        }
        mAdapter.submitList(data)
    }

    private fun showComicsRetryButton(show: Boolean) {
        binding.retryCommicButton.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun bindCharacter(character: Character) {
        binding.apply {
            characterImage.load(character.imageUrl, circularProgressDrawable)
            characterName.text = character.name
            characterDescription.text = character.description
        }
    }

    override fun onCheckedChanged(p0: CompoundButton?, isChecked: Boolean) {
        val scaleAnimation = ScaleAnimation(
            0.7f,
            1.0f,
            0.7f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.7f,
            Animation.RELATIVE_TO_SELF,
            0.7f
        )
        scaleAnimation.duration = 500
        val bounceInterpolator = BounceInterpolator()
        scaleAnimation.interpolator = bounceInterpolator

        if (isChecked) {
            mViewModel.addToFavorites(mViewModel.character.value!!)
        } else {
            mViewModel.remoteFromFavorites(mViewModel.character.value!!)
        }

        p0?.startAnimation(scaleAnimation)
    }
}
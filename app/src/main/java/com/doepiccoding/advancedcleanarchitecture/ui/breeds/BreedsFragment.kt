package com.doepiccoding.advancedcleanarchitecture.ui.breeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.doepiccoding.advancedcleanarchitecture.R
import com.doepiccoding.advancedcleanarchitecture.ui.breeds.screen_state.BreedsScreenState
import com.doepiccoding.advancedcleanarchitecture.databinding.FragmentBreedsBinding
import com.doepiccoding.domain.entity.CatBreed
import com.doepiccoding.domain.entity.action.error.ErrorEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedsFragment : Fragment() {

    private var _binding: FragmentBreedsBinding? = null
    private val binding get() = _binding!!
    private val breedsViewModel: BreedsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        FragmentBreedsBinding.inflate(inflater, container, false).let {
            _binding = it
            it.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        //TODO: Further setup steps depending on views being available...
    }

    private fun setupViewModel() {
        breedsViewModel.breeds.observe(viewLifecycleOwner) { screenState ->
            when(screenState) {
                is BreedsScreenState.OnBreedsLoaded -> handleBreedsLoaded(screenState.breeds)
                is BreedsScreenState.OnError -> handleErrorFetchingBreeds(screenState.error)
                is BreedsScreenState.OnLoading -> binding.progress.isVisible = screenState.isLoading
            }
        }

        breedsViewModel.fetchBreeds()
    }

    private fun handleBreedsLoaded(breeds: List<CatBreed>) {
        binding.resultText.text = getString(R.string.breeds_found_number, breeds.size)
    }

    private fun handleErrorFetchingBreeds(error: ErrorEntity) {
        binding.resultText.text = getMessageFromError(error)
    }

    private fun getMessageFromError(error: ErrorEntity) =
        when(error) {
            ErrorEntity.EmptyResponseError -> getString(R.string.error_no_response)
            is ErrorEntity.NetworkError -> getString(R.string.error_http_code, error.httpStatus)
            is ErrorEntity.UnknownError -> getString(R.string.error_unknown, error.exception.message)
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
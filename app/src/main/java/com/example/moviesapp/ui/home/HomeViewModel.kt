package com.example.moviesapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.NetworkResult
import com.example.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val _viewState = MutableStateFlow(HomeState())
    val viewState: StateFlow<HomeState> get() = _viewState

    fun getOnPlayingMovies() {
        viewModelScope.launch {
            val result = moviesRepository.getMoviesOnPlaying()
            _viewState.value = when (result) {
                is NetworkResult.Success -> _viewState.value.copy(onPlayingMovies = result.data)
                is NetworkResult.Error -> _viewState.value.copy(error = true)
            }
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            val result = moviesRepository.getPopularMovies()
            _viewState.value = when (result) {
                is NetworkResult.Success -> _viewState.value.copy(popularMovies = result.data)
                is NetworkResult.Error -> _viewState.value.copy(error = true)
            }
        }
    }

}


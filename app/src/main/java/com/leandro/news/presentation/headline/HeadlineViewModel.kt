package com.leandro.news.presentation.headline

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leandro.news.base.Resource
import com.leandro.news.domain.repository.HeadlineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeadlineViewModel @Inject constructor(
    private val repository: HeadlineRepository
) : ViewModel(){

    private val _uiState = mutableStateOf(HeadlineUiState())
    val uiState : State<HeadlineUiState> = _uiState

    fun getHeadline(){
        viewModelScope.launch {
            repository.getTopHeadlines().collectLatest { response ->
                when(response) {
                    is Resource.Loading -> {
                        if(!response.isLoading){
                            _uiState.value = uiState.value.copy(isRefreshing = false)
                        }
                        _uiState.value = uiState.value.copy(isLoading = response.isLoading)
                    }
                    is Resource.Error -> {
                        _uiState.value = uiState.value.copy(errorMessage = response.errorMessage)
                    }
                    is Resource.Success -> {
                        response.data?.let { articles ->
                            _uiState.value = uiState.value.copy(articles = articles)
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: HeadlineEvent) {
        when(event){
            is HeadlineEvent.Refresh -> {
                _uiState.value = uiState.value.copy(isRefreshing = true)
                getHeadline()
            }
        }
    }

}
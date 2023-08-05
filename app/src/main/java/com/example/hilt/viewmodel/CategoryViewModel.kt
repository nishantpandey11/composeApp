package com.example.hilt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilt.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val tweetRepository: TweetRepository) :
    ViewModel() {

    val category: StateFlow<List<String>>
        get() = tweetRepository.categories

    init {
        viewModelScope.launch {
            tweetRepository.getCategories()
        }
    }

}
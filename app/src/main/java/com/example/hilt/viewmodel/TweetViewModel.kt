package com.example.hilt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilt.model.Tweet
import com.example.hilt.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetViewModel @Inject constructor(private val tweetRepository: TweetRepository) :
    ViewModel() {

    val tweets: StateFlow<List<Tweet>>
        get() = tweetRepository.tweets


    fun getTweets(category: String) {
        viewModelScope.launch {
            tweetRepository.getTweets(category)
        }
    }
}
package com.example.hilt.repository

import com.example.hilt.api.TweetApi
import com.example.hilt.model.Tweet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val api: TweetApi) {
    private var _category = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _category

    private var _tweet = MutableStateFlow<List<Tweet>>(emptyList())
    val tweets: StateFlow<List<Tweet>>
        get() = _tweet

    suspend fun getCategories() {
        val response = api.getCategories()
        if (response.isSuccessful && response.body() != null) {
            _category.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category: String) {
        val response = api.getTweets("tweets[?(@.category==\"$category\")]")
        if (response.isSuccessful && response.body() != null) {
            _tweet.emit(response.body()!!)
        }
    }
}
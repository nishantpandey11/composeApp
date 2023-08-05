package com.example.hilt.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hilt.model.Tweet
import com.example.hilt.viewmodel.TweetViewModel


@Composable
fun TweetListScreen(navController: NavController,category:String) {
    BackHandler() {
        navController.navigate("category")
    }
    val detailViewModel: TweetViewModel = hiltViewModel()
    detailViewModel.getTweets(category)

    val tweets: State<List<Tweet>> = detailViewModel.tweets.collectAsState()
    LazyColumn(content = {
        items(tweets.value) {
            TweetListItem(tweet = it.text)
        }
    })

}

@Composable
fun TweetListItem(tweet: String) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            //.clickable { onclick(quote) }
            .padding(16.dp,8.dp)
    ) {
        Text(
            text = tweet,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(8.dp)
        )
    }

}
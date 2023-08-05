package com.example.hilt.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.hilt.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(navController: NavController) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val category: State<List<String>> = categoryViewModel.category.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), contentPadding = PaddingValues(6.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        items(category.value.distinct()) {
            CategoryItem(category = it,navController)
        }
    }
}

@Composable
fun CategoryItem(category: String,navController: NavController) {
    Box(
        modifier = Modifier.clickable {
            navController.navigate("detail/$category")
        }
            .padding(8.dp)
            .size(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.Black), Alignment.Center
    ) {
        Text(text = category, style = MaterialTheme.typography.body2)

    }

}

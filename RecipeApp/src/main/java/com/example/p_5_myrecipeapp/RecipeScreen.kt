package com.example.p_5_myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter


@Composable
fun RecipeScreen(modifier:Modifier = Modifier,
                 viewstate: MainViewModel.RecipeState,
                 navigateToDetail: (Category) -> Unit
                 ){
    val recipeViewModel: MainViewModel= viewModel()
    Box(modifier = Modifier.fillMaxSize()){

        when{
             viewstate.loading->{
               CircularProgressIndicator(progress = 0.89f, modifier.align(Alignment.Center))//This is another way of doing
            }
            viewstate.error != null->{
                Text("ERROR OCCURRED")
            }
            else->{
                //Display Categories
                CategoryScreen(categories = viewstate.list,navigateToDetail)
            }
        }
    }
}


@Composable
fun CategoryScreen(categories: List<Category>,
                   navigateToDetail: (Category) -> Unit
                   ){
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()){
        //the lazy column should have a content and the content will be our list of items
        items(categories){
            category ->
            CategoryItem(category = category,navigateToDetail)

        }

    }
}

//How each item looks like
@Composable
fun CategoryItem(category: Category,navigateToDetail: (Category) -> Unit){
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize()
        .clickable {  navigateToDetail(category)},
        //We want our item to be clickable
        //The cool thing is composable have property called clickable
        horizontalAlignment = Alignment.CenterHorizontally) {
        
        Image(painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
            contentDescription =null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)//means it will be as wide and high it could be
        )

        Text(text=category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
            )
    }

}
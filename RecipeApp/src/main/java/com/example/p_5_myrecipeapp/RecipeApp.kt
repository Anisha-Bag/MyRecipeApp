package com.example.p_5_myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

//It wil take care of the App navigation

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel :MainViewModel= viewModel()
    val viewstate by recipeViewModel.categoriesState

   NavHost(navController = navController, startDestination = Screen.RecipeScreen.route )//Instead of route="recipescreen we are doing this to avoid running into errors.
   {
        //This navHostController now can take care of the 2 different Composable, we have the composable for recipe screen and for detailed screen
        composable(route= Screen.RecipeScreen.route){
            //What the composable screen should be displayed should be written here
            //In our case its the recipe screen
            RecipeScreen(viewstate= viewstate,navigateToDetail = {

                //This is responsible for passing data from the current screen to the detail screen.
                //It utilises the savedStateHandle, which is a component of thee Compose navigation framework,
                //All of the flow is stored inside of this BackStackEntry of going to detailed screen and so on.
                //set("cat",it)---This method sets a key value pair in the savedStateHandle
                //The key of the key value pair is key cat and value it which is the category that we get from the up here.
                //The key is used as a reference to retrieve the data on the detailScreen
                //This is basically saying here we are storing the data and from detail screen we are retrieving the data

                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }


       //Setting up the detailScreen
       composable(route=Screen.DetailScreen.route){
           val category= navController.previousBackStackEntry?.savedStateHandle?.
           get<Category>("cat") ?: Category("","","","")
           CategoryDetailScreen(category = category)
       }

   }
}
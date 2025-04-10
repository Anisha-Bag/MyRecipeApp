package com.example.p_5_myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {

    private val _categorieState= mutableStateOf(RecipeState())//If we create our own state we need to add ()
    val categoriesState: State<RecipeState> = _categorieState//it basically means we want our user interface updated whenever we change the state of our recipe state


    init {
        fetchCategories()
    }

    private fun fetchCategories(){
        //a view model scope provides a lounge for suspend functions to be processed
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categorieState.value= _categorieState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null

                )
            }
            catch (e: Exception){
                _categorieState.value = _categorieState.value.copy(
                    loading = false,
                    error="Error fetching categories ${e.message}"
                )

            }
        }
    }

    //dataclass will take care of the state of the recipe
    data class RecipeState(
        val loading: Boolean=true,
        val list: List<Category> = emptyList(),
        val  error:String ?= null//for  a variable to be null we should put?
    )



}
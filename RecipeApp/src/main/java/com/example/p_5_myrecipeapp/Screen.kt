package com.example.p_5_myrecipeapp


//This class Screen will take care of individual roots
//sealed keyword is used when it is known in advance that a type will confront to one of the subclass types
//So sealed class is ensured type safety by restricting types to be matched at compile time rather than runtime
//Therefore we actually don't run into the problem where we write the root name incorrectly accidentally when we want to call the root or pass the root something like that
//That's why we want to have the sealed class as we want to have different roots directing in here
sealed class Screen(val route:String){
    object RecipeScreen:Screen("recipescreen")
    object DetailScreen:Screen("detailscreen")



}
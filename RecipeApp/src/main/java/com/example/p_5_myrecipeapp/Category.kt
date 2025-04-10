package com.example.p_5_myrecipeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Data class from json format from meal db for recipes
@Parcelize
data class Category(val idCategory:String,
    val strCategory:String,
    val strCategoryThumb:String,
    val strCategoryDescription:String): Parcelable

//We require a list
data class CategoriesResponse(val categories: List<Category>)

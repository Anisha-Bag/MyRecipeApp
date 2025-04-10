package com.example.p_5_myrecipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//this retrofit builder is responsible for preparing the end point and adding the gson converter
//it then provides the create method for gaining access to the service methods for example our getCategories
private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

//This basically says we want to have the service that allows us to get this categories.php file which then we will convert into json objects
val recipeService =  retrofit.create(ApiService::class.java)



interface ApiService {

    //in order to get data we are calling a website, we need to use the Get keyword
    //GET allows us to da a http request
    //This category.php is coming from mealdb,that was a php file
    //this get keyword is used to specify the type of request that should be made to a particular url
    @GET("categories.php")
    suspend fun getCategories():CategoriesResponse
}
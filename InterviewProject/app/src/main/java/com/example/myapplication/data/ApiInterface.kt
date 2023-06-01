package com.example.myapplication.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("51eZ6Vcr")
    fun getData(): Call<BookList>
}
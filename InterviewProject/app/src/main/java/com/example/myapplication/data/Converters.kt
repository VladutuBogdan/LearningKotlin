package com.example.myapplication.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromListToString(list: List<String>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringToList(json: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun fromImageToString(image: Image?): String? {
        return Gson().toJson(image)
    }

    @TypeConverter
    fun fromStringToImage(json: String?): Image? {
        return Gson().fromJson(json, Image::class.java)
    }
}

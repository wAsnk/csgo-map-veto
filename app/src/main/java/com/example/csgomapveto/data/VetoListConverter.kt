package com.example.csgomapveto.data

import androidx.room.TypeConverter
import com.google.gson.Gson

class VetoListConverter {

    @TypeConverter
    fun listToJson(value: List<Veto>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Veto>::class.java).toList()

}
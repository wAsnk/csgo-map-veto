package com.example.csgomapveto.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "teams_table", indices = [Index(value = ["id"],
    unique = true)])
data class Team (

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo
    var Name: String = ""

    /*@ColumnInfo(name = "LogoName")
    var Logo: String = "",

    @ColumnInfo(name = "FlagName")
    var Flag: String = ""*/
){
    override fun toString(): String {
        return Name
    }
}
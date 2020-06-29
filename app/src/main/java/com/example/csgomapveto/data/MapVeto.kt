package com.example.csgomapveto.data

import androidx.lifecycle.ViewModelProviders
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.example.csgomapveto.MainActivityViewModel

@Entity(tableName = "mapVetos_table")
@TypeConverters(VetoListConverter::class)
data class MapVeto (


    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "Name")
    var Name : String,

    @ColumnInfo(name = "Team1")
    var Team1 : Long,

    @ColumnInfo(name = "Team2")
    var Team2 : Long,

    /*@ColumnInfo(name = "TimeStamp")
    var TimeStamp: String = "",*/

    var VetoList: List<Veto>
) {

    override fun toString(): String {
        return Name + " " + Team1.toString() + " " + Team2.toString()
    }
}
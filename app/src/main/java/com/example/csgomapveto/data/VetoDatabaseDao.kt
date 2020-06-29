package com.example.csgomapveto.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VetoDatabaseDao {

    @Insert
    fun insertTeam(team: Team)

    @Insert
    fun insertMapVeto(mapVeto: MapVeto)

    @Query("SELECT * FROM teams_table ORDER BY Name")
    fun getAllTeamsAsList(): List<Team>

    @Query("SELECT * FROM teams_table ORDER BY id DESC LIMIT 2")
    fun getLastTwoTeamsAsList(): List<Team>

    @Query("SELECT * FROM teams_table ORDER BY id DESC LIMIT 2")
    fun getLastTwoTeams(): LiveData<List<Team>>

    @Query("SELECT * FROM teams_table ORDER BY Name")
    fun getAllTeams(): LiveData<List<Team>>

    @Query("SELECT * FROM mapVetos_table ORDER BY id")
    fun getAllMapVetos(): LiveData<List<MapVeto>>

    @Query("SELECT * FROM mapVetos_table ORDER BY id DESC LIMIT 1")
    fun getLastMapVeto(): MapVeto?



}
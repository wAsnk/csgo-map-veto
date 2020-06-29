package com.example.csgomapveto.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MapVetoRepository(private  val database: VetoDatabaseDao) {

    val mapVetos=database.getAllMapVetos()

    val teams=database.getAllTeams()

    val lastTwoTeams = database.getLastTwoTeams()

    suspend fun insertMapveto(mapveto: MapVeto) {
        withContext(Dispatchers.IO) {
            database.insertMapVeto(mapveto)
        }
    }

    suspend fun insertTeam(team: Team) {
        withContext(Dispatchers.IO) {
            database.insertTeam(team)
        }
    }

    fun getLastTwoTeamsAsList() : List<Team>{
        return database.getLastTwoTeamsAsList()
    }
}
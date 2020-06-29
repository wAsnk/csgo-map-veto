package com.example.csgomapveto

import android.app.Application
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.csgomapveto.data.MapVeto
import com.example.csgomapveto.data.MapVetoRepository
import com.example.csgomapveto.data.Team
import com.example.csgomapveto.data.VetoDatabase

class MainActivityViewModel(application: Application) : AndroidViewModel(application), AdapterView.OnItemSelectedListener {
    val repository : MapVetoRepository
    var mapVetos : LiveData<List<MapVeto>>
    var teams : LiveData<List<Team>>
    var lastTwoTeams : LiveData<List<Team>>

    lateinit var selectedMapVeto : MapVeto
    lateinit var selectedTeam1 : Team
    lateinit var selectedTeam2 : Team
    private var teamToStart : Int = 0

    init {
        val dataSource = VetoDatabase.getInstance(application).vetoDatabaseDao
        repository= MapVetoRepository(dataSource)

        mapVetos=repository.mapVetos
        teams = repository.teams
        lastTwoTeams = repository.lastTwoTeams
    }

    fun getLastTwoTeamsAsList() : List<Team>{
        return repository.getLastTwoTeamsAsList()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        when(parent?.id){
            R.id.team1_spinner -> selectedTeam1 = parent?.getItemAtPosition(position) as Team
            R.id.team2_spinner -> selectedTeam2 = parent?.getItemAtPosition(position) as Team
            R.id.teamToStart -> {
                if ((parent?.getItemAtPosition(position) as String) == "Team1"){
                    teamToStart = 1
                }
                else{
                    teamToStart = 2
                }
            }
        }
    }
}
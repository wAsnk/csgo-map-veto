package com.example.csgomapveto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.csgomapveto.data.Team
import kotlinx.android.synthetic.main.fragment_new_veto_settings.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewVetoSettingsFragment : Fragment() {
    private lateinit var viewOfLayout : View
    lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewOfLayout = inflater.inflate(R.layout.fragment_new_veto_settings, container, false)
        viewModel = activity?.let { ViewModelProviders.of(it).get(MainActivityViewModel::class.java) }!!
        viewOfLayout.startVeto.setOnClickListener {
                view: View -> Thread{mapVetoStartInit()}.join()
                view.findNavController().navigate(NewVetoSettingsFragmentDirections.actionNewVetoSettingsFragmentToVetoRundownFragment())
        }

        initData()
        return viewOfLayout
    }

    private fun mapVetoStartInit(){
        if(viewOfLayout.editText_Team1.text.toString() != "" && viewOfLayout.editText_Team2.text.toString() != ""){
            val team1: Team = Team(Name = viewOfLayout.editText_Team1.text.toString())
            val team2: Team = Team(Name = viewOfLayout.editText_Team2.text.toString())
            GlobalScope.launch {
                viewModel.repository.insertTeam(team1)
                viewModel.repository.insertTeam(team2)
            }
            val lastTwoTeamFromRepo = viewModel.getLastTwoTeamsAsList()
            viewModel.selectedTeam1 = lastTwoTeamFromRepo[0]
            viewModel.selectedTeam2 = lastTwoTeamFromRepo[1]

        }

    }

    fun initData() {
        var adapter = ArrayAdapter<Team>(requireContext(), android.R.layout.simple_spinner_item)

        viewModel.teams.observe(viewLifecycleOwner, Observer { teams ->

            teams?.forEach {
                if(!it.Name.equals("decider"))
                adapter.add(it)
            }
        })

        var team1_spinner = viewOfLayout.team1_spinner
        team1_spinner.adapter = adapter
        team1_spinner.onItemSelectedListener = viewModel

        var team2_spinner = viewOfLayout.team2_spinner
        team2_spinner.adapter = adapter
        team2_spinner.onItemSelectedListener = viewModel

        //Init team1 or team2 select for first veto
        val TeamToStart = resources.getStringArray(R.array.TeamToStart)
        val TeamToStartSpinner = viewOfLayout.teamToStart
        if (TeamToStartSpinner != null) {
            val adapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item, TeamToStart)
            TeamToStartSpinner.adapter = adapter
            TeamToStartSpinner.onItemSelectedListener = viewModel
        }
    }

}
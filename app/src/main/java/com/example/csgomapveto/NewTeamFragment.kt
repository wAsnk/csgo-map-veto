package com.example.csgomapveto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.csgomapveto.data.Team
import kotlinx.android.synthetic.main.fragment_new_team.view.*
import kotlinx.android.synthetic.main.fragment_new_veto_settings.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewTeamFragment : Fragment() {
    private lateinit var viewOfLayout : View
    lateinit var viewModel: MainActivityViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewOfLayout = inflater.inflate(R.layout.fragment_new_team, container, false)
        viewModel = activity?.let { ViewModelProviders.of(it).get(MainActivityViewModel::class.java) }!!

        viewOfLayout.addTeam.setOnClickListener {
                view: View -> createNewTeam()
                view.findNavController().navigateUp()
        }

        return viewOfLayout
    }

    private fun createNewTeam(){
        if(viewOfLayout.editText_Team1.text.toString() != "" ){
            val team1: Team = Team(Name = viewOfLayout.editText_Team1.text.toString())
            var lastTwoTeamFromRepo = listOf<Team>()
            GlobalScope.launch {
                viewModel.repository.insertTeam(team1)
            }
        }



    }

}
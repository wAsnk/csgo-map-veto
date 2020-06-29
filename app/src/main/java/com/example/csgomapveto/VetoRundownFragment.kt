package com.example.csgomapveto

import android.graphics.drawable.ColorDrawable
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.csgomapveto.data.*
import com.example.csgomapveto.data.Map
import kotlinx.android.synthetic.main.fragment_new_veto_settings.*
import kotlinx.android.synthetic.main.fragment_veto_rundown.*
import kotlinx.android.synthetic.main.fragment_veto_rundown.view.*
import kotlinx.android.synthetic.main.fragment_veto_rundown.view.goToResultBtn
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule

class VetoRundownFragment : Fragment(), View.OnClickListener {
    lateinit var viewModel: MainActivityViewModel
    lateinit var viewOfLayout : View
    private val mapButtonList = mutableListOf<Button>()
    var vetoTeams = mutableListOf<Team>()
    private var actualTeam : Int = 0
    private lateinit var actualMap : Map
    private lateinit var currentVetoType : VetoType

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewOfLayout = inflater.inflate(R.layout.fragment_veto_rundown, container, false)
        viewOfLayout.goToResultBtn.setOnClickListener(
            Navigation.createNavigateOnClickListener(VetoRundownFragmentDirections.actionVetoRundownFragmentToVetoResultFragment())
        )
        viewModel = activity?.let { ViewModelProviders.of(it).get(MainActivityViewModel::class.java) }!!
        viewOfLayout.versusTeams.setText(viewModel.selectedTeam1.Name + " vs. " + viewModel.selectedTeam2.Name )

        InitOnClickAndCreateList()

        return viewOfLayout
    }

    fun InitOnClickAndCreateList(){
        mapButtonList.add(viewOfLayout.de_dust2)
        mapButtonList.add(viewOfLayout.de_inferno)
        mapButtonList.add(viewOfLayout.de_mirage)
        mapButtonList.add(viewOfLayout.de_nuke)
        mapButtonList.add(viewOfLayout.de_overpass)
        mapButtonList.add(viewOfLayout.de_train)
        mapButtonList.add(viewOfLayout.de_vertigo)

        mapButtonList.forEach {
            it.setOnClickListener(this)
        }
        //Setup the team list for veto order
        if (viewModel.teamToStart == 1){
            vetoTeams.add(viewModel.selectedTeam1)
            vetoTeams.add(viewModel.selectedTeam2)
        }
        else{
            vetoTeams.add(viewModel.selectedTeam2)
            vetoTeams.add(viewModel.selectedTeam1)
        }

        viewModel.newMapveto = MapVeto(Name = viewOfLayout.versusTeams.text.toString(), Team1 = viewModel.selectedTeam1.Name, Team2 = viewModel.selectedTeam2.Name, VetoList = mutableListOf())
        viewModel.vetoNumber = 1
        viewOfLayout.goToResultBtn.visibility = View.GONE
    }
    override fun onClick(v: View?) {
        actualTeam = (viewModel.vetoNumber - 1) % 2


        var btn = v?.id?.let { viewOfLayout.findViewById<Button>(it) }

        //Set on click to null so the button couldn't be pushed again
        btn?.setOnClickListener(null)

        if (viewModel.vetoNumber <= viewModel.banNumer) {
            currentVetoType = VetoType.BAN
            btn?.setBackgroundColor(resources.getColor(R.color.BanBGColor))
        }
        else if (viewModel.vetoNumber > viewModel.banNumer && viewModel.vetoNumber != viewModel.numberOfMaps) {
            currentVetoType = VetoType.PICK
            btn?.setBackgroundColor(resources.getColor(R.color.PickBGColor))
        }

        actualMap = enumValueOf<Map>(btn?.text.toString())
        viewModel.newMapveto.VetoList.add(Veto(Number = viewModel.vetoNumber, Team = vetoTeams[actualTeam], Map = actualMap, Type = currentVetoType))
        viewModel.vetoNumber++

        if (viewModel.vetoNumber == viewModel.numberOfMaps){
            currentVetoType = VetoType.DECIDER
            mapButtonList.forEach {
                if (it.background !is ColorDrawable){
                    it.setBackgroundColor(resources.getColor(R.color.DeciderBGColor))
                    actualMap = enumValueOf<Map>(it.text.toString())
                    viewModel.newMapveto.VetoList.add(Veto(Number = viewModel.vetoNumber, Team = Team(Name = "decider"), Map = actualMap, Type = currentVetoType))
                }
            }
            GlobalScope.launch {viewModel.repository.insertMapveto(viewModel.newMapveto)}
            viewOfLayout.goToResultBtn.visibility = View.VISIBLE

        }
    }


}




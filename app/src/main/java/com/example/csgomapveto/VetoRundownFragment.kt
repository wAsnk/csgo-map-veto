package com.example.csgomapveto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_new_veto_settings.view.*
import kotlinx.android.synthetic.main.fragment_veto_rundown.view.*

class VetoRundownFragment : Fragment() {
    lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewOfLayout = inflater.inflate(R.layout.fragment_veto_rundown, container, false)
        viewOfLayout.goToResultBtn.setOnClickListener(
            Navigation.createNavigateOnClickListener(VetoRundownFragmentDirections.actionVetoRundownFragmentToVetoResultFragment())
        )
        viewModel = activity?.let { ViewModelProviders.of(it).get(MainActivityViewModel::class.java) }!!
        viewOfLayout.versusTeams.setText(viewModel.selectedTeam1.Name + " vs. " + viewModel.selectedTeam2.Name )
        return viewOfLayout
    }
}
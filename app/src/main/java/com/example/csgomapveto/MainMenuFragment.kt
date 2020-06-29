package com.example.csgomapveto

import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.fragment_main_menu.view.*

class MainMenuFragment : Fragment() {
    private lateinit var viewOfLayout: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val viewOfLayout = inflater.inflate(R.layout.fragment_main_menu, container, false)
        viewOfLayout.NewVetoButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(MainMenuFragmentDirections.actionMainMenuFragmentToNewVetoSettingsFragment())
        )
        viewOfLayout.NewTeamButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(MainMenuFragmentDirections.actionMainMenuFragmentToNewTeamFragment())
        )
        viewOfLayout.OldVetoButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(MainMenuFragmentDirections.actionMainMenuFragmentToMapVetoHistoryFragment())
        )

        setHasOptionsMenu(true)
        return viewOfLayout
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return NavigationUI.onNavDestinationSelected(item!!,
            requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}
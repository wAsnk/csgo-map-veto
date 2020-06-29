package com.example.csgomapveto

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.csgomapveto.data.VetoType
import kotlinx.android.synthetic.main.fragment_main_menu.view.*
import kotlinx.android.synthetic.main.fragment_veto_result.*
import kotlinx.android.synthetic.main.fragment_veto_result.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VetoResultFragment : Fragment() {
    private lateinit var viewOfLayout : View
    lateinit var viewModel: MainActivityViewModel

    //var lastMapVeto = GlobalScope.launch { viewModel.repository.getLastMapVeto() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewOfLayout = inflater.inflate(R.layout.fragment_veto_result, container, false)
        viewModel = activity?.let { ViewModelProviders.of(it).get(MainActivityViewModel::class.java) }!!


        viewModel.newMapveto.VetoList.forEach {
            val textV = TextView(context)
            textV.textSize = 20f
            if (it.Type == VetoType.DECIDER){
                textV.text = it.Type.toString() + " " + it.Map.toString()
            }
            else {
                textV.text = it.Team.Name + " " + it.Type.toString() + " " + it.Map.toString()
            }
            textV.gravity = Gravity.CENTER

            viewOfLayout.resultLayout.addView(textV)
        }

        viewOfLayout.BackToMainMenu.setOnClickListener(
            Navigation.createNavigateOnClickListener(VetoResultFragmentDirections.actionVetoResultFragmentToMainMenuFragment())
        )


        return viewOfLayout
    }

}
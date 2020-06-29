package com.example.csgomapveto

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.VERTICAL
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.csgomapveto.data.MapVeto
import com.example.csgomapveto.data.Veto
import com.example.csgomapveto.data.VetoType
import kotlinx.android.synthetic.main.fragment_map_veto_history.view.*
import kotlinx.android.synthetic.main.fragment_new_team.view.*
import kotlinx.android.synthetic.main.fragment_veto_result.view.*

class MapVetoHistoryFragment : Fragment() {
    private lateinit var viewOfLayout : View
    lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewOfLayout = inflater.inflate(R.layout.fragment_map_veto_history, container, false)
        viewModel = activity?.let { ViewModelProviders.of(it).get(MainActivityViewModel::class.java) }!!

        FillScrollView()

        return viewOfLayout

        //scrollLinearLayout
    }

    fun FillScrollView(){

        var mapVetoList = mutableListOf<MapVeto>()
        viewModel.mapVetos.observe(viewLifecycleOwner, Observer { mapVetos ->
            mapVetos?.forEach {
                mapVetoList.add(it)
            }
        })


        mapVetoList.forEach {
            val hLayout = LinearLayout(context)
            hLayout.orientation = VERTICAL
            viewOfLayout.scrollLinearLayout.addView(hLayout)


            val team1 = TextView(context)
            team1.textSize = 25f
            team1.text = it.Team1
            hLayout.addView(team1)

            val vs = TextView(context)
            vs.textSize = 15f
            vs.text = " vs. "
            hLayout.addView(vs)

            val team2 = TextView(context)
            team2.textSize = 25f
            team2.text = it.Team1
            hLayout.addView(team2)

            val hLayout2 = LinearLayout(context)
            hLayout2.orientation = VERTICAL
            viewOfLayout.scrollLinearLayout.addView(hLayout2)

            addVeto(hLayout2,it.VetoList)
        }
    }

    private fun addVeto(layout : LinearLayout, vetoList : MutableList<Veto>){
        vetoList.forEach {
            val textV = TextView(context)
            textV.textSize = 20f

            if (it.Type == VetoType.DECIDER) {
                textV.text = it.Type.toString() + " " + it.Map.toString()
            } else {
                textV.text = it.Team.Name + " " + it.Type.toString() + " " + it.Map.toString()
            }
            textV.gravity = Gravity.CENTER
            layout.addView(textV)
        }
    }


}
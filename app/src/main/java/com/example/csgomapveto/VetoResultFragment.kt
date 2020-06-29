package com.example.csgomapveto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProviders

class VetoResultFragment : Fragment() {

    lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = activity?.let { ViewModelProviders.of(it).get(MainActivityViewModel::class.java) }!!
        return inflater.inflate(R.layout.fragment_veto_result, container, false)
    }

}
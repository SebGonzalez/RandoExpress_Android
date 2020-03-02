package com.example.randoexpress.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.randoexpress.R
import com.example.randoexpress.viewmodels.RandoListViewModel

class HomeFragment : Fragment() {

    private lateinit var randoViewModel: RandoListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        randoViewModel = ViewModelProviders.of(requireActivity()).get(RandoListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView : RecyclerView = root.findViewById(R.id.rando_list)
        recyclerView.adapter = RandoListAdapter(ArrayList())
        randoViewModel.randoList.observe(viewLifecycleOwner, Observer { list ->
            val adapter = RandoListAdapter(list)
            recyclerView.adapter = adapter
        })
        return root
    }


}
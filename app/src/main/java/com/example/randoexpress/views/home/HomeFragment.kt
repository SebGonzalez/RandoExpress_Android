package com.example.randoexpress.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.randoexpress.R
import com.example.randoexpress.viewmodels.RandoListViewModel

class HomeFragment : Fragment() {
    private val randoViewModel: RandoListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView : RecyclerView = root.findViewById(R.id.rando_list)
        recyclerView.adapter = RandoListAdapter(ArrayList())
        randoViewModel.getRandoList.observe(viewLifecycleOwner, Observer { list ->
            val adapter = RandoListAdapter(list)
            recyclerView.adapter = adapter
        })
        return root
    }


}
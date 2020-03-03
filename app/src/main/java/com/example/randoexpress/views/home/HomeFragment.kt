package com.example.randoexpress.views.home

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.randoexpress.R
import com.example.randoexpress.viewmodels.RandoListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {
    private val randoViewModel: RandoListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView : RecyclerView = root.findViewById(R.id.rando_list)
        val searchFab: FloatingActionButton = root.findViewById(R.id.search_fab)
        val searchBox: EditText = root.findViewById(R.id.search_box)
        searchFab.setOnClickListener {
            if(searchBox.isGone)
                searchBox.visibility = View.VISIBLE
            else
                searchBox.visibility = View.GONE
        }
        recyclerView.adapter = RandoListAdapter(ArrayList())
        randoViewModel.getRandoList.observe(viewLifecycleOwner, Observer { list ->
            val adapter = RandoListAdapter(list)
            recyclerView.adapter = adapter
        })
        return root
    }


}
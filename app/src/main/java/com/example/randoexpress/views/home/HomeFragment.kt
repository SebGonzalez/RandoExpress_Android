package com.example.randoexpress.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.randoexpress.R
import com.example.randoexpress.viewmodels.RandoViewModel

class HomeFragment : Fragment() {

    val data: ArrayList<String> = ArrayList()
    private lateinit var randoViewModel: RandoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        randoViewModel = ViewModelProviders.of(this).get(RandoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        mockData()
        val adapter = RandoListAdapter(data)
        val recyclerView : RecyclerView = root.findViewById(R.id.rando_list)
        recyclerView.adapter = adapter
        return root
    }

    fun mockData(){
        data.add("First rando")
        data.add("Second rando")
        data.add("Third rando")
        data.add("Another rando")
        data.add("First rando")
        data.add("Second rando")
        data.add("Third rando")
        data.add("Another rando")
        data.add("First rando")
        data.add("Second rando")
        data.add("Third rando")
        data.add("Another rando")
    }


}
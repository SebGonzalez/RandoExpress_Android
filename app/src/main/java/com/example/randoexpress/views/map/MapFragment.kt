package com.example.randoexpress.views.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.randoexpress.R
import com.example.randoexpress.viewmodels.RandoViewModel

class MapFragment : Fragment() {

    private lateinit var mapViewModel: RandoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mapViewModel =
            ViewModelProviders.of(this).get(RandoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_map, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        mapViewModel.text.observe(this, Observer {
            textView.text = it+" Map Fragment"
        })
        return root
    }
}
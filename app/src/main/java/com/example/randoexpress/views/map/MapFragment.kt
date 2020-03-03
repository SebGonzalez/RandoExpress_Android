package com.example.randoexpress.views.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.randoexpress.R
import com.example.randoexpress.viewmodels.RandoListViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.fragment_map.*

class MapFragment : Fragment(), OnMapReadyCallback {


    private lateinit var googleMap: GoogleMap
    private val randoViewModel: RandoListViewModel by activityViewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        map_view.onCreate(savedInstanceState)
        map_view.onResume()
        map_view.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            googleMap = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_map, container, false)
        randoViewModel.getRandoList.observe(viewLifecycleOwner, Observer { list ->
            // list is an ArrayList of Model.Rando
            // You can display randos on map from here
        })
        return root
    }
}
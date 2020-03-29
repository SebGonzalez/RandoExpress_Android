package com.example.randoexpress.views.createrando

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.randoexpress.R
import com.example.randoexpress.model.Model
import com.example.randoexpress.viewmodels.CreateRandoViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_create_rando.*


class CreateRandoFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    private lateinit var createRandoViewModel: CreateRandoViewModel
    private lateinit var googleMap: GoogleMap
    private lateinit var markerPosition: LatLng

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_rando, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        create_rando_map_view.onCreate(savedInstanceState)
        create_rando_map_view.onResume()
        create_rando_map_view.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            googleMap = it
            googleMap.setOnMarkerDragListener(this)
            googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    context,
                    R.raw.map_style
                )
            )
            markerPosition = LatLng(46.1305, 2.823)
            googleMap.addMarker(
                MarkerOptions()
                    .position(markerPosition)
                    .draggable(true)
            )
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerPosition, 3f));
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val createButton: Button = view.findViewById(R.id.signup_button)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val jwt = sharedPref!!.getString("jwt", "none") as String
        createButton.setOnClickListener {
            val newRando: Model.Rando = buildRando(view)
            createRandoViewModel = ViewModelProvider(this).get(CreateRandoViewModel::class.java)
            createRandoViewModel.jwt = jwt
            createRandoViewModel.rando = newRando
            //createRandoViewModel = CreateRandoViewModel(jwt, newRando)
            createRandoViewModel.addRando.observe(viewLifecycleOwner, Observer {
                Log.i("===>Create rando", "" + newRando)
                Log.i("===>Create rando", "" + it)
                Toast.makeText(context, "Hike successfully created!", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view)
                    .navigate(R.id.action_createRandoFragment_to_navigation_home)
            })

        }
    }

    /**
     * Builds rando object with input data
     * @param view root view
     * @return rando object
     */
    private fun buildRando(view: View): Model.Rando {
        val randoCreator: Model.User = buildUser()
        val attendees = arrayOf(randoCreator)
        val lat: Double = markerPosition.latitude
        val long: Double = markerPosition.longitude
        val location: TextInputEditText = view.findViewById(R.id.create_rando_location_name_input_text)
        val name: TextInputEditText = view.findViewById(R.id.create_rando_location_name_input_text)
        val description: TextInputEditText = view.findViewById(R.id.create_rando_description_input_text)
        val date: TextInputEditText = view.findViewById(R.id.create_rando_date_input_text)
        val time: TextInputEditText = view.findViewById(R.id.create_rando_time_input_text)
        return Model.Rando(
            ville = location.text.toString(),
            name = name.text.toString(),
            description = description.text.toString(),
            dateDepart = date.text.toString(),
            heureDepart = time.text.toString(),
            latitude = lat.toString(),
            longitude = long.toString(),
            owner = randoCreator,
            persons = attendees
        )
    }

    /**
     * Builds rando owner object
     * with data from SharedPreferences
     * @return owner object
     */
    private fun buildUser(): Model.User {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val email: String = sharedPref!!.getString("email", "none") as String
        val name: String = sharedPref.getString("name", "none") as String
        val firstName = sharedPref.getString("firstName", "none") as String
        val password = sharedPref.getString("password", "none") as String
        val id = sharedPref.getInt("id", 0) as Int
        return Model.User(
            firstName = firstName,
            name = name,
            id = id,
            mail = email,
            password = password
        )
    }

    /**
     * Records marker new position after being dragged
     * @param p0 google map marker object
     */
    override fun onMarkerDragEnd(p0: Marker?) {
        markerPosition = p0!!.position
        Log.i("====>onMarkerDragEnd", "" + markerPosition)
    }

    override fun onMarkerDragStart(p0: Marker?) {
    }

    override fun onMarkerDrag(p0: Marker?) {
    }
}

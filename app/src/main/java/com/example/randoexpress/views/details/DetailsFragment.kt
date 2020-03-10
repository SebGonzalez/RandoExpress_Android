package com.example.randoexpress.views.details


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.randoexpress.R
import com.example.randoexpress.model.Model
import com.example.randoexpress.viewmodels.RandoListViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var rando: Model.Rando
    private lateinit var googleMap: GoogleMap
    private val randoViewModel: RandoListViewModel by activityViewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        details_map_view.onCreate(savedInstanceState)
        details_map_view.onResume()
        details_map_view.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            googleMap = it
            googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style)
            );
            placeMarker()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val randoId = bundle!!.getInt("randoId")
        randoViewModel.getRandoList.observe(viewLifecycleOwner, Observer { list ->
            rando = list[randoId]
            bindData(view)
            setupAttendeesList(view)
            setOnClickListeners(view)
        })
    }

    private fun setupAttendeesList(view: View) {
        val attendeeList: ListView = view.findViewById(R.id.rando_details_attendees_list)
        val array = ArrayList<String>()
        // copying user names from list of users to string ArrayList
        rando.persons.forEach { user ->
            array.add("${user.firstName} ${user.name}")
        }
        val attendeeListAdapter: ArrayAdapter<String> =
            ArrayAdapter(context, R.layout.attendee_item, array)
        attendeeList.adapter = attendeeListAdapter
    }

    private fun bindData(view: View) {
        val randoTitle: TextView = view.findViewById(R.id.rando_details_title)
        val randoHost: TextView = view.findViewById(R.id.rando_details_host_name_value)
        val randoDescription: TextView = view.findViewById(R.id.rando_details_description)
        val randoLocation: TextView = view.findViewById(R.id.rando_item_location_value)
        val randoAttendeesNumber: TextView = view.findViewById(R.id.rando_details_attending_value)
        val randoDateTime: TextView = view.findViewById(R.id.rando_details_time_value)
        randoTitle.text = rando.name
        randoHost.text = rando.owner.firstName
        randoDescription.text = rando.description
        randoLocation.text = rando.ville
        randoAttendeesNumber.text = rando.persons.size.toString()
        randoDateTime.text = "${rando.heureDepart} ${rando.dateDepart}"
    }

    private fun setOnClickListeners(view: View) {
        val joinButton: Button = view.findViewById(R.id.rando_details_join_button)
        val showAttendeesButton: Button = view.findViewById(R.id.rando_details_attendees_button)
        val hideAttendees: ImageView = view.findViewById(R.id.rando_details_attendees_close)
        val attendeesView: CardView = view.findViewById(R.id.details_list_card_view)

        joinButton.setOnClickListener {

        }
        showAttendeesButton.setOnClickListener {
            if (attendeesView.visibility == View.VISIBLE)
                attendeesView.visibility = View.GONE
            else if (attendeesView.visibility == View.GONE)
                attendeesView.visibility = View.VISIBLE

        }
        hideAttendees.setOnClickListener {
            attendeesView.visibility = View.GONE
        }
    }


    private fun placeMarker() {
        val location = LatLng(rando.latitude.toDouble(), rando.longitude.toDouble())
        googleMap.addMarker(
            MarkerOptions()
                .position(location)
                .title(rando.name)
        )
        moveCamera(location)
    }

    private fun moveCamera(location: LatLng) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f));
    }


}

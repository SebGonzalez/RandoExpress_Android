package com.example.randoexpress.views.details


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.randoexpress.R
import com.example.randoexpress.model.Model
import com.example.randoexpress.viewmodels.RandoListViewModel
import com.example.randoexpress.viewmodels.SubscriptionViewModel
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
    private lateinit var sharedPref: SharedPreferences
    private lateinit var jwt: String
    private lateinit var email: String
    private lateinit var subscriptionViewModel: SubscriptionViewModel
    private lateinit var randoListViewModel: RandoListViewModel
    private var randoId: Int = 0
    private var randoOrigin: Int = 0


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
        getBundleData()
        getSharedPref()
        setupSubscriptionViewModel()
        setupRandoListViewModel()
        randoListViewModel.getRando.observe(viewLifecycleOwner, Observer { r ->
            rando = r
            Log.i("===>Details", "Rando=" + r)
            bindData(view)
            setupAttendeesList(view)
            setAttendeesOnClickListener(view)
            adaptActionButtonToOrigin(view)
            placeMarker()
        })
    }

    /**
     * Creating ViewModel and passing data needed to make get/post requests
     */
    private fun setupSubscriptionViewModel(){
        subscriptionViewModel = ViewModelProvider(this).get(SubscriptionViewModel::class.java)
        subscriptionViewModel.jwt = jwt
        subscriptionViewModel.id = randoId
        subscriptionViewModel.email = email
    }

    /**
     * Creating ViewModel and passing data needed to make get/post requests
     */
    private fun setupRandoListViewModel(){
        randoListViewModel = ViewModelProvider(this).get(RandoListViewModel::class.java)
        randoListViewModel.jwt = jwt
        randoListViewModel.id = randoId
    }

    /**
     * Fetch data from passed bundle
     * bundle comes from RandoListAdapter
     */
    private fun getBundleData(){
        val bundle = arguments
        randoId = bundle!!.getInt("randoId")
        randoOrigin = bundle.getInt("randoOrigin")
    }

    /**
     * Fetch shared pref data
     */
    private fun getSharedPref() {
        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        jwt = sharedPref.getString("jwt", "none") as String
        email = sharedPref.getString("email", "none") as String
    }

    /**
     * Adapts join/leave button according to the context of display
     * @param fragment root view
     */
    private fun adaptActionButtonToOrigin(view: View) {
        if (randoOrigin == R.id.action_navigation_home_to_detailsFragment) {
            setJoinOnClickListener(view)
        } else if (randoOrigin == R.id.action_navigation_dashboard_to_detailsFragment) {
            setLeaveOnClickListener(view)
        }
    }

    /**
     * Displays list of people attending the hike
     * @param fragment root view
     */
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

    /**
     * Binds hike data to the views
     * @param fragment root view
     */
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

    /**
     * Show/hide attendees list logic
     * uses both "show" and "X" button to hide list
     * @param fragment root view
     */
    private fun setAttendeesOnClickListener(view: View) {
        val showAttendeesButton: Button = view.findViewById(R.id.rando_details_attendees_button)
        val hideAttendees: ImageView = view.findViewById(R.id.rando_details_attendees_close)
        val attendeesView: CardView = view.findViewById(R.id.details_list_card_view)

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

    /**
     * Join the current hike logic
     * @param fragment root view
     */
    private fun setJoinOnClickListener(view: View) {
        val joinButton: Button = view.findViewById(R.id.rando_details_join_button)
        joinButton.setOnClickListener {
            subscriptionViewModel.subscribe.observe(viewLifecycleOwner, Observer { message ->
                Log.i("====>Details", "Subscribe")
                Toast.makeText(context, "You joined a hike!", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(it)
                    .navigate(R.id.action_detailsFragment_to_navigation_dashboard)
            })
        }
    }

    /**
     * Leave the current hike logic
     * @param fragment root view
     */
    private fun setLeaveOnClickListener(view: View) {
        val joinButton: Button = view.findViewById(R.id.rando_details_join_button)
        joinButton.text = "Leave"
        joinButton.setOnClickListener {
            subscriptionViewModel.unsubscribe.observe(viewLifecycleOwner, Observer { message ->
                Log.i("====>Details", "Unsubscription")
                Toast.makeText(context, "You left a hike!", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(it)
                    .navigate(R.id.action_detailsFragment_to_navigation_dashboard)
            })

        }
    }


    /**
     * Places current hike marker on map
     */
    private fun placeMarker() {
        val location = LatLng(rando.latitude.toDouble(), rando.longitude.toDouble())
        googleMap.addMarker(
            MarkerOptions()
                .position(location)
                .title(rando.name)
        )
        moveCamera(location)
    }

    /**
     * Centers camera on marker of current hike
     */
    private fun moveCamera(location: LatLng) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f));
    }


}

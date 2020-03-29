package com.example.randoexpress.views.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.randoexpress.R
import com.example.randoexpress.model.Model
import com.example.randoexpress.viewmodels.RandoListViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var randoListViewModel: RandoListViewModel

    private var permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    lateinit var locationManager: LocationManager
    private var locationGps: Location? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        map_view.onCreate(savedInstanceState)
        map_view.onResume()
        map_view.getMapAsync(this)

        if (!checkLocationPermission()) {
            requestPermission();
        }

    }

    override fun onMapReady(map: GoogleMap?) {

        map?.let {
            googleMap = it
            googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style)
            )
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
            val jwt: String = sharedPref.getString("jwt", "none") as String
            Log.i("====>Home fragment", "JWT:" + jwt)
            randoListViewModel = ViewModelProvider(this).get(RandoListViewModel::class.java)
            randoListViewModel.jwt = jwt
            randoListViewModel.getRandoList.observe(viewLifecycleOwner, Observer { list ->
                // iterating through list of Rando
                // and placing marker for each
                list.forEach { rando: Model.Rando ->
                    val location = LatLng(rando.latitude.toDouble(), rando.longitude.toDouble())
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(location)
                            .title(rando.name)
                    )
                }
            })
        }

        if (checkLocationPermission()) {
            // We get and display the user's location on the map if we have the permissions
            val currentLocation = getLocation()
            if (currentLocation != null) {
                val curLatLng = LatLng(getLocation()!!.latitude, getLocation()!!.longitude)
                googleMap.addMarker(
                    MarkerOptions()
                        .position(curLatLng)
                        .title("Je suis ici")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                )
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curLatLng, 12.0f))
                // We focus the map on the user's location
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // UI manipulation goes here
        // ex: view.findViewById(R.id.your_view_id)
    }

    // Check if the permission passed in parameters is granted or not
    private fun isPermissionGranted(permission: String): Boolean =
        ContextCompat.checkSelfPermission(
            this.requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED


    // Check if we have access to the locations permissions
    private fun checkLocationPermission(): Boolean {
        if (isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)
            && isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            return true;
        }
        return false;
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this.requireActivity(), permissions, 0)
    }

    @SuppressLint("MissingPermission")
    fun getLocation(): Location? {

        locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0F, object :
                LocationListener {
                override fun onLocationChanged(location: Location?) {
                    if (location != null) {
                        locationGps = location
                    }
                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
                override fun onProviderEnabled(provider: String?) {}
                override fun onProviderDisabled(provider: String?) {}
            })
            val localGpsLocation =
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

            if (localGpsLocation != null) {
                locationGps = localGpsLocation;
                return locationGps
            }
        }
        return locationGps
    }

}

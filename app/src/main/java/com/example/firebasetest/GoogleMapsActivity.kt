package com.example.firebasetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class GoogleMapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private var mGoogleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Retrieve the location data from the intent
        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)

        // The camera movement is moved to onMapReady for a more synchronous behavior
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap

        // Retrieve the location data from the intent
        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)

        // Add a marker at the specified location
        val locationLatLng = LatLng(latitude, longitude)
        mGoogleMap?.addMarker(MarkerOptions().position(locationLatLng).title("Current Location"))

        // Default location set to Tel Aviv, Israel
        val defaultLocation = LatLng(latitude, longitude)
        mGoogleMap?.addMarker(MarkerOptions().position(defaultLocation).title("Your Current Location"))
        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLng(defaultLocation))

        // Move the camera to the specified location
//        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLng(locationLatLng))
//        mGoogleMap?.animateCamera(CameraUpdateFactory.zoomTo(15.0f)) // You can adjust the zoom level as needed
    }
}

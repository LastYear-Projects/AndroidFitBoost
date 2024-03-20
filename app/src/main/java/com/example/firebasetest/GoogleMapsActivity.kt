package com.example.firebasetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore

class GoogleMapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private var mGoogleMap: GoogleMap? = null
    private lateinit var firestore: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        firestore = FirebaseFirestore.getInstance()

        // Retrieve the location data from the intent
        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap

        // Retrieve the location data from the intent
        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)
        val locationLatLng = LatLng(latitude, longitude)
        mGoogleMap?.addMarker(MarkerOptions().position(locationLatLng).title("Current Location"))


        firestore.collection("location")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val title = document.getString("title") ?: ""
                    val latitudeTemp = document.getDouble("latitude")
                    val longitudeTemp = document.getDouble("longitude")

                    if (latitudeTemp != null && longitudeTemp != null) {
                        val locationLatLngTemp = LatLng(latitudeTemp, longitudeTemp)
                        mGoogleMap?.addMarker(MarkerOptions().position(locationLatLngTemp).title(title))
                    } else {
                        Log.e("LocationError", "Latitude or Longitude is null for document with title: $title")
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("FirestoreError", "Error getting documents: ", exception)
            }

        val defaultLocation = LatLng(latitude, longitude)
        mGoogleMap?.addMarker(MarkerOptions().position(defaultLocation).title("Your Current Location"))
        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLng(defaultLocation))

    }
}

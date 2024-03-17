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

        // The camera movement is moved to onMapReady for a more synchronous behavior
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


        // Add a marker at the specified location
//        val locationLatLng = LatLng(latitude, longitude)
//        val locationLatLng2 = LatLng(latitude+5.002, longitude+5.002)
//        val locationLatLng3 = LatLng(latitude+9.007, longitude+9.007)
//        val locationLatLng4 = LatLng(latitude+7.007, longitude+2.007)
//        mGoogleMap?.addMarker(MarkerOptions().position(locationLatLng).title("Current Location"))
//        mGoogleMap?.addMarker(MarkerOptions().position(locationLatLng2).title("Benda's Location"))
//        mGoogleMap?.addMarker(MarkerOptions().position(locationLatLng3).title("Yuval's Location"))
//        mGoogleMap?.addMarker(MarkerOptions().position(locationLatLng4).title("Idan's Location"))


        // Default location set to Tel Aviv, Israel
        val defaultLocation = LatLng(latitude, longitude)
        mGoogleMap?.addMarker(MarkerOptions().position(defaultLocation).title("Your Current Location"))
        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLng(defaultLocation))

        //TODO -> Fetch the all favorite Exercise from the current user and add markers for each one.

        // Move the camera to the specified location
//        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLng(locationLatLng))
//        mGoogleMap?.animateCamera(CameraUpdateFactory.zoomTo(15.0f)) // You can adjust the zoom level as needed
    }
}

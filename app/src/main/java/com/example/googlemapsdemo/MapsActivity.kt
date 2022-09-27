package com.example.googlemapsdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.googlemapsdemo.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.codelabs.buildyourfirstmap.BitmapHelper
import com.google.codelabs.buildyourfirstmap.place.Place
import com.google.codelabs.buildyourfirstmap.place.PlaceRenderer
import com.google.codelabs.buildyourfirstmap.place.PlacesReader


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnPolylineClickListener,
    GoogleMap.OnPolygonClickListener {

    // List of Place objects gathered by the place reader
    private val places: List<Place> by lazy {
        PlacesReader(this).read()
    }

    // converts the drawable to a BitMap in order to be used as a map marker
    private val bicycleIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(this, R.color.teal_700)
        BitmapHelper.vectorToBitmap(this, R.drawable.ic_directions_bike_black_24dp, color)
    }

    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map
        ) as? SupportMapFragment
        // add markers to the google map
        mapFragment?.getMapAsync { googleMap ->
            addMarkers(googleMap)
            // Set custom info window adapter (when marker is clicked the description window will appear)
            googleMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(this))
        }
    }

    private fun addMarkers(googleMap: GoogleMap) {
        places.forEach { place ->
            // define and add marker options
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .position(place.latLng)
                    .icon(bicycleIcon)
            )

            // Set place as the tag on the marker object so it can be referenced within
            // MarkerInfoWindowAdapter
            if (marker != null) {
                marker.tag = place
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    override fun onMapReady(googleMap: GoogleMap) {
        val polygon1 = googleMap.addPolygon(
            PolygonOptions()
            .clickable(true)
            .add(
                LatLng(-27.457, 153.040),
                LatLng(-33.852, 151.211),
                LatLng(-37.813, 144.962),
                LatLng(-34.928, 138.599)))

        // Position the map's camera near Alice Springs in the center of Australia,
        // and set the zoom factor so most of Australia shows on the screen.

        // Position the map's camera near Alice Springs in the center of Australia,
        // and set the zoom factor so most of Australia shows on the screen.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-23.684, 133.903), 4f))

        // Set listeners for click events.

        // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this)
        googleMap.setOnPolygonClickListener(this)
    }

    override fun onPolylineClick(p0: Polyline) {
        TODO("Not yet implemented")
    }

    override fun onPolygonClick(p0: Polygon) {
        TODO("Not yet implemented")
    }
}
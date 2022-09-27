package com.example.googlemapsdemo

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.google.android.cl

data class Place(
    val name: String,
    val latLng: LatLng,
    val address: String,
    val rating: Float
) : ClusterItem {
    override fun getPosition(): LatLng =
        latLng

    override fun getTitle(): String =
        name

    override fun getSnippet(): String =
        address
}
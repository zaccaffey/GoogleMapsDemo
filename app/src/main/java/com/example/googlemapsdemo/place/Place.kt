package com.google.codelabs.buildyourfirstmap.place

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

// data class that describes the information of each Place (Bike shop) on the map
data class Place(
    val name: String,
    val latLng: LatLng,
    val address: String,
    val rating: Float
    // ClusterItem represents a marker on a map
) : ClusterItem {
    override fun getPosition(): LatLng =
        latLng

    override fun getTitle(): String =
        name

    override fun getSnippet(): String =
        address
}

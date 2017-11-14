package com.example.i21633.myruns.Start

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import com.example.i21633.myruns.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.joda.time.DateTime


class MapDisplayActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var time : DateTime
    private lateinit var startLocation : Location
    private lateinit var previousLocation : Location


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_display)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
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
        mMap = googleMap

        mMap.isMyLocationEnabled = checkLocationPermission()
        time = DateTime.now()
        // Get current location
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0.toFloat(), locationListener)

        val criteria = Criteria()
        val provider = locationManager.getBestProvider(criteria, false)
        val location = locationManager.getLastKnownLocation(provider)
        val userLocation = LatLng(location.latitude, location.longitude)
        startLocation = location
        previousLocation = location
        //Current location
        mMap.addMarker(MarkerOptions().position(userLocation).title("My location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation))

    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            val timeDif = time .. DateTime.now()
            val locationDif = location!!.distanceTo(previousLocation)
            previousLocation = location
            //var avgSpeed : Int = timeDif / locationDif
        }
        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}
        override fun onProviderEnabled(p0: String?) {}
        override fun onProviderDisabled(p0: String?) {}
    }

    private fun checkLocationPermission(): Boolean {
        val permission = "android.permission.ACCESS_FINE_LOCATION"
        val res = this.checkCallingOrSelfPermission(permission)
        return res == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mapFragment.getMapAsync(this)
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }// other 'case' lines to check for other
        // permissions this app might request
    }

}

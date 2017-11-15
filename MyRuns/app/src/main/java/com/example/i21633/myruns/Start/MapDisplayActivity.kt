package com.example.i21633.myruns.Start

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.i21633.myruns.MainActivity
import com.example.i21633.myruns.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.android.synthetic.main.activity_map_display.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime


class MapDisplayActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var startTime : LocalDateTime
    private lateinit var previousTime : LocalDateTime
    private lateinit var startLocation : Location
    private lateinit var previousLocation : Location
    private var isRunStarted : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_display)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment

        setClickListeners()

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

        startTime = LocalDateTime.now()
        previousTime = startTime

        // Get current location
        val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1500, 0.toFloat(), locationListener)

        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.toFloat()))
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            if (!isRunStarted) {
                mMap.addMarker(MarkerOptions().position(LatLng(location!!.latitude, location!!.longitude)))
                startLocation = location
                previousLocation = location
                isRunStarted = true
            } else {

                // Calculate the Avg Speed and display
                displayAvgSpeed.text = calculateAverageSpeed(location)

                // Calculate Current Speed and display
                displayCurSpeed.text = calculateCurrentSpeed(location)

                // Calculate the Climb and display
                displayClimb.text = calculateClimb(location)

                // Calculate Calorie and display
                displayCaloriesBurned.text = calculateTotalCaloriesBurned()

                // Calculate total distance
                displayTotalDistance.text = calculateTotalDistance(location)

                // Draw new line over traveled space
                var locationList = arrayListOf(previousLocation, location!!)
                drawPrimaryLinePath(locationList)

                // Update the previousTime and previousLocation
                previousLocation = location
                previousTime = LocalDateTime.now()
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(location!!.latitude, location!!.longitude)))
        }
        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}
        override fun onProviderEnabled(p0: String?) {}
        override fun onProviderDisabled(p0: String?) {}
    }

    private fun setClickListeners() {
        saveGpsEntryButton.setOnClickListener({ v -> saveExerciseEntry(v) })
        cancelGpsEntryButton.setOnClickListener({ v -> cancelExerciseEntry(v) })
    }

    private fun saveExerciseEntry(v: View?) {

        var intent = Intent(application, MainActivity::class.java)
        startActivity(intent)
    }

    private fun cancelExerciseEntry(v: View?) {
        var intent = Intent(application, MainActivity::class.java)
        startActivity(intent)
    }

    private fun drawPrimaryLinePath(listLocationChangeToDraw : ArrayList<Location> ) {
        if ( map == null )
            return

        if ( listLocationChangeToDraw.size < 2 )
            return

        var options = PolylineOptions()

        options.color( Color.parseColor( "#ff0000" ) )
        options.width( 5.toFloat() )
        options.visible( true )

        listLocationChangeToDraw.forEach {
            locRecorded -> options.add(
                LatLng(locRecorded.latitude, locRecorded.longitude)
            )
        }

        mMap.addPolyline(options)
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

    private fun getDifference(start: LocalDateTime, now: LocalDateTime) : Double {
        val hourDif = Math.abs(start.hour - now.hour)
        val minDif = Math.abs(start.minute - now.minute)
        val secDif = Math.abs(start.second - now.second)

        return hourDif.toDouble() + (minDif.toDouble() / 60.toDouble()) + (secDif.toDouble() / 3600.toDouble())
    }

    private fun calculateAverageSpeed(location : Location?) : String {
        val timeDifInHours = getDifference(startTime, LocalDateTime.now())
        val locationDif = location!!.distanceTo(startLocation) / 1000
        val avgSpeed  = locationDif / timeDifInHours
        if (avgSpeed != Double.NaN && avgSpeed != Double.POSITIVE_INFINITY && avgSpeed != Double.NEGATIVE_INFINITY)
            return "Avg Speed: " + BigDecimal(avgSpeed).setScale(2, RoundingMode.DOWN).toString() + " km/h"
        return "Avg Speed: 0.00 km/h"
    }

    private fun calculateCurrentSpeed (location : Location?) : String {
        val timeDifInHours = getDifference(previousTime, LocalDateTime.now())
        val locationDif = location!!.distanceTo(previousLocation) / 1000
        val currentSpeed  = locationDif / timeDifInHours

        if (currentSpeed != Double.NaN && currentSpeed != Double.POSITIVE_INFINITY && currentSpeed != Double.NEGATIVE_INFINITY)
            return "Current Speed: " + BigDecimal(currentSpeed).setScale(2, RoundingMode.DOWN).toString() + " km/h"
        return "Current Speed: 0.00 km/h"
    }

    private fun calculateClimb(location: Location?): String {


        return "Climb: 0.0 km"
    }

    private fun calculateTotalCaloriesBurned(): String {
        return "Calories: 0"
    }

    private fun calculateTotalDistance(location : Location?) : String {
        val distance = Math.abs((location!!.distanceTo(startLocation) / 1000).toDouble())
        return "Distance: " + BigDecimal(distance).setScale(2, RoundingMode.DOWN) + " km"
    }

}

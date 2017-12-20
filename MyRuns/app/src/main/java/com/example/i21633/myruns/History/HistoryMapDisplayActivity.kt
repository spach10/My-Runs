package com.example.i21633.myruns.History

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import com.example.i21633.myruns.App
import com.example.i21633.myruns.Database.entity.ExerciseEntry
import com.example.i21633.myruns.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.android.synthetic.main.activity_history_map_display.*
import kotlinx.android.synthetic.main.activity_map_display.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.ArrayList

class HistoryMapDisplayActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment
    private var locationPoints : ArrayList<LatLng> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_map_display)

        mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.isMyLocationEnabled = checkLocationPermission()

        // Set up the location manager and location updates
        this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val bundle = intent.extras
        val exerciseEntryId = bundle.getInt("exerciseEntryId")

        val exerciseEntry = App.db?.exerciseEntryDao()?.getExerciseEntryById(exerciseEntryId) as ExerciseEntry
        historyAvgSpeed.text = "Avg Speed: " + exerciseEntry.mAvgSpeed.toString() + " km/h"
        historyTotalDistance.text = "Distance: " + BigDecimal(exerciseEntry.mDistance).setScale(2, RoundingMode.DOWN) + "km"
        historyActivityType.text = getActivityTypeFromPos(exerciseEntry.mActivityType)


        val coordinates = App.db?.latLngCoordinatesDao()?.getAllLatLngPointsByExerciseEntry(exerciseEntryId)
        coordinates!!.forEach {
            coordinate -> locationPoints.add(LatLng(coordinate.lat, coordinate.lng))
        }
        drawPrimaryLinePath(locationPoints)

        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.toFloat()))
    }

    private fun checkLocationPermission(): Boolean {
        val permission = "android.permission.ACCESS_FINE_LOCATION"
        val res = this.checkCallingOrSelfPermission(permission)

        return res == PackageManager.PERMISSION_GRANTED
    }

    private fun drawPrimaryLinePath(listLocationChangeToDraw : ArrayList<LatLng>) {
        if ( mMap == null )
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

        mMap.addMarker(MarkerOptions().position(LatLng(listLocationChangeToDraw.first()!!.latitude, listLocationChangeToDraw.first()!!.longitude)))
        mMap.addMarker(MarkerOptions().position(LatLng(listLocationChangeToDraw.last()!!.latitude, listLocationChangeToDraw.last()!!.longitude)))
    }

    private fun getActivityTypeFromPos(activityTypeInt: Int) : String {
        val activityTypes : Array<String> = resources.getStringArray(R.array.activityTypeArray)
        return "Type: " + activityTypes[activityTypeInt]
    }

}

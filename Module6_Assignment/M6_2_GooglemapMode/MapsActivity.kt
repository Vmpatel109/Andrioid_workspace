package com.example.googlemapmode

import android.Manifest
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import com.example.m6_2.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places

import java.util.Locale

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private  var CODE = 42
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var currentlocation : Location
    private lateinit var mMap: GoogleMap
    private lateinit var map:FrameLayout
    private lateinit var marker: Marker

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        map= findViewById(R.id.map)


        searchView = findViewById(R.id.searchview)
        searchView.clearFocus()


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getCurrentLocation()


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(p0: String?): Boolean {

                var loc = searchView.query.toString()

                if(loc == null)
                {
                    Toast.makeText(this@MapsActivity,"Location Not Found", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    val mGeocoder = Geocoder(this@MapsActivity, Locale.getDefault())

                    try {
                        val addressList: List<Address> = mGeocoder.getFromLocationName(loc,1)!!
                        if(addressList.size >0)
                        {
                            val latlng = LatLng(addressList.get(0).latitude,addressList.get(0).longitude)
                            if(marker != null)
                            {
                                marker.remove()
                            }
                            val markeroptions = MarkerOptions().position(latlng).title(loc)
                            markeroptions.icon(
                                BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_AZURE))
                            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latlng, 5f)
                            mMap.animateCamera(cameraUpdate)
                            marker = mMap.addMarker(markeroptions)!!

                        }

                    }catch (e:Exception)
                    {
                        e.printStackTrace()
                    }
                    //  Toast.makeText(this@MapsActivity,"Location Found",Toast.LENGTH_SHORT).show()
                }

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {



                return false
            }


        })
      /*  val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)*/

        val ai: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
        val value = ai.metaData["com.google.android.geo.API_KEY"]
        val apiKey = value.toString()

        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey)
        }

    }

    private fun getCurrentLocation()
    {
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),CODE)
            return
        }
       val task  = mFusedLocationClient.lastLocation


        task.addOnSuccessListener {
                location ->

            if (location != null) {
                currentlocation = location
                val supportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
                supportMapFragment.getMapAsync(this@MapsActivity)
            }
        }

    }


    override fun onMapReady(googleMap: GoogleMap) {

        this.mMap = googleMap
        val latLng = LatLng(currentlocation.latitude,currentlocation.longitude)
        val markerOptions = MarkerOptions().position(latLng).title("Current Location")
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20F))
        googleMap.addMarker(markerOptions)


       /* this.mMap = googleMap

        val mapIndia = LatLng(20.5937, 78.9629)
        this.mMap.addMarker(MarkerOptions().position(mapIndia).title("Marker in India"))
        this.mMap.moveCamera(CameraUpdateFactory.newLatLng(mapIndia))*/
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CODE)
        {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getCurrentLocation()
            }
        }

    }
}
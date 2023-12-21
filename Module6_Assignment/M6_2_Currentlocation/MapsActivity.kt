@file:Suppress("DEPRECATION")

package com.example.m6_2_currentlocation


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.CompoundButton
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.material.switchmaterial.SwitchMaterial
import java.util.Locale


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {


    //search view on google map

    private lateinit var marker: Marker
    private lateinit var searchView: SearchView

    private lateinit var switchMaterial: SwitchMaterial
 /*   private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor
*/

  //  private lateinit var uiModeManager: UiModeManager
    private lateinit var mMap: GoogleMap

    private  var PERMISSION_ID = 42
    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    var currentLocation: LatLng = LatLng(20.5, 78.9)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)


        // search view define
        searchView = findViewById(R.id.searchview)
        searchView.clearFocus()


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(p0: String?): Boolean {

                var loc = searchView.query.toString()

                if(loc == null)
                {
                    Toast.makeText(this@MapsActivity,"Location Not Found",Toast.LENGTH_SHORT).show()
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
                            markeroptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
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





        // toggle switch
        switchMaterial = findViewById(R.id.on1)


      /*  mapFragment = (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)!!
        mapFragment!!.getMapAsync(this)
*/
      /*  sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE)
        val  nightmode = sharedPreferences.getBoolean("nightmode",false)*/
       // uiModeManager = getSystemService(UI_MODE_SERVICE) as UiModeManager

        val ai: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
        val value = ai.metaData["com.google.android.geo.API_KEY"]
        val apiKey = value.toString()

        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey)
        }
        //option menu code
        val mapOptionMenu:ImageButton = findViewById(R.id.mapOptionsMenu)
        val popupMenu = PopupMenu(this,mapOptionMenu)
        popupMenu.menuInflater.inflate(R.menu.menu_main,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            changeMap(menuItem.itemId)
            true

        }
       /* if (nightmode)
        {
            switchMaterial.isChecked = true
         //   mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_in_night))

          //  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }*/

        mapOptionMenu.setOnClickListener {
            popupMenu.show()
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Adding functionality to the button
        val btn = findViewById<ImageView>(R.id.currentLoc)
        btn.setOnClickListener {
            getLastLocation()
        }
        switchMaterial.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener  { buttonView, isChecked ->

            if(switchMaterial.isChecked)
            {
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_in_night))
                //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
           /*     sharedPreferencesEditor = sharedPreferences.edit()
                sharedPreferencesEditor.putBoolean("nightmode",false)
*/
            }
            else
            {
                /*AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)*/

                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.my_map_style))
               /* sharedPreferencesEditor = sharedPreferences.edit()
                sharedPreferencesEditor.putBoolean("nightmode",true)*/
            }
          /*  sharedPreferencesEditor.apply()*/


        })

    }
   /* fun NightModeON(view: View?) {
        uiModeManager.nightMode = UiModeManager.MODE_NIGHT_YES
    }

    fun NightModeOFF(view: View?) {
        uiModeManager.nightMode = UiModeManager.MODE_NIGHT_NO
    } */

    private fun changeMap(itemId:Int)
    {
        when(itemId)
        {
            R.id.mapNone ->mMap.mapType = GoogleMap.MAP_TYPE_NONE
            R.id.mapNormal ->mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            R.id.mapSatelite ->mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            R.id.mapHybrid ->mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            R.id.mapTerrain ->mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


    }
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        currentLocation = LatLng(location.latitude, location.longitude)
                        mMap.clear()
                        mMap.addMarker(MarkerOptions().position(currentLocation))
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16F))
                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }
    @SuppressLint("MissingPermission", "NewApi")
    private fun requestNewLocationData() {

        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }
    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation!!
            currentLocation = LatLng(mLastLocation.latitude, mLastLocation.longitude)
        }
    }
    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED)
        {
            return true
        }
        return false
    }
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }
/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId

        if (id == R.id.mapNone)
        {
            mMap.mapType = GoogleMap.MAP_TYPE_NONE
        }
        if (id == R.id.mapNormal)
        {
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        }
        if (id == R.id.mapSatelite)
        {
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        }
        if (id == R.id.mapHybrid)
        {
            mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        }
        if (id == R.id.mapTerrain)
        {
            mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
        }
        return super.onOptionsItemSelected(item)
    }*/
}

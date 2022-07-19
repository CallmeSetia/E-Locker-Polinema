package com.polinema.smartlocker.views

// import org.jetbrains.anko.startActivity
import android.content.Intent
import android.app.AlertDialog
import android.content.DialogInterface

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.polinema.smartlocker.R
import com.polinema.smartlocker.databinding.ActivityMainBinding
import com.polinema.smartlocker.dialog.Dialog
import com.polinema.smartlocker.model.LoginResponse
import com.polinema.smartlocker.networking.ApiServices
import com.polinema.smartlocker.networking.RetrofitClient
import okhttp3.ResponseBody
import com.polinema.smartlocker.hawkstorage.HawkStorage
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import java.lang.Math.toRadians
import kotlin.math.*

import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager

import android.os.Looper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.polinema.smartlocker.model.LockerResponse
import com.polinema.smartlocker.model.LogoutResponse


class MainActivity : AppCompatActivity() {

    private lateinit var bindingUtama: ActivityMainBinding

    //Config Maps
    private var mapAttendance: SupportMapFragment? = null
    private var map: GoogleMap? = null
    private var locationManager: LocationManager? = null
    private var locationRequest: LocationRequest? = null
    private var locationSettingsRequest: LocationSettingsRequest? = null
    private var settingsClient: SettingsClient? = null
    private var currentLocation: Location? = null
    private var locationCallBack: LocationCallback? = null
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingUtama = DataBindingUtil.setContentView(this, R.layout.activity_main)
        updateView()
        click()
        init()
        checkPermission()
        getCurrentLocation()
//        loginToServer()

    }


    override fun onDestroy() {
        super.onDestroy()
        if (currentLocation != null && locationCallBack != null) {
            fusedLocationProviderClient?.removeLocationUpdates(locationCallBack!!)
        }
    }

    override fun onResume() {
        super.onResume()
        getCurrentLocation()
    }

    private fun init() {
        //Setup Location
        locationManager = this@MainActivity?.getSystemService(LOCATION_SERVICE) as LocationManager
        settingsClient = LocationServices.getSettingsClient(this@MainActivity!!)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this@MainActivity!!)
        locationRequest = LocationRequest()
            .setInterval(10000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest!!)
        locationSettingsRequest = builder.build()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_MAP_PERMISSIONS -> {
                var isHasPermission = false
                val permissionNotGranted = StringBuilder()

                for (i in permissions.indices) {
                    isHasPermission = grantResults[i] == PackageManager.PERMISSION_GRANTED

                    if (!isHasPermission) {
                        permissionNotGranted.append("${permissions[i]}\n")
                    }
                }

                if (isHasPermission) {
                    getCurrentLocation()
                } else {
                    val message =
                        permissionNotGranted.toString() + "\n" + getString(R.string.not_granted)
                    Dialog.dynamicDialog(
                        this@MainActivity,
                        getString(R.string.required_permission),
                        message
                    )
                }
            }
        }
    }

    private fun getCurrentLocation() {
        if (checkPermission()) {

            // Coordinate Perpiuse
            val Perpus = LatLng(-7.930024, 112.6658602)


            if (checkPermission()) {
                if (isLocationEnabled()) {
                    map?.isMyLocationEnabled = true
                    map?.uiSettings?.isMyLocationButtonEnabled = false

                    locationCallBack = object : LocationCallback() {
                        override fun onLocationResult(locationResult: LocationResult) {
                            super.onLocationResult(locationResult)
                            currentLocation = locationResult.lastLocation

                            if (currentLocation != null) {
                                val latitude = currentLocation?.latitude
                                val longitude = currentLocation?.longitude
//                                val posSkrg = LatLng(latitude,longitude)

                                val distance = hitungJarak(
                                    latitude!!,
                                    longitude!!,
                                    Perpus.latitude,
                                    Perpus.longitude
                                ) * 1000

                                Log.e(TAG, "LAT LONG JARAK: ${latitude} ${longitude} ${distance}")
                                if (HawkStorage.instance(this@MainActivity).apaScanOTP()) {
                                    kirimDataLatLng(HawkStorage.instance(this@MainActivity).getID() ,latitude!!,longitude!!);
                                }

                                if (distance > 20.0) {
                                    AlertDialog.Builder(this@MainActivity)
                                        .setTitle("Anda Keluar Dari Perpustakaan")
                                        .setMessage("Silahakan Login Lagi, Terimakasih Sudah Menggunakan Aplikasi Perpustakaan")
                                        .setNegativeButton("Dismis") { dialog, _ ->
                                            logoutRequest()
                                            dialog.dismiss()
                                        }
                                        .show()
                                }


                                //Toast.makeText(this@MainActivity, latitude.toString() + " - " + longitude.toString() +  " - " + distance.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    locationRequest?.let {
                        fusedLocationProviderClient?.requestLocationUpdates(
                            it,
                            locationCallBack as LocationCallback,
                            Looper.myLooper()
                        )
                    }
                } else {
                    goToTurnOnGps()
                }
            } else {
                makeRequest()
            }
        } else {
            makeRequest()
        }
    }

    private fun kirimDataLatLng(idMhs:String, lat: Double,lng: Double) {
        ApiServices.getSmartLockerApiServices()
            .kirimLatLng(idMhs, lat, lng)
            .enqueue(object : Callback<LogoutResponse> {
                override fun onResponse(
                    call: Call<LogoutResponse>,
                    response: Response<LogoutResponse>
                ) {
//                    MyDialog.hideDialog()
                    if (response.isSuccessful) {
                        Log.e(TAG, "Sukses: ${response.body()}")
                        val message = response.body()?.message

                        if (message != null) {
                            if (message == "1") {

                            }
                        }

                    } else {
                        val errorConverter: Converter<ResponseBody, LogoutResponse> =
                            RetrofitClient
                                .getClient()
                                .responseBodyConverter(
                                    LockerResponse::class.java,
                                    arrayOfNulls<Annotation>(0)
                                )
                        var errorResponse: LogoutResponse?
                        try {
                            response.errorBody()?.let {
                                errorResponse = errorConverter.convert(it)
//                                MyDialog.dynamicDialog(
//                                    this@LoginActivity,
//                                    getString(R.string.failed),
//                                    errorResponse?.message.toString()
//                                )
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                            Log.e(TAG, "Error: ${e.message}")
                        }
                    }
                }

                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
//                    MyDialog.hideDialog()
                    Log.e(TAG, "Error: ${t.message}")
                }

            })
    }

    private fun requestLogout() {

    }

    private fun hitungJarak(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val r = 6372.8 // in kilometers
        val radiansLat1 = toRadians(lat1)
        val radiansLat2 = toRadians(lat2)
        val dLat = toRadians(lat2 - lat1)
        val dLon = toRadians(lon2 - lon1)
        return 2 * r * asin(
            sqrt(
                sin(dLat / 2).pow(2.0) + sin(dLon / 2).pow(2.0) * cos(radiansLat1) * cos(
                    radiansLat2
                )
            )
        )
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this, mapPermissions,
            REQUEST_CODE_MAP_PERMISSIONS
        )
    }

    private fun isLocationEnabled(): Boolean {
        if (locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER)!! ||
            locationManager?.isProviderEnabled(LocationManager.NETWORK_PROVIDER)!!
        ) {
            return true
        }
        return false
    }

    private fun goToTurnOnGps() {
        settingsClient?.checkLocationSettings(locationSettingsRequest!!)
            ?.addOnSuccessListener {
                getCurrentLocation()
            }?.addOnFailureListener {
                when ((it as ApiException).statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                        try {
                            val resolvableApiException = it as ResolvableApiException
                            resolvableApiException.startResolutionForResult(
                                this@MainActivity,
                                REQUEST_CODE_LOCATION
                            )
                        } catch (ex: IntentSender.SendIntentException) {
                            ex.printStackTrace()
                            Log.e(TAG, "Error: ${ex.message}")
                        }
                    }
                }
            }
    }


    //    private fun setRequestPermission() {
//        ActivityCompat.requestPermissions(mapPermissions, REQUEST_CODE_MAP_PERMISSIONS)
//    }
    private fun checkPermission(): Boolean {
        var isHasPermission = false
        this@MainActivity?.let {
            for (permission in mapPermissions) {
                isHasPermission = ActivityCompat.checkSelfPermission(
                    it,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            }
        }
        return isHasPermission
    }

    private fun updateView() {
        val nama = HawkStorage.instance(this@MainActivity).getNama()
        val nim = HawkStorage.instance(this@MainActivity).getNim()
        val prodi = HawkStorage.instance(this@MainActivity).getProdi()
        val tglLahir = HawkStorage.instance(this@MainActivity).getTglLahir()
        val noHp = HawkStorage.instance(this@MainActivity).getNoHp()

        bindingUtama.textNama.setText("NAMA: " + nama.toString())
        bindingUtama.textNim.setText("NIM: " + nim.toString())
        bindingUtama.textProdi.setText("PRODI: " + prodi.toString())
        bindingUtama.textTglLahir.setText("TGL LAHIR: " + tglLahir.toString())
        bindingUtama.texNoHp.setText("NO.HP: " + noHp.toString())

    }

    private fun click() {
//       bindingUtama.buttonInputOTP.setOnClickListener {
//           if (!HawkStorage.instance(this@MainActivity).isScanQR()) {
//               Toast.makeText(this@MainActivity, "ERROR: Belum SCAN QR", Toast.LENGTH_SHORT).show()
//           }
//       }
        bindingUtama.buttonReqOTP.setOnClickListener {
            if (!HawkStorage.instance(this@MainActivity).isScanQR()) {
                Toast.makeText(this@MainActivity, "ERROR: Belum SCAN QR", Toast.LENGTH_SHORT).show()
            } else {
                val intentLogin = Intent(this@MainActivity, RequestOtpActivity::class.java)
                startActivity(intentLogin)
            }

        }
        bindingUtama.buttonScanQR.setOnClickListener {
            val intentLogin = Intent(this@MainActivity, ScanActivity::class.java)
            startActivity(intentLogin)
        }
        bindingUtama.buttonLogout.setOnClickListener {
            logoutRequest()
        }
    }

    private fun logoutRequest() {
        val idMhs = HawkStorage.instance(this@MainActivity).getID()
        ApiServices.getSmartLockerApiServices()
            .logout(idMhs)
            .enqueue(object : Callback<LogoutResponse> {
                override fun onResponse(
                    call: Call<LogoutResponse>,
                    response: Response<LogoutResponse>
                ) {
//                    MyDialog.hideDialog()
                    if (response.isSuccessful) {
                        Log.e(TAG, "Sukses: ${response.body()}")
                        val message = response.body()?.message

                        if (message != null) {
                            if (message == "1") {

                            }
                        }

                    } else {
                        val errorConverter: Converter<ResponseBody, LogoutResponse> =
                            RetrofitClient
                                .getClient()
                                .responseBodyConverter(
                                    LockerResponse::class.java,
                                    arrayOfNulls<Annotation>(0)
                                )
                        var errorResponse: LogoutResponse?
                        try {
                            response.errorBody()?.let {
                                errorResponse = errorConverter.convert(it)
//                                MyDialog.dynamicDialog(
//                                    this@LoginActivity,
//                                    getString(R.string.failed),
//                                    errorResponse?.message.toString()
//                                )
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                            Log.e(TAG, "Error: ${e.message}")
                        }
                    }
                }

                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
//                    MyDialog.hideDialog()
                    Log.e(TAG, "Error: ${t.message}")
                }

            })
        HawkStorage.instance(this@MainActivity).deleteAll()
        startActivity(Intent(this, SplashActivity::class.java))
        finish()
    }


    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private const val REQUEST_CODE_MAP_PERMISSIONS = 1000
        private const val REQUEST_CODE_LOCATION = 2000
    }

    private val mapPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
}

package com.polinema.smartlocker.views

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode

import android.annotation.SuppressLint
import android.content.Intent
import androidx.databinding.DataBindingUtil
import com.polinema.smartlocker.BuildConfig
import com.polinema.smartlocker.R
import com.polinema.smartlocker.databinding.ActivityScanqrBinding
import com.polinema.smartlocker.hawkstorage.HawkStorage
import android.widget.Toast
import com.polinema.smartlocker.model.LoginResponse
import com.polinema.smartlocker.model.QrResponse
import com.polinema.smartlocker.networking.ApiServices
import com.polinema.smartlocker.networking.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

@SuppressLint("CustomSplashScreen")
class ScanActivity : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner
    private lateinit var binding: ActivityScanqrBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scanqr)



        setupPermissions()
        codeScanner()
    }

    private fun codeScanner() {
        codeScanner = CodeScanner(this, binding.scn)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    binding.tvText.text = it.text
                    if (it.text.toString() ==  BuildConfig.BASE_URL + "request_qr.php") {
                        requestQR()
//                        Toast.makeText(this@ScanActivity, "PASS" +  BuildConfig.BASE_URL + "request_qr.php", Toast.LENGTH_SHORT).show()

                    }
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    Log.e("Main", "codeScanner: ${it.message}")
                }
            }

            binding.scn.setOnClickListener {
                codeScanner.startPreview()
            }

        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun requestQR() {
        ApiServices.getSmartLockerApiServices()
            .request_qr()
            .enqueue(object : Callback<QrResponse> {
                override fun onResponse(
                    call: Call<QrResponse>,
                    response: Response<QrResponse>
                ) {
//                    MyDialog.hideDialog()
                    if (response.isSuccessful){
                        Log.e(TAG, "Sukses: ${response.body()}")
                        val otp = response.body()?.otp
                        val message = response.body()?.qrCode

                        var otpmasuk : String? = ""
                        var otpkeluar : String?=  ""
                        var lockerNo : String? =  ""
                        var timeValid : Int? =  0
                        var timeNow: Int? =  0

                        if (message != null ) {
                            if (otp != null ) {

                                otpmasuk = otp[0]?.masuk;
                                otpkeluar = otp[0]?.keluar;
                                timeValid = otp[0]?.validUntil;
                                timeNow = otp[0]?.timeNow;
                                lockerNo = otp[0]?.locker;

                                if (lockerNo == "0") {
                                    Toast.makeText(this@ScanActivity, "LOCKER PENUH", Toast.LENGTH_SHORT).show()
                                }
                            }
                            if (message == "OKE") {
                                if (lockerNo != "0") {
                                    HawkStorage.instance(this@ScanActivity)
                                        .setOTPMasuk(otpmasuk.toString())
                                    HawkStorage.instance(this@ScanActivity)
                                        .setOTPKeluar(otpkeluar.toString())
                                    HawkStorage.instance(this@ScanActivity)
                                        .setLockerNum(lockerNo.toString())
                                    HawkStorage.instance(this@ScanActivity)
                                        .setTimeNow(timeNow.toString())
                                    HawkStorage.instance(this@ScanActivity)
                                        .setTimeValid(timeValid.toString())

                                    HawkStorage.instance(this@ScanActivity).setIsScanQR(1)

                                    val intent = Intent(this@ScanActivity, MainActivity::class.java)
                                    startActivity(intent)

                                }
                            }
                        }

                    }else{
                        val errorConverter: Converter<ResponseBody, QrResponse> =
                            RetrofitClient
                                .getClient()
                                .responseBodyConverter(
                                    LoginResponse::class.java,
                                    arrayOfNulls<Annotation>(0)
                                )
                        var errorResponse: QrResponse?
                        try {
                            response.errorBody()?.let {
                                errorResponse = errorConverter.convert(it)
//                                MyDialog.dynamicDialog(
//                                    this@LoginActivity,
//                                    getString(R.string.failed),
//                                    errorResponse?.message.toString()
//                                )
                            }
                        }catch (e: IOException){
                            e.printStackTrace()
                            Log.e(TAG, "Error: ${e.message}")
                        }
                    }
                }
                override fun onFailure(call: Call<QrResponse>, t: Throwable) {
//                    MyDialog.hideDialog()
                    Log.e(TAG, "Error: ${t.message}")
                }

            })
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQ
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQ -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                        this,
                        "You need the camera permission to use this app",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    companion object {
        private const val CAMERA_REQ = 101
        private val TAG = ScanActivity::class.java.simpleName
    }
}
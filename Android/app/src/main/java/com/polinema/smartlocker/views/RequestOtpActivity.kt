package com.polinema.smartlocker.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.polinema.smartlocker.R
import com.polinema.smartlocker.databinding.ActivityOtpBinding
import com.polinema.smartlocker.hawkstorage.HawkStorage
import com.polinema.smartlocker.model.LockerResponse
import com.polinema.smartlocker.model.LoginResponse
import com.polinema.smartlocker.model.OtpResponse
import com.polinema.smartlocker.model.QrResponse
import com.polinema.smartlocker.networking.ApiServices
import com.polinema.smartlocker.networking.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

class RequestOtpActivity : AppCompatActivity() {

    private lateinit var bindingUtama: ActivityOtpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingUtama = DataBindingUtil.setContentView(this, R.layout.activity_otp)

        checkAfterScanQr();
        requestOTP();
        click();
    }

    private fun checkAfterScanQr() {
        if (!HawkStorage.instance(this@RequestOtpActivity).isScanQR()) {
            Toast.makeText(this@RequestOtpActivity, "ERROR: Belum SCAN QR", Toast.LENGTH_SHORT)
                .show()
            val intentLogin = Intent(this@RequestOtpActivity, MainActivity::class.java)
            startActivity(intentLogin)
        }
    }

    private fun requestOTP() {
        val otpMasuk = HawkStorage.instance(this@RequestOtpActivity).getOTPMasuk()
        val otpKeluar = HawkStorage.instance(this@RequestOtpActivity).getOTPKeluar()

        val timeValid = HawkStorage.instance(this@RequestOtpActivity).getTimeValid()
        val lockerNum = HawkStorage.instance(this@RequestOtpActivity).getLockerNum()
        val idMhs = HawkStorage.instance(this@RequestOtpActivity).getID()

        Log.e(TAG, "REQUEST:  -  ${lockerNum}")


        ApiServices.getSmartLockerApiServices()
            .request_otp(idMhs, otpMasuk, otpKeluar, timeValid, lockerNum)
            .enqueue(object : Callback<OtpResponse> {
                override fun onResponse(
                    call: Call<OtpResponse>,
                    response: Response<OtpResponse>
                ) {

                    Log.e(TAG, "Sukses: ${response.body()}")
                    if (response.isSuccessful) {
//                        val data = response.body()?.data
                        val message = response.body()?.message
//                        var otpmsk : Any? = ""
//                        var otpklr :Any?=  ""
//
//                        var user = response.body()?.data
                        if (message != null) {
//                            if (user != null ) {
//                                otpmsk  = user[0]?.otpMasuk;
//                                otpklr  = user[0]?.otpKeluar;
//                            }
                            if (message == "OKE") {
                                bindingUtama.textOTPMasuk.setText(otpMasuk.toString())
                                bindingUtama.textOTPKeluar.setText(otpKeluar.toString())

                            }
                        }

                    } else {
                        val errorConverter: Converter<ResponseBody, OtpResponse> =
                            RetrofitClient
                                .getClient()
                                .responseBodyConverter(
                                    LoginResponse::class.java,
                                    arrayOfNulls<Annotation>(0)
                                )
                        var errorResponse: OtpResponse?
                        try {
                            response.errorBody()?.let {
                                errorResponse = errorConverter.convert(it)
//                                MyDialog.dynamicDialog(
//                                    this@LoginActivity,
//                                    getString(R.string.failed),
//                                    errorResponse?.message.toString()
//                                )ip
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                            Log.e(TAG, "Error: ${e.message}")
                        }
                    }
                }

                override fun onFailure(call: Call<OtpResponse>, t: Throwable) {
//                    MyDialog.hideDialog()
                    Log.e(TAG, "Error: ${t.message}")
                }
            })
    }

    private fun preRequestOTP() {
        ApiServices.getSmartLockerApiServices()
            .request_qr()
            .enqueue(object : Callback<QrResponse> {
                override fun onResponse(
                    call: Call<QrResponse>,
                    response: Response<QrResponse>
                ) {
//                    MyDialog.hideDialog()
                    if (response.isSuccessful) {
                        Log.e(TAG, "Sukses: ${response.body()}")
                        val otp = response.body()?.otp
                        val message = response.body()?.qrCode

                        var otpmasuk: String? = ""
                        var otpkeluar: String? = ""
                        var lockerNo: String? = ""
                        var timeValid: Int? = 0
                        var timeNow: Int? = 0

                        if (message != null) {
                            if (otp != null) {
                                if (lockerNo == "0") {
                                    Toast.makeText(
                                        this@RequestOtpActivity,
                                        "LOCKER PENUH",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                otpmasuk = otp[0]?.masuk;
                                otpkeluar = otp[0]?.keluar;
                                timeValid = otp[0]?.validUntil;
                                timeNow = otp[0]?.timeNow;
                            }
                            if (message == "OKE") {
                                if (lockerNo != "0") {
                                    HawkStorage.instance(this@RequestOtpActivity)
                                        .setOTPMasuk(otpmasuk.toString())
                                    HawkStorage.instance(this@RequestOtpActivity)
                                        .setOTPKeluar(otpkeluar.toString())
                                    HawkStorage.instance(this@RequestOtpActivity)
                                        .setLockerNum(lockerNo.toString())
                                    HawkStorage.instance(this@RequestOtpActivity)
                                        .setTimeNow(timeNow.toString())
                                    HawkStorage.instance(this@RequestOtpActivity)
                                        .setTimeValid(timeValid.toString())

                                    HawkStorage.instance(this@RequestOtpActivity).setIsScanQR(1)
                                    HawkStorage.instance(this@RequestOtpActivity).isReqOTP(true)

                                    val intent =
                                        Intent(this@RequestOtpActivity, MainActivity::class.java)
                                    startActivity(intent)

                                }
                            }
                        }

                    } else {
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
                        } catch (e: IOException) {
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

    private fun click() {
        bindingUtama.btnReqOTP.setOnClickListener {
            if (bindingUtama.textOTPKeluar.text.toString() == "XXXX" || bindingUtama.textOTPMasuk.text.toString() == "XXXX") {
                preRequestOTP();
                requestOTP();
            } else {
                Toast.makeText(this@RequestOtpActivity, "KODE OTP SUDAH VALID", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        bindingUtama.btnInputOTP.setOnClickListener {
            val teksInputOtp = bindingUtama.inputTextOTP.text.toString()
            val idMhs = HawkStorage.instance(this@RequestOtpActivity).getID()
            val idLocker = HawkStorage.instance(this@RequestOtpActivity).getLockerNum()
            Toast.makeText(
                this@RequestOtpActivity,
                "Locker " + idLocker +" : Terbuka ",
                Toast.LENGTH_SHORT
            ).show()
            ApiServices.getSmartLockerApiServices()
                .input_otp(idMhs, teksInputOtp)
                .enqueue(object : Callback<LockerResponse> {
                    override fun onResponse(
                        call: Call<LockerResponse>,
                        response: Response<LockerResponse>
                    ) {
//                    MyDialog.hideDialog()
                        if (response.isSuccessful) {
                            Log.e(TAG, "Sukses: ${response.body()}")
                            val status = response.body()?.status
                            val locker = response.body()?.locker

                            var stateLocker: String? = ""
                            var lock: String? = ""

                            if (status != null) {
                                if (locker != null) {
                                    stateLocker = status;
                                    lock = locker;
                                }
                                if (status == "OKE") {
                                    if (lock == "1") {


                                    }
                                }
                            }

                        } else {
                            val errorConverter: Converter<ResponseBody, LockerResponse> =
                                RetrofitClient
                                    .getClient()
                                    .responseBodyConverter(
                                        LockerResponse::class.java,
                                        arrayOfNulls<Annotation>(0)
                                    )
                            var errorResponse: LockerResponse?
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

                    override fun onFailure(call: Call<LockerResponse>, t: Throwable) {
//                    MyDialog.hideDialog()
                        Log.e(TAG, "Error: ${t.message}")
                    }

                })
        }
    }

    companion object {
        private val TAG = RequestOtpActivity::class.java.simpleName
    }

}
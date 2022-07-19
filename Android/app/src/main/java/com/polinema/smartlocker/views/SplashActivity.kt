package com.polinema.smartlocker.views

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.polinema.smartlocker.R
import com.polinema.smartlocker.dialog.Dialog
import com.polinema.smartlocker.hawkstorage.HawkStorage
import com.polinema.smartlocker.model.LockerResponse
import com.polinema.smartlocker.model.LogoutResponse
import com.polinema.smartlocker.networking.ApiServices
import com.polinema.smartlocker.networking.RetrofitClient
import kotlinx.coroutines.Dispatchers.Main
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
//    Deklarasi Variabel untuk timer Splash Screen
    private val SPLASH_TIME_OUT:Long = 1000; //Delay 3 detik
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        afterDelayGoToLogin()
    }

    private fun afterDelayGoToLogin() {
        var cek = 0;
        Handler(Looper.getMainLooper()).postDelayed({
            checkKonekJaringan()
            checkIsLogin()
//            if (checkKonekJaringan() == 1) checkIsLogin()
        },1200)
    }

    private fun checkIsLogin() {
        val isLogin = HawkStorage.instance(this).isLogin()
        if (isLogin){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this, LoginActivty::class.java))
            finish()
        }
    }

    private fun checkKonekJaringan() {

        ApiServices.getSmartLockerApiServices()
            .cek_jaringan()
            .enqueue(object : Callback<LogoutResponse> {
                override fun onResponse(
                    call: Call<LogoutResponse>,
                    response: Response<LogoutResponse>
                ) {
                    Dialog.hideDialog()
                    if (response.isSuccessful) {
                        val message = response.body()?.message
                        if (message != null) {
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
                                Dialog.dynamicDialog(
                                    this@SplashActivity,
                                    getString(R.string.failed),
                                    "Anda Tidak Terhubung Jaringan Perpustakaan"
                                )
                                startActivity(Intent(this@SplashActivity, SplashActivity::class.java))
                                finish()

                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
//                    AlertDialog.Builder(this@SplashActivity)
//                        .setTitle("Anda Keluar Dari Perpustakaan")
//                        .setMessage("Silahakan Login Lagi, Terimakasih Sudah Menggunakan Aplikasi Perpustakaan")
//                        .setNegativeButton("Dismis") { dialog, _ ->
//                            dialog.dismiss()
//                        }
//                        .show()
//                    startActivity(Intent(this@SplashActivity, SplashActivity::class.java))
//                    finish()
                }
            })
    }
//  Kode untuk menjalankan main activity setelah timer splash screen habis
//        Handler().postDelayed({
//            startActivity(Intent(this, LoginActivty::class.java))
//            finish()
//        }, SPLASH_TIME_OUT)


}
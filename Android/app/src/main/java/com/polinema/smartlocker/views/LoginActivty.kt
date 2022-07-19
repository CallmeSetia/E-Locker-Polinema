package com.polinema.smartlocker.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.polinema.smartlocker.R
import com.polinema.smartlocker.databinding.ActivtyLoginBinding
import android.util.Log
import android.widget.Toast
import com.polinema.smartlocker.hawkstorage.HawkStorage

import com.polinema.smartlocker.dialog.Dialog
import com.polinema.smartlocker.model.LoginResponse
import com.polinema.smartlocker.networking.ApiServices
import com.polinema.smartlocker.networking.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

class LoginActivty : AppCompatActivity() {

    private lateinit var bindingUtama : ActivtyLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingUtama = DataBindingUtil.setContentView(this, R.layout.activty_login)



        bindingUtama.btnLogin.setOnClickListener {

            loginToServer();
        }
    }
    private fun loginToServer() {
        //val loginRequest = LoginRequest(email = email, password = password, deviceName = "mobile")
        //val loginRequestString = Gson().toJson(loginRequest)
        //MyDialog.showProgressDialog(this)
//        val intent = intent
//        val extras = intent.extras
//        val username = bindingUtama.usernameInput.text.toString()
//        val password = bindingUtama.passwordInput.text.toString()
        val usernameInput =  bindingUtama.usernameInput.text.toString()
        val passwordInput =  bindingUtama.passwordInput.text.toString()

        Log.e(TAG, "Input: ${usernameInput} &  ${passwordInput} ")
        ApiServices.getSmartLockerApiServices()
            .loginRequest(usernameInput, passwordInput)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    Dialog.hideDialog()
                    if (response.isSuccessful){
                        Log.e(TAG, "Sukses: ${response.body()}")
                        val data = response.body()?.data
                        val message = response.body()?.message
                        var nama : String? = ""
                        var nim : String?=  ""
                        var prodi : String? =  ""
                        var tglLahir : String?=  ""
                        var noHp : String?=  ""
                        var idMhs : String?=  ""


                        var user = response.body()?.data
                        if (message != null ) {
                            if (user != null ) {
                                nama  = user[0]?.namaMhs;
                                nim  = user[0]?.nimMhs;
                                prodi  = user[0]?.prodiMhs;
                                tglLahir  = user[0]?.tglLahirMhs;
                                noHp  = user[0]?.nohpMhs;
                                idMhs  = user[0]?.idMhs;
                            }
                            if (message == "Username atau Password Salah") {
                                Toast.makeText(this@LoginActivty, "GAGAL LOGIN: Username atau Password Salah", Toast.LENGTH_SHORT).show()
                            }
                            if (message == "Login Berhasil") {
                                HawkStorage.instance(this@LoginActivty).setNama(nama.toString())
                                HawkStorage.instance(this@LoginActivty).setNim(nim.toString())
                                HawkStorage.instance(this@LoginActivty).setProdi(prodi.toString())
                                HawkStorage.instance(this@LoginActivty).setTglLahir(tglLahir.toString())
                                HawkStorage.instance(this@LoginActivty).setNoHP(noHp.toString())
                                HawkStorage.instance(this@LoginActivty).setID(idMhs.toString())


                                val intent = Intent(this@LoginActivty, MainActivity::class.java)
                                startActivity(intent)

                            }
                        }

                    }else{
                        val errorConverter: Converter<ResponseBody, LoginResponse> =
                            RetrofitClient
                                .getClient()
                                .responseBodyConverter(
                                    LoginResponse::class.java,
                                    arrayOfNulls<Annotation>(0)
                                )
                        var errorResponse: LoginResponse?
                        try {
                            response.errorBody()?.let {
                                errorResponse = errorConverter.convert(it)
                                Dialog.dynamicDialog(
                                    this@LoginActivty,
                                    getString(R.string.failed) + "Tidak Ada Koneksi Ke Server",
                                    errorResponse?.message.toString()
                                )
                            }
                        }catch (e: IOException){
                            e.printStackTrace()
                            Log.e(TAG, "Error: ${e.message}")
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Dialog.dynamicDialog(
                        this@LoginActivty,
                        getString(R.string.failed),
                        "Anda Tidak Terhubung Jaringan Perpustakaan"
                    )
//                    Dialog.hideDialog()
                    Log.e(TAG, "Error: ${t.message}")
                }
            })
    }

    companion object{
        private val TAG = LoginActivty::class.java.simpleName
    }
}
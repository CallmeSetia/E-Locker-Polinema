package com.polinema.smartlocker.networking

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

import retrofit2.http.GET
import retrofit2.http.Query
import com.polinema.smartlocker.model.*

interface SmartLocketAPIServices {

//    @GET("login.php?{}")

    @GET("login.php")
    fun loginRequest(@Query("username") usernname: String?,
                     @Query("password") password: String?
    ): Call<LoginResponse>

    @GET("request_qr.php")
    fun request_qr(): Call<QrResponse>

    @GET("request_otp.php")
    fun request_otp(@Query("idMhs") idMhs: String?,
                    @Query("OTPMasuk") otp_masuk: String?,
                    @Query("OTPKeluar") otp_keluar: String?,
                    @Query("TimeValid") time_valid: String?,
                    @Query("Locker") locker: String?
    ): Call<OtpResponse>

    @GET("input_otp.php")
    fun input_otp(
            @Query("idMhs") idMhs: String?,
            @Query("otp") otp_masuk: String?,

    ): Call<LockerResponse>

    @GET("logout.php")
    fun logout(
        @Query("idMhs") idMhs: String?,
        ): Call<LogoutResponse>

    @GET("cek_jaringan.php")
    fun cek_jaringan(): Call<LogoutResponse>

    @GET("sendLatLng.php")
    fun kirimLatLng(@Query("idMhs") idMhs: String?,
                    @Query("lat") lat: Double?,
                    @Query("lng") lng: Double?
    ): Call<LogoutResponse>
}


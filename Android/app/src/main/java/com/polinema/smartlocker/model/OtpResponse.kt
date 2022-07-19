package com.polinema.smartlocker.model

import com.google.gson.annotations.SerializedName

data class OtpResponse(

//	@field:SerializedName("data")
//	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

//data class DataItem(
//
//	@field:SerializedName("otp_masuk")
//	val otpMasuk: Any? = null,
//
//	@field:SerializedName("time_otp")
//	val timeOtp: Int? = null,
//
//	@field:SerializedName("otp_keluar")
//	val otpKeluar: Any? = null,
//
//	@field:SerializedName("locker")
//	val locker: String? = null
//)

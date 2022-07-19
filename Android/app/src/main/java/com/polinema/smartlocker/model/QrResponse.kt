package com.polinema.smartlocker.model

import com.google.gson.annotations.SerializedName

data class QrResponse(

	@field:SerializedName("qrCode")
	val qrCode: String? = null,

	@field:SerializedName("otp")
	val otp: List<OtpItem?>? = null
)

data class OtpItem(

	@field:SerializedName("id_locker")
	val idLocker: String? = null,

	@field:SerializedName("time_now")
	val timeNow: Int? = null,

	@field:SerializedName("masuk")
	val masuk: String? = null,

	@field:SerializedName("validUntil")
	val validUntil: Int? = null,

	@field:SerializedName("control")
	val control: String? = null,

	@field:SerializedName("keluar")
	val keluar: String? = null,

	@field:SerializedName("id_mhs")
	val idMhs: String? = null,

	@field:SerializedName("locker")
	val locker: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

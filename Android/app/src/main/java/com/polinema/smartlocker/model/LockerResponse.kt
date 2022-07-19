package com.polinema.smartlocker.model

import com.google.gson.annotations.SerializedName

data class LockerResponse(

	@field:SerializedName("locker")
	val locker: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

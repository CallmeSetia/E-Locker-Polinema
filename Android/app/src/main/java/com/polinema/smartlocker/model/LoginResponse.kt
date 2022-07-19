package com.polinema.smartlocker.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: List<User?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class User(

	@field:SerializedName("nama_mhs")
	val namaMhs: String? = null,

	@field:SerializedName("email_mhs")
	val emailMhs: String? = null,

	@field:SerializedName("alamat_mhs")
	val alamatMhs: String? = null,

	@field:SerializedName("tgl_lahir_mhs")
	val tglLahirMhs: String? = null,

	@field:SerializedName("nohp_mhs")
	val nohpMhs: String? = null,

	@field:SerializedName("prodi_mhs")
	val prodiMhs: String? = null,

	@field:SerializedName("foto_mhs")
	val fotoMhs: String? = null,

	@field:SerializedName("nim_mhs")
	val nimMhs: String? = null,

	@field:SerializedName("id_mhs")
	val idMhs: String? = null
)

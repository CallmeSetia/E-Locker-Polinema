package com.polinema.smartlocker.hawkstorage

import android.content.Context
import com.polinema.smartlocker.model.User
import com.orhanobut.hawk.Hawk

class HawkStorage {
    companion object{
        private const val NAMA_USER = "USERNAME"
        private const val PASSWORD_USER = "PASSWORD"
        private const val NIM_USER = "NIM"
        private const val PRODI_USER = "PRODI"
        private const val TGLLAHIR_USER = "TGLLAHIR"
        private const val NOHP_USER = "NOHP"
        private const val SCAN_QR = "SCAN_QR"
        private const val ID = "ID"

        private const val TIME_VALID = "TIME_VALID"
        private const val TIME_NOW  = "TIME_NOW"

        private const val OTP_MASUK = "OTP_MASUK"
        private const val OTP_KELUAR = "OTP_KELUAR"
        private const val LOCKER_NUM = "LOCKER_NUM"

        private const val IS_REQ_OTP = "LOCKER_NUM"

        private val hawkStorage = HawkStorage()

        fun instance(context: Context?): HawkStorage{
            Hawk.init(context).build()
            return hawkStorage
        }
    }

    fun setNama(user: String){
        Hawk.put(NAMA_USER, user)
        Hawk.put(PASSWORD_USER, user)
    }
    fun setNim(user: String){
        Hawk.put(NIM_USER, user)
    }
    fun setProdi(user: String){
        Hawk.put(PRODI_USER, user)
    }
    fun setTglLahir(user: String){
        Hawk.put(TGLLAHIR_USER, user)
    }
    fun setNoHP(user: String){
        Hawk.put(NOHP_USER, user)
    }
    fun setOTPMasuk(otp: String){
        Hawk.put(OTP_MASUK, otp)
    }
    fun setOTPKeluar(otp: String){
        Hawk.put(OTP_KELUAR, otp)
    }
    fun setIsScanQR(otp: Int){
        Hawk.put(SCAN_QR, otp)
    }
    fun setID(id: String){
        Hawk.put(ID, id)
    }
    fun setLockerNum(locker: String) {
        Hawk.put(LOCKER_NUM,locker)
    }
    fun setTimeNow(locker: String) {
        Hawk.put(TIME_NOW,locker)
    }
    fun setTimeValid(locker: String) {
        Hawk.put(TIME_VALID,locker)
    }
    fun isReqOTP(locker: Boolean) {
        Hawk.put(IS_REQ_OTP,locker)
    }

    ///
    fun getNama(): String{
        return Hawk.get(NAMA_USER)
    }
    fun getNim(): String{
        return Hawk.get(NIM_USER)
    }
    fun getProdi(): String{
        return Hawk.get(PRODI_USER)
    }
    fun getTglLahir(): String{
        return Hawk.get(TGLLAHIR_USER)
    }
    fun getNoHp(): String{
        return Hawk.get(NOHP_USER)
    }
    fun getOTPMasuk() : String{
        return Hawk.get(OTP_MASUK)
    }
    fun getOTPKeluar() : String{
        return Hawk.get(OTP_KELUAR)
    }
    fun getLockerNum (): String {
        return Hawk.get(LOCKER_NUM)
    }
    fun getID (): String {
        return Hawk.get(ID)
    }
    fun getTimeValid(): String {
        return Hawk.get(TIME_VALID)
    }
    fun getTimeNow(): Int {
        return Hawk.get(TIME_NOW)
    }

    fun deleteScanQr() {
        Hawk.delete(SCAN_QR)
        Hawk.delete(OTP_MASUK)
        Hawk.delete(OTP_KELUAR)
        Hawk.delete(LOCKER_NUM)
    }

//    fun setToken(token: String){
//        Hawk.put(TOKEN_KEY, token)
//    }




//    fun getToken(): String{
//        val rawToken = Hawk.get<String>(TOKEN_KEY)
//        val token = rawToken.split("|")
//        return token[1]
//    }

    fun isLogin(): Boolean{
        if (Hawk.contains(NAMA_USER)){
            return true
        }
        return false
    }

    fun isScanQR() : Boolean{
        if (Hawk.contains(SCAN_QR)) {
            return true
        }
        return false
    }

    fun apaScanOTP() : Boolean{
        if (Hawk.contains(IS_REQ_OTP)) {
            return true
        }
        return false
    }


    fun deleteAll(){
        Hawk.deleteAll()
    }
}
package com.polinema.smartlocker.networking

object ApiServices {
    fun getSmartLockerApiServices(): SmartLocketAPIServices{
        return RetrofitClient
            .getClient()
            .create(SmartLocketAPIServices::class.java)
    }
}
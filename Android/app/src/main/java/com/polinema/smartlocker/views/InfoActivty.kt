package com.polinema.smartlocker.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.polinema.smartlocker.R
import com.polinema.smartlocker.databinding.ActivtyLoginBinding

class InfoActivty : AppCompatActivity() {

    private lateinit var bindingUtama : ActivtyLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingUtama =DataBindingUtil.setContentView(this, R.layout.activty_login)

        bindingUtama.btnLogin.setOnClickListener {
            val intent = Intent(this, InfoActivty::class.java)
            startActivity(intent)
        }
    }
}
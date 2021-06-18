package com.unss.defencecatalog.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.unss.defencecatalog.R
import com.unss.defencecatalog.ui.categories.MainActivity
import com.unss.defencecatalog.ui.login.LoginActivity
import com.unss.defencecatalog.util.AlertUtil
import com.unss.defencecatalog.util.Const
import com.unss.defencecatalog.util.NetworkUtil

class SplashActivity : AppCompatActivity() {

    var countDownTimer : CountDownTimer?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initSplash()
    }

    private fun initSplash() {
        countDownTimer = object : CountDownTimer(Const.TIMER_MS, Const.TIMER_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
              openScreen()
            }
        }.start()
    }

    private fun openScreen() {
        if (NetworkUtil.netCheck(applicationContext)) {
            val loginIntent = Intent(this@SplashActivity,  LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        } else {
            AlertUtil.appAlert(
                this,
                getString(R.string.no_connection),
                getString(R.string.sure_for_connect),
                true
            )
        }
    }
}
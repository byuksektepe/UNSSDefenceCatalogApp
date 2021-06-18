package com.unss.defencecatalog.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.util.Patterns
import com.unss.defencecatalog.R
import com.unss.defencecatalog.data.model.Kullanicilar
import com.unss.defencecatalog.data.model.KullanicilarResponse
import com.unss.defencecatalog.databinding.ActivityLoginBinding
import com.unss.defencecatalog.databinding.ActivitySplashBinding
import com.unss.defencecatalog.ui.categories.MainActivity
import com.unss.defencecatalog.util.AlertUtil

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    var kullaniciViewModel = KullaniciViewModel()
    var kullaniciDatas: KullanicilarResponse? = null
    var loggedUser: Kullanicilar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()

        binding.apply {
            btnGiris.setOnClickListener {
                performLogin()
            }
        }
    }

    private fun performLogin() {
        binding.apply {
            btnGiris.setOnClickListener {
                if (
                    !edtTxtUserName.text.isNullOrBlank() &&
                    Patterns.EMAIL_ADDRESS.matcher(edtTxtUserName.text).matches() &&
                    !edtTxtPassword.text.isNullOrBlank()
                ) {
                    findUser(edtTxtUserName.text)
                    if (loggedUser!= null && loggedUser!!.Sifre == edtTxtPassword.text.toString()){
                        val categoryIntent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(categoryIntent)
                        finish()
                    } else {
                        AlertUtil.invalidAlert(this@LoginActivity, "Giriş Hatalı", "Kullanıcı adı ve şifreniz eşleşmedi.")
                    }
                } else {
                    AlertUtil.invalidAlert(this@LoginActivity, "Kullanıcı Formu Boş Girildi", "Lütfen giriş bilgilerinizi doldurun.")
                }
            }
        }
    }

    private fun findUser(text: Editable?) {
        kullaniciDatas!!.Kullanicilar?.run {
            var foundUsers = this.filter { it.Email.contains(text.toString()) }
            loggedUser = foundUsers.firstOrNull()
        }
    }

    private fun initViewModel() {
        kullaniciViewModel.apply {
            kullaniciLiveData.observe(this@LoginActivity, androidx.lifecycle.Observer {
                Log.e("Artyom", "observe: " + it.toString())
                kullaniciDatas = it
            })

            loading?.observe(this@LoginActivity, androidx.lifecycle.Observer {
                Log.e("Artyom", "loading:")

            })

            error?.observe(this@LoginActivity, androidx.lifecycle.Observer {
                Log.e("Artyom", "error:")
            })
        }
    }
}
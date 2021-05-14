package com.sincronizacionweb.mitienda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.sincronizacionweb.mitienda.service.StoreRepository
import com.sincronizacionweb.mitienda.utils.MySharedPreferences
import com.sincronizacionweb.mitienda.utils.TOKEN
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.ConnectException

class SplashActivity : AppCompatActivity() {

    private lateinit var myPreferences: MySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        myPreferences = MySharedPreferences(this)

        val animacion = AnimationUtils.loadAnimation(this, R.anim.animacion)
        ivLogoSwAccent.startAnimation(animacion)


        animacion.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                validateToken()
                //finish()
            }

            override fun onAnimationStart(animation: Animation?) {

            }

        })

    }

    private fun validateToken() {

        val token = myPreferences.getString(TOKEN)

        if (token != null){

            autoLogin(token)

        }else{
           startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun autoLogin(token: String) {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = withContext(Dispatchers.Default){
                    val storeRepository = StoreRepository()
                    storeRepository.autologin(token)
                }
                if (response){
                    startActivity(Intent(this@SplashActivity, ShowProductsActivity::class.java))
                    finish()
                }else{
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }

            }catch (e: Exception) {
                if (e is ConnectException) {
                    startActivity(Intent(this@SplashActivity, ShowProductsActivity::class.java))
                } else {
                    Toast.makeText(this@SplashActivity, "${e.message}", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }

            }

        }
    }
}
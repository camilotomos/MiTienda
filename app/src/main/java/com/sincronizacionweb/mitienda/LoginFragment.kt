package com.sincronizacionweb.mitienda

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sincronizacionweb.mitienda.databinding.FragmentLoginBinding
import com.sincronizacionweb.mitienda.models.LoginModel
import com.sincronizacionweb.mitienda.service.StoreRepository
import com.sincronizacionweb.mitienda.utils.MySharedPreferences
import com.sincronizacionweb.mitienda.utils.TOKEN
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var myPreferences: MySharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myPreferences =MySharedPreferences(this.context!!)

        binding.buttonLogIn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                      val responseLogin = withContext(Dispatchers.Default){
                          val storeRepository = StoreRepository()
                          storeRepository.login(LoginModel(binding.editTextEmail.text.toString(), binding.editTextPassword.text.toString()))
                      }
                    myPreferences.addString(TOKEN, responseLogin.token)
                    //Toast.makeText(this@LoginFragment.context, responseLogin.token, Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@LoginFragment.context, ShowProductsActivity::class.java))
                }catch (e: Exception){
                    Toast.makeText(this@LoginFragment.context, "${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}
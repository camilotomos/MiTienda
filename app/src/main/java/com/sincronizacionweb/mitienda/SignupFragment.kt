package com.sincronizacionweb.mitienda

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sincronizacionweb.mitienda.databinding.FragmentSignupBinding
import com.sincronizacionweb.mitienda.models.SignupModel
import com.sincronizacionweb.mitienda.service.StoreRepository
import com.sincronizacionweb.mitienda.utils.MySharedPreferences
import com.sincronizacionweb.mitienda.utils.TOKEN
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var myPreferences: MySharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myPreferences = MySharedPreferences(this.context!!)

        binding.buttonSignUp.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val responseSignup = withContext(Dispatchers.Default){
                        val storeRepository = StoreRepository()
                        storeRepository.signup(
                                SignupModel(
                                        binding.editTextNombre.text.toString(),
                                        binding.editTextEmail.text.toString(),
                                        binding.editTextPassword.text.toString()
                                )
                        )
                    }

                    myPreferences.addString(TOKEN,responseSignup.token)
                    //Toast.makeText(this@SignupFragment.context, responseSignup.token, Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@SignupFragment.context, ShowProductsActivity::class.java))

                }catch (exception: Exception){
                    Toast.makeText(this@SignupFragment.context, "${exception.message}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}
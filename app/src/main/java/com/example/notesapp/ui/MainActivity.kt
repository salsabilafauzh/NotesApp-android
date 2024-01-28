package com.example.notesapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.model.AuthModel
import com.example.notesapp.network.RetfofitBuilder
import com.example.notesapp.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var authRepository: AuthRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authRepository = AuthRepository(RetfofitBuilder.retrofit)
        binding.signIn.setOnClickListener {
            val user = AuthModel(
                binding.edtUsername.text.toString(),
                binding.edtPassword.text.toString()
            )
            GlobalScope.launch {
                try {
                    val loginResponse = withContext(Dispatchers.IO) {
                        authRepository.postLogin(user)
                    }
                    if (loginResponse.isSuccessful) {
                        val accessToken = loginResponse.body()!!.data!!.accessToken
                        if (!accessToken.isNullOrBlank()) {
                            saveCredentials(accessToken)

                            val intent = Intent(this@MainActivity, Dashboard::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Log.e("MainActivity", "null accessToken: ${loginResponse!!.message()}")
                        }
                    }
                } catch (e: Exception) {
                    Log.e("MainActivity", "Login case: ${e!!.message}")
                }

            }
            binding.createNewAccount.setOnClickListener {
                val intent = Intent(this@MainActivity, SignUp::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun saveCredentials(accessToken: String) {
        // You should implement a secure storage mechanism to save the access token
        // For simplicity, you can use SharedPreferences, but for a production app,
        // consider using a more secure storage solution, such as Android Keystore.
        val sharedPreferences = getSharedPreferences("Credentials", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("ACCESS_TOKEN", accessToken)
        editor.apply()
    }
}





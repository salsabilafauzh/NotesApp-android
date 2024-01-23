package com.example.notesapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.databinding.ActivitySignUpBinding
import com.example.notesapp.model.UserModel
import com.example.notesapp.network.RetfofitBuilder
import com.example.notesapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var userRepository: UserRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_sign_up)
        setContentView(binding.root)

        binding.signInHere.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        userRepository = UserRepository(RetfofitBuilder.retrofit)



        binding.signUp.setOnClickListener {
            val newUser = UserModel(
                binding.edtUsername.text.toString(),
                binding.edtPassword.text.toString(),
                binding.edtFullname.text.toString()
            )
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val createdUserResponse = withContext(Dispatchers.IO) {
                        userRepository.createUser(newUser)
                    }
                    // Handle the response
                    if (createdUserResponse.code() == 201) {
                        val createdUser = createdUserResponse.body()!!.data
                        Log.d("SignUp", "User created successfully: ${createdUserResponse.body()!!}")
                        // Process the created user
                    } else {
                        // Handle error or show a message
                        Log.e(
                            "SignUp",
                            "Error creating user: ${createdUserResponse.message()}"
                        )
                    }
                } catch (e: Exception) {
                    // Handle exceptions
                    Log.e("SignUp", "Exception: ${e!!.message}")
                }
            }
        }
    }

}
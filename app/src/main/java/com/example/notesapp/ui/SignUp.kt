package com.example.notesapp.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.notesapp.databinding.ActivitySignUpBinding
import com.example.notesapp.model.ErrorResponse
import com.example.notesapp.model.UserModel
import com.example.notesapp.network.RetfofitBuilder
import com.example.notesapp.repository.UserRepository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var userRepository: UserRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
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
                    if (createdUserResponse.isSuccessful) {
                        this@SignUp.showAlert(
                            "Akun berhasil dibuat",
                            isSuccess = true,
                            createdUserResponse.body()!!.success
                        )

                    } else {
                        val errorJson = createdUserResponse.errorBody()!!.string()
                        val moshi = Moshi.Builder().build()
                        val errorAdapter: JsonAdapter<ErrorResponse> =
                            moshi.adapter(ErrorResponse::class.java)
                        val errorResponse: ErrorResponse? = errorAdapter.fromJson(errorJson)
                        this@SignUp.showAlert(
                            errorResponse!!.message,
                            isSuccess = false,
                            errorResponse.success

                        )
                    }
                } catch (e: Exception) {
                    Log.e("SignUp", "Exception: ${e!!.message}")
                }
            }
        }
    }

    private fun showAlert(message: String, isSuccess: Boolean, title: Boolean): Boolean {
        val builder = AlertDialog.Builder(this@SignUp)
        builder.setTitle(isSuccess.toString())
        builder.setMessage(message)

        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            if (isSuccess) {
                val intent = Intent(this@SignUp, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        builder.setPositiveButton("OK", positiveButtonClick)

        val alertDialog = builder.create()

        try {
            alertDialog.show()
        } catch (e: Exception) {
            // Handle potential WindowLeaked exception
            Log.e("SignUp", "Error showing dialog: ${e.message}")
        }

        // Return false if the dialog couldn't be shown
        return false
    }


}
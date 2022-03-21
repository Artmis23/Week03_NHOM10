package com.example.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Profile : AppCompatActivity() {
    var txtFullName: TextView? = null
    var txtEmail: TextView? = null
    var txtPhone: TextView? = null

    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val text: TextView = findViewById(R.id.txtEmail) as TextView
        var data = intent.getStringExtra("Data")
        text.setText(data)

        initView()
        getDataFromSignUp();
    }

    private fun getDataFromSignUp() {
        val intent2 = intent
        val bundle2 = intent.getBundleExtra("Data")
        val fullNameBundle = bundle2?.getString("name")
        val emailBundle = bundle2?.getString("email")

        txtFullName?.setText(fullNameBundle)
        txtEmail?.setText(emailBundle)


    }

    private fun initView() {
        txtFullName = findViewById(R.id.txtFullName)
        txtEmail = findViewById(R.id.txtEmail)
        txtPhone = findViewById(R.id.txtPhone)

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            txtFullName?.setOnClickListener(View.OnClickListener { view ->
                val builder = AlertDialog.Builder(this@Profile)
                val viewGroup = findViewById<ViewGroup>(android.R.id.content)

                val dialogView =
                    LayoutInflater.from(view.context)
                        .inflate(R.layout.dialog_signup_fullname, viewGroup, false)
                builder.setView(dialogView)
                val alertDialog = builder.create()
                alertDialog.show()

                val edtDialogFullName = alertDialog.findViewById<EditText>(R.id.edtDialogFullName)
                val txtDialogCancelFullName =
                    alertDialog.findViewById<TextView>(R.id.txtDialogCancelFullName)
                val btnDialogEnterFullName =
                    alertDialog.findViewById<TextView>(R.id.btnDialogEnterFullName)

                txtDialogCancelFullName.setOnClickListener {
                    alertDialog.dismiss()
                }

                btnDialogEnterFullName.setOnClickListener {
                    val fullName: String = edtDialogFullName.getText().toString().trim { it <= ' ' }
                    txtFullName?.setText(fullName)
                    alertDialog.cancel()
                }
            })
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            txtEmail?.setOnClickListener(View.OnClickListener { view ->
                val builder = AlertDialog.Builder(this@Profile)
                val viewGroup = findViewById<ViewGroup>(android.R.id.content)

                val dialogView =
                    LayoutInflater.from(view.context)
                        .inflate(R.layout.dialog_signup_email, viewGroup, false)
                builder.setView(dialogView)
                val alertDialog = builder.create()
                alertDialog.show()

                val edtDialogEmail = alertDialog.findViewById<EditText>(R.id.edtDialogEmail)
                val txtDialogCancelEmail =
                    alertDialog.findViewById<TextView>(R.id.txtDialogCancelEmail)
                val btnDialogEnterEmail =
                    alertDialog.findViewById<TextView>(R.id.btnDialogEnterEmail)

                txtDialogCancelEmail.setOnClickListener {
                    alertDialog.dismiss()
                }

                btnDialogEnterEmail.setOnClickListener {
                    val email: String = edtDialogEmail.getText().toString().trim { it <= ' ' }
                    txtEmail?.setText(email)
                    alertDialog.cancel()
                }
            })
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            txtPhone?.setOnClickListener(View.OnClickListener { view ->
                val builder = AlertDialog.Builder(this@Profile)
                val viewGroup = findViewById<ViewGroup>(android.R.id.content)

                val dialogView =
                    LayoutInflater.from(view.context)
                        .inflate(R.layout.dialog_signup_password, viewGroup, false)
                builder.setView(dialogView)
                val alertDialog = builder.create()
                alertDialog.show()

                val edtDialogPassword = alertDialog.findViewById<EditText>(R.id.edtDialogPassword)
                val txtDialogCancelPassword =
                    alertDialog.findViewById<TextView>(R.id.txtDialogCancelPassword)
                val btnDialogEnterPassword =
                    alertDialog.findViewById<TextView>(R.id.btnDialogEnterPassword)

                txtDialogCancelPassword.setOnClickListener {
                    alertDialog.dismiss()
                }

                btnDialogEnterPassword.setOnClickListener {
                    val password: String = edtDialogPassword.getText().toString().trim { it <= ' ' }
                    txtPhone?.setText(password)
                    alertDialog.cancel()
                }
            })
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false

    }
}
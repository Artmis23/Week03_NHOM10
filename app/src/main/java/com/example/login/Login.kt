package com.example.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login.SignUp

class Login : AppCompatActivity(), View.OnClickListener {
    private var edtEmail: EditText? = null
    private var edtPassWord: EditText? = null
    private var btnLogin: TextView? = null
    private var txtSignUp: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edtEmail = findViewById<View>(R.id.edtEmail) as EditText
        edtPassWord = findViewById<View>(R.id.edtPassWord) as EditText
        btnLogin = findViewById<View>(R.id.btnLogin) as TextView
        btnLogin!!.setOnClickListener(this)
        txtSignUp = findViewById<View>(R.id.txtSignUp) as TextView
        txtSignUp!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.txtSignUp -> {
                val intent2 = Intent(this@Login, SignUp::class.java)
                intent2.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent2)
                finish()
            }
            R.id.btnLogin -> LoginUser()
        }
    }

    private fun LoginUser() {
        val email = edtEmail!!.text.toString().trim { it <= ' ' }
        val pass = edtPassWord!!.text.toString().trim { it <= ' ' }

        val intent = intent
        val bundle = intent.getBundleExtra("Data")
        val emailBundle = bundle?.getString("email")
        val passwordBundle = bundle?.getString("pass")
        val fullNameBundle = bundle?.getString("name")


        val EMAIL = emailBundle
        val PASS = passwordBundle

        if (email.isEmpty()) {
            edtEmail!!.error = "Email is reqired!"
            edtEmail!!.requestFocus()
            return
        }
        if (pass.isEmpty()) {
            edtPassWord!!.error = "Pass is reqired!"
            edtPassWord!!.requestFocus()
            return
        }
        if (email != EMAIL) {
            Toast.makeText(this@Login, "Email is invalid", Toast.LENGTH_LONG).show()
            return
        }
        if (pass != PASS) {
            Toast.makeText(this@Login, "Pass is invalid", Toast.LENGTH_LONG).show()
            return
        } else {
            val intent = Intent(this@Login, Profile::class.java)
            intent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("Data", email)
            startActivity(intent)
            finish()
        }

        val intent2 = Intent(this@Login, Profile::class.java)
        val bundle2 = Bundle()
        bundle2.putString("email", email)
        bundle2.putString("name", fullNameBundle)
        intent2.putExtra("Data", bundle2)
        startActivity(intent2)

    }
}
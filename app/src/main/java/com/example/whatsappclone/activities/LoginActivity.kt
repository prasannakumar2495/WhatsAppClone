package com.example.whatsappclone.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.whatsappclone.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var mDataBase: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        loginButtonID.setOnClickListener {
            var email = loginEmailE.text.toString().trim()
            var password = loginPasswordEt.text.toString().trim()
            if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Sorry Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        /*below code is used to verify the login details. we are comparing the login details
         with the details in the firebase. */
        mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                //pk@gmail.com --> [pk],[gmail.com]
                var userName = email.split("@")[0]
                var dashBoardIntent = Intent(this, DashboardActivity::class.java)
                dashBoardIntent.putExtra("name", userName)
                startActivity(dashBoardIntent)
                finish()
            } else {

                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
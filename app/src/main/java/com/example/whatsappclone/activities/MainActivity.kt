package com.example.whatsappclone.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.whatsappclone.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var user: FirebaseUser? = null
    var mAuthListner: FirebaseAuth.AuthStateListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //this code will be used to save the user logged in instance, so that there is
        // no need to login again and again into the application.

        mAuth = FirebaseAuth.getInstance()
        mAuthListner = FirebaseAuth.AuthStateListener { firebaseAuth: FirebaseAuth ->
            user = firebaseAuth.currentUser
            if (user != null) {
                //let's go to dashboard
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Not Signed In", Toast.LENGTH_SHORT).show()
            }
        }
        //functionalities of the buttons in the main scree.
        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        createAccount.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }
    }

    //we will run the code of mAuthListner to save the user information.
    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener { mAuthListner }
    }

    //when the activity memory is full, we will logout the user.
    override fun onStop() {
        super.onStop()
        if (mAuthListner != null) {
            mAuth!!.removeAuthStateListener(mAuthListner!!)
        }
    }
}
package com.example.whatsappclone.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.whatsappclone.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_status.*

class StatusActivity : AppCompatActivity() {
    var mDataBase: DatabaseReference? = null
    var mCurrentUSer: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        supportActionBar!!.title = "Status"
        if (intent.extras != null) {
            var oldStatus = intent.extras!!.get("status")
            statusUpdateET.setText(oldStatus.toString())
        }
        if (intent.extras!!.equals(null)) {
            statusUpdateET.setText("Enter Your New Status")
        }
        statusUpdateBtn.setOnClickListener {
            //getting the instance of the current logged in user.
            mCurrentUSer = FirebaseAuth.getInstance().currentUser
            //getting the user ID of the logged in user.
            var userID = mCurrentUSer!!.uid
            //finding the user with the required user ID --> userID
            mDataBase = FirebaseDatabase.getInstance()
                .reference.child("Users").child(userID)
            //storing the new entered status in status variable
            var status = statusUpdateET.text.toString().trim()
            //setting the new updated status into firebase.
            mDataBase!!.child("status").setValue(status).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        this, "Status is Updated",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this, SettingsActivity::class.java))
                } else {
                    Toast.makeText(
                        this, "Status is not Updated",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
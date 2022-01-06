package com.example.whatsappclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var mDataBase: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        mAuth = FirebaseAuth.getInstance()
        accountCreateACTBtn.setOnClickListener {
            var email = accountEmailEt.text.toString().trim()
            var password = accountPasswordEt.text.toString().trim()
            var displayName = accountDisplayNameEt.text.toString().trim()
            if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) ||
                !TextUtils.isEmpty(displayName)
            ) {
                createAccount(email, password, displayName)
            } else {
                Toast.makeText(
                    this, "Please enter all the details!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun createAccount(email: String, password: String, displayName: String) {
        mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                var currentUser = mAuth!!.currentUser
                var userID = currentUser!!.uid

                /*below is to set the data into database. right now we are making the
                data ready. */
                mDataBase = FirebaseDatabase.getInstance().reference
                    .child("Users").child(userID)

                //below data will be added to the above obtained user ID.
                //we can find the same uid, in the authentication and also in realtime database.
                var userObject = HashMap<String, String>()
                userObject["display_name"] = displayName
                userObject["status"] = "Hello there..."
                userObject.put("image", "default")
                userObject.put("thum_image", "default")

                mDataBase!!.setValue(userObject).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            this, "User is Created",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this, "User is not Created",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {

            }
        }
    }
}
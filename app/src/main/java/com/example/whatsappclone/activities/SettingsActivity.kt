package com.example.whatsappclone.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.whatsappclone.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    var mDataBase: DatabaseReference? = null
    var mCurrentUSer: FirebaseUser? = null
    var mStorageRef: StorageReference? = null
    var GALLERY_ID: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        mCurrentUSer = FirebaseAuth.getInstance().currentUser
        //got the uid,then we will move on to set the path required and fetch the required data.
        var userID = mCurrentUSer!!.uid
        mDataBase = FirebaseDatabase.getInstance().reference.child("Users").child(userID)

        mDataBase!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //fetching display name from firebase.
                var displayName = dataSnapshot!!.child("display_name").value
                var image = dataSnapshot!!.child("image").value
                var userStatus = dataSnapshot!!.child("status").value
                var thum_image = dataSnapshot!!.child("thum_image").value

                settingsStatusText.text = userStatus.toString()
                settingDisplayName.text = displayName.toString()
            }

            override fun onCancelled(databaseErrorSnapshot: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        settingsChangeStatus.setOnClickListener {
            var intent = Intent(this, StatusActivity::class.java)
            intent.putExtra("status", settingsStatusText.text.toString().trim())
            startActivity(intent)
        }
        settingChangeImage.setOnClickListener {
            var galleryIntent = Intent()
            galleryIntent.type = "image/*"
            galleryIntent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(
                    galleryIntent,
                    "SELECT_IMAGE"
                ), GALLERY_ID
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
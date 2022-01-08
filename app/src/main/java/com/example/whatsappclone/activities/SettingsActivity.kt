package com.example.whatsappclone.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.net.toUri
import com.example.whatsappclone.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import id.zelory.compressor.Compressor
import kotlinx.android.synthetic.main.activity_settings.*
import java.io.ByteArrayOutputStream
import java.io.File

class SettingsActivity : AppCompatActivity() {
    var mDataBase: DatabaseReference? = null
    var mCurrentUSer: FirebaseUser? = null
    var mStorageRef: StorageReference? = null
    var GALLERY_ID: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        mCurrentUSer = FirebaseAuth.getInstance().currentUser
        mStorageRef = FirebaseStorage.getInstance().reference
        //got the uid,then we will move on to set the path required and fetch the required data.
        var userID = mCurrentUSer!!.uid
        mDataBase = FirebaseDatabase.getInstance().reference.child("Users").child(userID)

        //fetching the status and displayname from firebase.
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
        //sending the current status into the status screen.
        settingsChangeStatus.setOnClickListener {
            var intent = Intent(this, StatusActivity::class.java)
            intent.putExtra("status", settingsStatusText.text.toString().trim())
            startActivity(intent)
        }
        //to open gallery in the device
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

    //to crop the image, to compress the image and to upload the image to firebase.
    //not able to compress and upload. only crop had been written successfully.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_ID && resultCode == Activity.RESULT_OK) {
            var image: Uri? = data!!.data
            //croping the image
            CropImage.activity(image)
                .setAspectRatio(1, 1)
                .start(this)
        }
        //super.onActivityResult(requestCode, resultCode, data)
    }
}
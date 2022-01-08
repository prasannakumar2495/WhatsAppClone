package com.example.whatsappclone.adapters

import android.content.Context
import com.example.whatsappclone.R
import com.example.whatsappclone.models.Users
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference

class UsersAdapter(databaseQuery:DatabaseReference,var context: Context)
    :FirebaseRecyclerAdapter<Users,UsersAdapter.ViewHolder>(){
}
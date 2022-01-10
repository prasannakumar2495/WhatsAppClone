package com.example.whatsappclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappclone.R
import com.example.whatsappclone.models.Users
import com.google.firebase.database.DatabaseReference

class UsersFragment : Fragment() {
    lateinit var dbRef:DatabaseReference
    lateinit var userRecyclerView:RecyclerView 
    lateinit var usersArrayList: ArrayList<Users>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false )
    }

}
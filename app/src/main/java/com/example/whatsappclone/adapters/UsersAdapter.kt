package com.example.whatsappclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappclone.R
import com.example.whatsappclone.models.Users
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference

class UsersAdapter(val userList: ArrayList<Users>, var context: Context) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.usersrow, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UsersAdapter.ViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.display_name.text = currentItem.display_name
        holder.status.text = currentItem.status
        holder.image.text = currentItem.image
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val display_name: TextView = itemView.findViewById(R.id.userName)
        val image: TextView = itemView.findViewById(R.id.usersProfile)
        val status: TextView = itemView.findViewById(R.id.userStatus)
    }
}
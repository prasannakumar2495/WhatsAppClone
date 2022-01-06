package com.example.whatsappclone.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.whatsappclone.fragments.ChatsFragment
import com.example.whatsappclone.fragments.UsersFragment

class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        //in this method, we are setting up the tabview with fragments.
        when (position) {
            0 -> {
                return UsersFragment()
            }
            1 -> {
                return ChatsFragment()
            }
        }
        return null!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        //setting up the tab names
        when(position){
            0 -> return "Users"
            1 -> return "Chats"
        }
        return null!!
    }
}
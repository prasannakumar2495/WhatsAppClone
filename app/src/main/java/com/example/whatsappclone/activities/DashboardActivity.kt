package com.example.whatsappclone.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.whatsappclone.R
import com.example.whatsappclone.adapters.SectionPagerAdapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        //setting the title in the appBar
        supportActionBar!!.title = "Dashboard"
        //to show the fragments in the dashboard
        var sectionAdapter: SectionPagerAdapter? = null

        sectionAdapter = SectionPagerAdapter(supportFragmentManager)
        //if you use ViewPager2, we will be facing an issue with the adapter.
        //So, i have used ViewPager only
        dashbaordViewPagerID.adapter = sectionAdapter
        mainTabs.setupWithViewPager(dashbaordViewPagerID)
        //setting up the text colors in the tabs.
        mainTabs.setTabTextColors(Color.WHITE, Color.GREEN)

        if (intent.extras != null) {
            var userName = intent.extras!!.get("name")
            Toast.makeText(this, userName.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    //to display something in the app bar.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item != null) {
            if (item.itemId == R.id.logoutID) {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            if (item.itemId == R.id.settingsID) {
                startActivity(Intent(this, SettingsActivity::class.java))

            }
        }
        return true
    }
}
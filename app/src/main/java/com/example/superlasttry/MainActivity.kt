package com.example.superlasttry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.superlasttry.ui.Nav_header
import com.example.superlasttry.ui.tools.SessionMarks
import com.example.superlasttry.ui.tools.RubControl
import com.example.superlasttry.ui.settings.settings
import com.example.superlasttry.ui.tools.News
import com.google.android.material.navigation.NavigationView
class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val groupName = intent.getStringExtra("groupName")

        val navigationView: NavigationView = findViewById(R.id.navigationView)
        val headerView = navigationView.getHeaderView(0)

        val usernameTextView = headerView.findViewById<TextView>(R.id.user_name)
        val gmailTextView = headerView.findViewById<TextView>(R.id.Gmail)

        usernameTextView.text = "$firstName $lastName"
        gmailTextView.text = groupName

        val HeadPage = findViewById<TextView>(R.id.HeadPage)
        HeadPage.isSelected = true

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.navigationView)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val imageMenuButton = findViewById<ImageButton>(R.id.imageMenu)
        imageMenuButton.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        navView.setNavigationItemSelectedListener {

            it.isChecked = true
            val SettingsButton = findViewById<Button>(R.id.settingsButton)
            SettingsButton.setOnClickListener{
                replaseFragment(settings())
                HeadPage.setText("Настройки")
            }
            when(it.itemId) {
                R.id.RubContr -> {
                    replaseFragment(RubControl())
                HeadPage.setText("Рубежный контроль")
                    Toast.makeText(applicationContext,"Выполняется переход",Toast.LENGTH_SHORT).show()
                }
                R.id.MarkPract -> {
                    Toast.makeText(applicationContext,"Выполняется переход",Toast.LENGTH_SHORT).show()
                }

                R.id.MarkSess -> {
                    replaseFragment(SessionMarks())
                    HeadPage.setText("Оценки за сессию")
                    Toast.makeText(applicationContext,"Выполняется переход",Toast.LENGTH_SHORT).show()
                }

                R.id.MarkPract -> {
                    Toast.makeText(applicationContext,"Выполняется переход",Toast.LENGTH_SHORT).show()
                }

                R.id.StudentStudyPlan -> {
                    Toast.makeText(applicationContext,"Выполняется переход",Toast.LENGTH_SHORT).show()
                }

                R.id.UMKD -> {
                    Toast.makeText(applicationContext,"Выполняется переход",Toast.LENGTH_SHORT).show()
                }

                R.id.Recomend_lit -> {
                    Toast.makeText(applicationContext,"Выполняется переход",Toast.LENGTH_SHORT).show()
                }

                R.id.schedule -> {
                    Toast.makeText(applicationContext,"Выполняется переход",Toast.LENGTH_SHORT).show()
                }

                R.id.News -> {
                    replaseFragment(News())
                    HeadPage.setText("Новости")
                    Toast.makeText(applicationContext, "Выполняется переход", Toast.LENGTH_SHORT).show()
                }
                R.id.Online_maps -> {
                    Toast.makeText(applicationContext,"Выполняется переход",Toast.LENGTH_SHORT).show()
                }

            }
            true
        }



    }


    private fun replaseFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    fun openCloseNavigationDrawer() {

    }


}
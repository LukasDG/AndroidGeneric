package be.kdg.integratieproject.services

import android.content.Context
import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import be.kdg.integratieproject.*

fun initMenuListener(context: Context): BottomNavigationView.OnNavigationItemSelectedListener{
    val menuListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId){
            R.id.action_home -> {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(context, intent, null)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_search -> {
                val intent = Intent(context, SearchActivity::class.java)
                startActivity(context, intent, null)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_profile -> {
                val intent = Intent(context, ProfileActivity::class.java)
                startActivity(context, intent, null)
                return@OnNavigationItemSelectedListener true
            }
            else -> {
                return@OnNavigationItemSelectedListener false
            }
        }
    }

    return menuListener
}
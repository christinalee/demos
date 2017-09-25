package com.examples.lee.demo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import com.examples.lee.demo.unsplash.overview.UserCollectionsActivity

// TODO: MVP this
class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: fast launch into a playground and store that pref
        with(findViewById<AppCompatButton>(R.id.constraintLayoutButton)) {
            setOnClickListener {
                startFeedActivity()
            }
        }
    }

    private fun startFeedActivity() {
        val intent = Intent(this, UserCollectionsActivity::class.java)
        startActivity(intent)
    }

}

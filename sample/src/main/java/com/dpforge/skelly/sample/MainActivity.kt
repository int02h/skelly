package com.dpforge.skelly.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_skeleton_list).setOnClickListener {
            startActivity(Intent(this, SkeletonListActivity::class.java))
        }

        findViewById<Button>(R.id.btn_skeleton_factory).setOnClickListener {
            startActivity(Intent(this, SkeletonFactoryActivity::class.java))
        }
    }
}
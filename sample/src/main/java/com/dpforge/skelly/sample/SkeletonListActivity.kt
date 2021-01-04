package com.dpforge.skelly.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SkeletonListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skeleton_list)

        val adapter = SkeletonAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.btn_add).setOnClickListener {
            adapter.add()
        }

        findViewById<Button>(R.id.btn_clear).setOnClickListener {
            adapter.clear()
        }
    }
}
package com.dpforge.skelly.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.dpforge.skelly.factory.SkeletonLayoutFactory

class SkeletonFactoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_factory)

        val inflater = LayoutInflater.from(this)
        val container: LinearLayout = findViewById(R.id.container)

        SkeletonLayoutFactory.Default.replaceInViewGroup(container.findViewById(R.id.in_layout))

        SkeletonLayoutFactory.Default.inflateFrom(
            context = this,
            layoutId = R.layout.avatar_title_subtitle,
            parent = container,
            attachToRoot = true
        )

        val viewToReplace = inflater.inflate(
            R.layout.avatar_title_subtitle,
            container,
            false
        ) as LinearLayout
        SkeletonLayoutFactory.Default.replaceInViewGroup(viewToReplace)
        container.addView(viewToReplace)
    }
}
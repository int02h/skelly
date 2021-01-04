package com.dpforge.skelly.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dpforge.skelly.SkeletonView

internal class SkeletonAdapter : RecyclerView.Adapter<SkeletonAdapter.ViewHolder>() {

    private var count: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.skeleton_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.skeleton.startColor = START_COLORS.getColor(position)
        holder.skeleton.endColor = END_COLORS.getColor(position)
    }

    override fun getItemCount(): Int = count

    fun add() {
        val insertPosition = count
        count++
        notifyItemInserted(insertPosition)
    }

    fun clear() {
        val removeCount = count
        count = 0
        notifyItemRangeRemoved(0, removeCount)
    }

    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val skeleton: SkeletonView = view.findViewById(R.id.skeleton)
    }

    private fun IntArray.getColor(index: Int): Int = (0xFF000000 + get(index % size)).toInt()

    private companion object {
        val START_COLORS = intArrayOf(
            0x90CAF9, // blue 200
            0xEF9A9A, // red 200
            0xA5D6A7, // green 200
            0xFFCC80, // orange 200
            0xEEEEEE, // grey 200
        )
        val END_COLORS = intArrayOf(
            0x1E88E5, // blue 600
            0xE53935, // red 600
            0x43A047, // green 600
            0xFB8C00, // orange 600
            0x757575, // grey 600
        )
    }

}
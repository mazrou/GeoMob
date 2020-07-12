package com.e.geomob.ui.detail.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.e.geomob.R
import com.e.geomob.data.model.Personality
import com.e.geomob.data.model.SlideItem
import kotlinx.android.synthetic.main.layout_image_item.view.*
import kotlinx.android.synthetic.main.personality_layout_list_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch



class ImageAdapter(

)  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: List<SlideItem>? = listOf<SlideItem>()

    inner class ImageViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(
        itemView
    ) {
        fun bind(item: SlideItem) {
            itemView.image_slide_item.setImageResource(item.image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_image_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        var length = 0
        list?.let {
            length = it.size
        }
        return length
    }

    fun submitList(newList: List<SlideItem>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        list?.let {
            when (holder) {
                is ImageViewHolder -> {
                    holder.bind(it[position])

                }
            }
        }

    }
}
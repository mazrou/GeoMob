package com.e.geomob.ui.detail.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.geomob.R

import com.e.geomob.data.model.Personality
import kotlinx.android.synthetic.main.personality_layout_list_item.view.*

class PersonalityAdapter(

)  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: List<Personality>? = listOf<Personality>()

    inner class HistoryViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(
        itemView
    ){
        fun bind(personality: Personality){
            itemView.personPicView.setImageResource(personality.image)
            itemView.personNameView.text = personality.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return HistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.personality_layout_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() :  Int {
        var length  = 0
        list?.let {
            length = it.size
        }
        return length
    }

    fun submitList(newList : List<Personality>){
        list = newList
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        list?.let {
            when (holder) {
                is HistoryViewHolder -> {
                    holder.bind(it[position])

                }
            }
        }


    }
}
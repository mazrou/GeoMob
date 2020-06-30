package com.e.geomob.ui.detail.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.geomob.R

import com.e.geomob.data.model.Personality
import kotlinx.android.synthetic.main.history_list_item.view.*

class PersonalityAdapter(
    private val list: List<Personality>
)  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    inner class HistoryViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(
        itemView
    ){
        fun bind(personality: Personality){
            itemView.image_history.setImageResource(personality.image!!)
            itemView.title_history.text = personality.name
            itemView.date_history.text = personality.birthDay
            itemView.content_history.text  = personality.history
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return HistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.history_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is HistoryViewHolder -> {
                holder.bind(list[position])

            }
        }


    }
}
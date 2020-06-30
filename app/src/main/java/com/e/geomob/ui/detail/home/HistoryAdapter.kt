package com.e.geomob.ui.detail.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.geomob.R

import com.e.geomob.data.model.HistoricalEvent
import kotlinx.android.synthetic.main.history_list_item.view.*

class HistoryAdapter(
    private var list: List<HistoricalEvent>
)  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    inner class HistoryViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(
        itemView
    ){
        fun bind(historicalEvent: HistoricalEvent){
          itemView.image_history.setImageResource(historicalEvent.image!!)
            itemView.title_history.text = historicalEvent.title
            itemView.date_history.text = historicalEvent.date
            itemView.content_history.text  = historicalEvent.description
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

    fun setList(historyList: List<HistoricalEvent>) {
            list = historyList
    }
}
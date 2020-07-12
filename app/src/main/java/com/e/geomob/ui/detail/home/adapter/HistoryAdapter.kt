package com.e.geomob.ui.detail.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.geomob.R

import com.e.geomob.data.model.HistoricalEvent
import kotlinx.android.synthetic.main.history_list_item.view.*

class HistoryAdapter(

)  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: List<HistoricalEvent>? = null


    inner class HistoryViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(
        itemView
    ){
        fun bind(historicalEvent: HistoricalEvent){
            itemView.title_history.text = historicalEvent.title
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

    override fun getItemCount() :  Int {
        var length  = 0
        list?.let {
            length = it.size
        }
        return length
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

    fun setList(historyList: List<HistoricalEvent>) {
            list = historyList
            notifyDataSetChanged()
    }
}
package com.e.geomob.ui.detail.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.geomob.R
import com.e.geomob.data.model.Resource


class ResourceAdapter (
) : RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder>() {

    var list = ArrayList<Resource>()


    inner class ResourceViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.findViewById<TextView>(R.id.resourceTitle)
        val desc: TextView = v.findViewById<TextView>(R.id.resourceDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        return ResourceViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_resource_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int  =  list.size


    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        val res = list[position]

        holder.title.text = res.name
        holder.desc.text = res.description
    }

    fun submitList(newList : List<Resource>){
        list =  newList as ArrayList<Resource>
        notifyDataSetChanged()
    }
}
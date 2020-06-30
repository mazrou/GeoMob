package com.e.geomob.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.blongho.country_data.World
import com.e.geomob.R
import com.e.geomob.ui.data.model.Country
import kotlinx.android.synthetic.main.country_list_item.view.*


class CountryListAdapter(
    private val interaction: Interaction? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Country>() {

        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return CountryItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.country_list_item,
                parent,
                false
            ),
            interaction
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Country>) {
        differ.submitList(list)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CountryItemViewHolder -> {
                holder.bind(differ.currentList[position])

            }
        }
    }


    inner class CountryItemViewHolder constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView)  {

        var isPlaying : Boolean = false
        private var holder: CountryItemViewHolder = this

        fun bind( country : Country) = with(itemView){
            flag_image.setImageResource(World.getFlagOf(country.name))
            country_name.text = country.name

            itemView.setOnClickListener {
                interaction!!.onItemSelected(adapterPosition, country)
            }

            audio_button.setOnClickListener{
                playOrPause()
                interaction!!.mediaPlayerButtonListener(adapterPosition , country , holder)
            }
        }

        fun playOrPause() = with(itemView){
            isPlaying = if(isPlaying){
                audio_button.setImageResource(R.drawable.ic_action_name)
                false
            }else{
                audio_button.setImageResource(R.drawable.ic_stop_name)
                true
            }
        }


    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Country)
        fun mediaPlayerButtonListener(position: Int , item: Country , holder: CountryItemViewHolder)
    }
}

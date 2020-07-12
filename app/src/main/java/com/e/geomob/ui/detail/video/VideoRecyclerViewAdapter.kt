package com.e.geomob.ui.detail.video

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.e.geomob.R
import com.e.geomob.data.model.YoutubeVideo
import kotlinx.android.synthetic.main.layout_video_list_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class VideoRecyclerViewAdapter(

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {





    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<YoutubeVideo>() {

        override fun areItemsTheSame(oldItem: YoutubeVideo, newItem: YoutubeVideo): Boolean {
            return oldItem.videoUrl == newItem.videoUrl
        }

        override fun areContentsTheSame(oldItem: YoutubeVideo, newItem: YoutubeVideo): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return YoutubeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_video_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<YoutubeVideo>) {
        differ.submitList(list)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is YoutubeViewHolder -> {
                holder.bind(differ.currentList[position])

            }
        }
    }


    inner class YoutubeViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {


        init {
            itemView.video_webView.webChromeClient = WebChromeClient()
            itemView.video_webView.webViewClient = WebViewClient()
            itemView.video_webView.settings.setAppCacheEnabled(true)
            itemView.video_webView.settings.javaScriptEnabled = true
        }

        fun bind(video: YoutubeVideo) = with(itemView) {
            val map :Map<String,String> = mapOf(Pair("text/html","utf-8"))
            video_webView.loadUrl(video.videoUrl,map)
            CoroutineScope(Main).launch {
                delay(2000)
                progressbar_Video.visibility = View.GONE
            }
        }

    }


    }


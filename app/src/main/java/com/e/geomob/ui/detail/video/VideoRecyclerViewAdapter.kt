package com.e.geomob.ui.detail.video

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.e.geomob.R
import com.e.geomob.ui.detail.home.PersonalityAdapter
import com.e.geomob.ui.main.CountryListAdapter
import kotlinx.android.synthetic.main.layout_video_list_item.view.*

class VideoRecyclerViewAdapter(
    private val interaction: IVideoPlayer?= null,
    val list: List<Int> ,
    val context : Context
)  : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    inner class VideoViewHolder(
        val item : View ,
        val context: Context
    ) : RecyclerView.ViewHolder(item) {

        var isPlaying : Boolean = false
        private var holder: VideoViewHolder = this

        fun bind(video : Int){
            val fileName = "android.resource://com.e.geomob/${video}";
        /*    val uri = Uri.parse(fileName)
            item.video_item.setVideoURI(uri)
//            interaction!!.playVideo(uri)
         //   val mediaController = MediaController(context)
           // item.video_item.setMediaController(mediaController)
            //mediaController.setAnchorView(itemView.video_item)

            item.video_controller.setOnClickListener(View.OnClickListener {
                interaction?.videoPlayerButtonListener(holder , video , item.video_item)

                isPlaying = if (isPlaying){
                    item.video_controller.setImageResource(R.drawable.ic_action_name)
                    false
                }else{
                    item.video_controller.setImageResource(R.drawable.ic_stop_name)
                    true
                }
            })*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_video_list_item,
                parent,
                false
            ) ,
            context
        )
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is VideoViewHolder -> {
                holder.bind(list[position])

            }
        }
    }

     interface IVideoPlayer{
        fun onItemClicked( videoView: VideoView)
        fun videoPlayerButtonListener(holder: VideoViewHolder , video: Int , videoView: VideoView)

    }

}



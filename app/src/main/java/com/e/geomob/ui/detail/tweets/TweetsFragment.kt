package com.e.geomob.ui.detail.tweets

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.geomob.AppExecutors
import com.e.geomob.R
import com.e.geomob.data.model.Tweet
import com.e.geomob.ui.detail.DetailActivity
import com.e.geomob.ui.detail.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_tweets.*
import kotlinx.android.synthetic.main.tweet_item_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import twitter4j.*
import twitter4j.conf.ConfigurationBuilder
import kotlin.properties.Delegates

class TweetsFragment : Fragment(R.layout.fragment_tweets) , KodeinAware {


     override val kodein  by  closestKodein()

    private val viewModel : DetailsViewModel by instance<DetailsViewModel>()

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progress_tweet.visibility = View.VISIBLE
        viewModel.country.observe(viewLifecycleOwner, Observer {country->
            web_view_twitter?.also{
                it.webChromeClient = WebChromeClient()
                it.webViewClient = WebViewClient()
                it.settings.setAppCacheEnabled(true)
                it.settings.javaScriptEnabled = true

                it.setOnTouchListener { _, _ -> true }
                it.loadUrl(  country.tweet)
                it.isVerticalScrollBarEnabled=true
                CoroutineScope(Main).launch {
                    delay(3500)
                    progress_tweet.visibility = View.GONE
                }


            }

        })

    }



}


package com.github.yashx.android_tweet_embed

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView

class AndroidTweetEmbed : WebView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, privateBrowsing: Boolean) : super(
        context,
        attrs,
        defStyleAttr,
        privateBrowsing
    )


    init {

        webChromeClient = WebChromeClient()
        settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            loadsImagesAutomatically = true
            defaultTextEncodingName = "UTF-8"
            layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
            useWideViewPort = false
        }
        isHorizontalScrollBarEnabled = false
        isVerticalScrollBarEnabled = false
        isScrollContainer = false
    }

    fun loadTweetUrl(
        url: String,
        hideMedia: Boolean? = null,
        hideThread: Boolean? = null,
        theme: TweetTheme? = null,
        doNotTrack: Boolean? = null,
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            settings.forceDark = if (theme == TweetTheme.DARK) WebSettings.FORCE_DARK_ON else WebSettings.FORCE_DARK_OFF
        }

        TwitterOembedHelper.getOembedResponseForUrl(
            url,
            hideMedia,
            hideThread,
            theme,
            doNotTrack
        ) { _, response ->
            response.body()?.html?.showTweet()
        }

    }

    fun loadTweetId(
        id: String,
        hideMedia: Boolean? = null,
        hideThread: Boolean? = null,
        theme: TweetTheme? = null,
        doNotTrack: Boolean? = null,
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            settings.forceDark = if (theme == TweetTheme.DARK) WebSettings.FORCE_DARK_ON else WebSettings.FORCE_DARK_OFF
        }

        TwitterOembedHelper.getOembedResponseForId(
            id,
            hideMedia,
            hideThread,
            theme,
            doNotTrack
        ) { _, response ->
            response.body()?.html?.showTweet()
        }

    }

    private fun String.showTweet() {
        loadDataWithBaseURL("https://twitter.com", this, "text/html", "utf-8", null)
    }


}
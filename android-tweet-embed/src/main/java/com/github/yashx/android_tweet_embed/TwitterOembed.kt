package com.github.yashx.android_tweet_embed

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitterOembedInterface {

    @GET("oembed")
    fun fetchResponse(
        @Query("url") url: String,
        @Query("hide_media") hideMedia: Boolean? = null,
        @Query("hide_thread") hideThread: Boolean? = null,
        @Query("theme") theme: String? = null,
        @Query("dnt") doNotTrack: Boolean? = null
    ): Call<TwitterOembedResponse>
}

data class TwitterOembedResponse(

    @SerializedName("url") val url: String,
    @SerializedName("author_name") val author_name: String,
    @SerializedName("author_url") val author_url: String,
    @SerializedName("html") val html: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("type") val type: String,
    @SerializedName("cache_age") val cache_age: String,
    @SerializedName("provider_name") val provider_name: String,
    @SerializedName("provider_url") val provider_url: String,
    @SerializedName("version") val version: String
)

enum class TweetTheme(val value: String) {
    LIGHT("light"), DARK("dark")
}

object TwitterOembedHelper {

    private val twitterOembedInterface = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://publish.twitter.com/").build()
        .create(TwitterOembedInterface::class.java)

    fun getOembedResponseForUrl(
        url: String,
        hideMedia: Boolean? = null,
        hideThread: Boolean? = null,
        theme: TweetTheme? = null,
        doNotTrack: Boolean? = null,
        onSuccess: (Call<TwitterOembedResponse>, Response<TwitterOembedResponse>) -> Unit
    ) {
        twitterOembedInterface.fetchResponse(url, hideMedia, hideThread, theme?.value, doNotTrack)
            .enqueue(object : Callback<TwitterOembedResponse> {
                override fun onResponse(call: Call<TwitterOembedResponse>, response: Response<TwitterOembedResponse>) {
                    onSuccess.invoke(call, response)
                }

                override fun onFailure(call: Call<TwitterOembedResponse>, t: Throwable) {
                }
            })
    }

    fun getOembedResponseForId(
        id: String,
        hideMedia: Boolean? = null,
        hideThread: Boolean? = null,
        theme: TweetTheme? = null,
        doNotTrack: Boolean? = null,
        onSuccess: (Call<TwitterOembedResponse>, Response<TwitterOembedResponse>) -> Unit
    ) {
        getOembedResponseForUrl(
            "https://twitter.com/twitter/status/$id",
            hideMedia,
            hideThread,
            theme,
            doNotTrack,
            onSuccess
        )
    }

}
package com.github.yashx.android_tweet_embed_demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.yashx.android_tweet_embed.TweetTheme
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        load_id_button.setOnClickListener {
            androidTweetEmbed.loadTweetId(
                tweet_id.text.toString(),
                hideMediaCheckBox.isChecked,
                hideThreadCheckBox.isChecked,
                if (darkThemeCheckBox.isChecked) TweetTheme.DARK else TweetTheme.LIGHT,
                doNotTrackCheckBox.isChecked
            )
        }

        load_url_button.setOnClickListener {
            androidTweetEmbed.loadTweetUrl(
                tweet_url.text.toString(),
                hideMediaCheckBox.isChecked,
                hideThreadCheckBox.isChecked,
                if (darkThemeCheckBox.isChecked) TweetTheme.DARK else TweetTheme.LIGHT,
                doNotTrackCheckBox.isChecked
            )
        }
    }
}
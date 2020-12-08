# AndroidTweetEmbed
Preconfigured Webview to make embedding tweets simple.

<img src="https://user-images.githubusercontent.com/20506602/101453904-165b8780-3956-11eb-9ce0-49624ad9b18c.gif" height="500">




## Download
Add this library from [Jitpack](https://jitpack.io/#yashx/AndroidTweetEmbed/)

Add this in your root build.gradle:

```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

Then add this dependency

```gradle
dependencies {
  implementation 'com.github.yashx:AndroidTweetEmbed:1.0.0'
}
```

## How to use
You can try the sample app ([apk](https://github.com/yashx/AndroidTweetEmbed/releases/download/1.0.0/demo.apk)) and read the code [here](https://github.com/yashx/AndroidTweetEmbed/tree/master/app)

### Basically

Add this view to your layout file
```xml
<com.github.yashx.android_tweet_embed.AndroidTweetEmbed
    android:id="@+id/androidTweetEmbed"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

Find a reference to it and use any of following two methods

```kotlin
   fun loadTweetUrl(
       url: String,
       hideMedia: Boolean? = null,
       hideThread: Boolean? = null,
       theme: TweetTheme? = null,
       doNotTrack: Boolean? = null,
   )
```


```kotlin
   fun loadTweetId(
       id: String,
       hideMedia: Boolean? = null,
       hideThread: Boolean? = null,
       theme: TweetTheme? = null,
       doNotTrack: Boolean? = null,
   )
```

Only id or url is required. The other arguments are optional which I think are self explanatory but if you want you can read about them [here](https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/get-statuses-oembed)

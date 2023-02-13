package com.example.simplevideo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.simplevideo.ui.theme.SimpleVideoTheme
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleVideoTheme {
                val mVideoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
                val context = LocalContext.current
                val player = SimpleExoPlayer.Builder(context).build()
                val playerView = PlayerView(context)
                val mediaItem = MediaItem.fromUri(mVideoUrl)
                val playWhenReady by rememberSaveable {
                    mutableStateOf(true)
                }
                player.setMediaItem(mediaItem)
                playerView.player = player
                playerView
                LaunchedEffect(player) {
                    player.prepare()
                    playerView.hideController()
                    playerView.useController = false
                    player.playWhenReady = playWhenReady

                }
                AndroidView(factory = {

                    playerView
                })
            }
        }
    }
}


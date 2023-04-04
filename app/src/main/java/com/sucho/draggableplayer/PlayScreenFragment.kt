package com.sucho.draggableplayer

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.sucho.draggableplayer.databinding.FragmentPlayScreenBinding

class PlayScreenFragment: Fragment() {
  companion object {
    const val TAG = "PlayScreenFragment"
    fun newInstance(): PlayScreenFragment {
      val args = Bundle()
      val playScreenFragment = PlayScreenFragment()
      playScreenFragment.arguments = args
      return playScreenFragment
    }
  }

  // creating a variable for exoplayerview.
  var exoPlayerView: PlayerView? = null

  // creating a variable for exoplayer
  var exoPlayer: SimpleExoPlayer? = null

  // url of video which we are loading.
  var videoURL =
    "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4"

  lateinit var fragmentPlayScreenBinding: FragmentPlayScreenBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    fragmentPlayScreenBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_play_screen, container, false)
    fragmentPlayScreenBinding.playPauseImageView.setOnClickListener {
      println("Click here");
    }
    exoPlayerView = fragmentPlayScreenBinding.albumArtImageView
    try {

      // BandwidthMeter is used for
      // getting default bandwidth
      val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()

      // track selector is used to navigate between
      // video using a default seekbar.
      val trackSelector: TrackSelector =
        DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))

      // we are adding our track selector to exoplayer.
      exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector)

      // we are parsing a video url
      // and parsing its video uri.
      val videouri: Uri = Uri.parse(videoURL)

      // we are creating a variable for datasource factory
      // and setting its user agent as 'exoplayer_view'
      val dataSourceFactory = DefaultHttpDataSourceFactory("exoplayer_video")

      // we are creating a variable for extractor factory
      // and setting it to default extractor factory.
      val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory()

      // we are creating a media source with above variables
      // and passing our event handler as null,
      val mediaSource: MediaSource =
        ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null)

      // inside our exoplayer view
      // we are setting our player
      exoPlayerView!!.player = exoPlayer

      // we are preparing our exoplayer
      // with media source.
      exoPlayer!!.prepare(mediaSource)

      // we are setting our exoplayer
      // when it is ready.
      exoPlayer!!.playWhenReady = true
    } catch (e: Exception) {
      // below line is used for
      // handling our errors.
      println("Error : $e")
    }
    return fragmentPlayScreenBinding.root
  }
}
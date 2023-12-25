package com.example.m7_4

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity()
{
    private lateinit var play : ImageButton
    private lateinit var pause : ImageButton
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        play = findViewById(R.id.play)
        pause = findViewById(R.id.pause)
        mediaPlayer = MediaPlayer()


        play.setOnClickListener {

            var audioUrl = "https://dw.zobj.net/download/v1/briihvIInNoMZz3svdPsABVLzWPYMrjKjcAFV2qOBbqvqoT6fggwNqXhNsLhs6A7a_V9KkpjzO-TR9UT4czdAaMBIfV_ucMrhQ-ZbCOALgTf4bMxmX5y7pGiyh8Y/?a=&c=72&f=ram_siya_ram.mp3&special=1703484104-kz%2BD4RIi5tu3hnL5pE8Jq25HRU3L10ASJQWm%2Fakcffc%3D"


            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)


            try {

                mediaPlayer.setDataSource(audioUrl)


                mediaPlayer.prepare()


                mediaPlayer.start()

            } catch (e: Exception) {


                e.printStackTrace()
            }

            Toast.makeText(applicationContext, "Audio started playing..", Toast.LENGTH_SHORT).show()
        }
        pause.setOnClickListener {

            if (mediaPlayer.isPlaying) {

                mediaPlayer.stop()

                mediaPlayer.reset()


                mediaPlayer.release()


                Toast.makeText(applicationContext, "Audio has been  paused..", Toast.LENGTH_SHORT)
                    .show()

            } else {

                Toast.makeText(applicationContext, "Audio not played..", Toast.LENGTH_SHORT).show()
            }

        }
    }
}
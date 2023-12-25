package com.example.m7_3

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import java.io.IOException
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var textview2: TextView
    lateinit  var textview3: TextView
    private lateinit var button1: Button
    private lateinit   var button2: Button
    lateinit var seekbar1: SeekBar
    var duration: String? = null
    var mediaPlayer: MediaPlayer? = null
    private lateinit var timer: ScheduledExecutorService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        textview2 = findViewById(R.id.textView2)
        textview3 = findViewById(R.id.textView3)
        seekbar1 = findViewById(R.id.seekbar1)
        button1.setOnClickListener{
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "audio/*"
            startActivityForResult(intent, PICK_FILE)
        }
        button2.setOnClickListener {
            if (mediaPlayer != null) {
                if (mediaPlayer!!.isPlaying) {
                    mediaPlayer!!.pause()
                    button2.text = "PLAY"
                    timer.shutdown()
                } else {
                    mediaPlayer!!.start()
                    button2.text = "PAUSE"
                    timer = Executors.newScheduledThreadPool(1)
                    timer.scheduleAtFixedRate({
                        if (mediaPlayer != null) {
                            if (!seekbar1.isPressed) {
                                seekbar1.progress = mediaPlayer!!.currentPosition
                            }
                        }
                    }, 10, 10, TimeUnit.MILLISECONDS)
                }
            }
        }
        seekbar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (mediaPlayer != null) {
                    val millis = mediaPlayer!!.currentPosition
                    val total_secs =
                        TimeUnit.SECONDS.convert(millis.toLong(), TimeUnit.MILLISECONDS)
                    val mins = TimeUnit.MINUTES.convert(total_secs, TimeUnit.SECONDS)
                    val secs = total_secs - mins * 60
                    textview3.text = "$mins:$secs / $duration"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                if (mediaPlayer != null) {
                    mediaPlayer!!.seekTo(seekbar1.progress)
                }
            }
        })
        button2.isEnabled = false
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_FILE && resultCode == RESULT_OK) {
            if (data != null) {
                val uri = data.data
                createMediaPlayer(uri)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun createMediaPlayer(uri: Uri?) {
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
        try {
            mediaPlayer!!.setDataSource(applicationContext, uri!!)
            mediaPlayer!!.prepare()
            textview2.text = getNameFromUri(uri)
            button2.isEnabled = true
            val millis = mediaPlayer!!.duration
            val total_secs = TimeUnit.SECONDS.convert(millis.toLong(), TimeUnit.MILLISECONDS)
            val mins = TimeUnit.MINUTES.convert(total_secs, TimeUnit.SECONDS)
            val secs = total_secs - mins * 60
            duration = "$mins:$secs"
            textview3.text = "00:00 / $duration"
            seekbar1.max = millis
            seekbar1.progress = 0
            mediaPlayer!!.setOnCompletionListener { releaseMediaPlayer() }
        } catch (e: IOException) {
            textview2.text = e.toString()
        }
    }

    @SuppressLint("Range")
    fun getNameFromUri(uri: Uri?): String {
        var fileName = ""
        val cursor: Cursor? = contentResolver.query(
            uri!!, arrayOf(
                MediaStore.Images.ImageColumns.DISPLAY_NAME
            ), null, null, null
        )
        if (cursor != null && cursor.moveToFirst()) {
            fileName =
                cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME))
        }
        cursor?.close()
        return fileName
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMediaPlayer()
    }

    @SuppressLint("SetTextI18n")
    fun releaseMediaPlayer() {
        timer.shutdown()
        if (mediaPlayer != null) {
            mediaPlayer!!.release()
            mediaPlayer = null
        }
        button2.isEnabled = false
        textview2.text = "TITLE"
        textview3.text = "00:00 / 00:00"
        seekbar1.max = 100
        seekbar1.progress = 0
    }
    companion object {
        const val PICK_FILE = 99
    }
}
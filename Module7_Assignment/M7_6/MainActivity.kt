package com.example.m7_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity()
{
    lateinit var Text:EditText
    lateinit var btnText: Button
    lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Text = findViewById(R.id.Text)
        btnText = findViewById(R.id.btnText)

        textToSpeech = TextToSpeech(applicationContext)
        {
            i->
            if (i!=TextToSpeech.ERROR)
            {
                textToSpeech.language = java.util.Locale.UK
            }
        }

        btnText.setOnClickListener {
            textToSpeech.speak(
                Text.text.toString(), TextToSpeech.QUEUE_FLUSH, null
            )
        }
    }
}
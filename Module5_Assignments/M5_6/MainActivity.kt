package com.example.imageupload

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity()
{
    lateinit var image: ImageView
    lateinit var btnsubmit: Button
    lateinit var imageuri: Uri
    lateinit var edt1: EditText
    lateinit var edt2:EditText
    lateinit var edt3:EditText
    lateinit var edt4: EditText

    private val contract = registerForActivityResult(ActivityResultContracts.GetContent())
    {
        imageuri = it!!
        image.setImageURI(it)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edt1 = findViewById(R.id.name)
        edt2 = findViewById(R.id.mobile)
        edt3 = findViewById(R.id.email)
        edt4 = findViewById(R.id.pass)
        image = findViewById(R.id.img)
        btnsubmit = findViewById(R.id.btn_regi)


        image.setOnClickListener { contract.launch("image/*") }

        btnsubmit.setOnClickListener {
            upload()
        }




    }

    private fun upload()
    {
        val filesDir = applicationContext.filesDir
        val file = File(filesDir,"image.png")
        val inputstream = contentResolver.openInputStream(imageuri)
        val outputstream = FileOutputStream(file)
        inputstream!!.copyTo(outputstream)

        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("img",file.name,requestBody)
        val name1:RequestBody = RequestBody.Companion.create(MultipartBody.FORM,edt1.text.toString())
        val mobile1:RequestBody = RequestBody.Companion.create(MultipartBody.FORM,edt2.text.toString())
        val email1: RequestBody = RequestBody.Companion.create(MultipartBody.FORM,edt3.text.toString())
        val pass1:RequestBody = RequestBody.Companion.create(MultipartBody.FORM,edt4.text.toString())

        val retrofit = Retrofit.Builder()
            .baseUrl("https://contentious-speaker.000webhostapp.com/uploaddata/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Uploadservice::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            var response = retrofit.uploadImage(part,name1,mobile1,email1,pass1)
            Log.d("Vandit",response.toString())

        }

    }
}
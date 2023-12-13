package com.example.imageupload

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface Uploadservice
{
    @Multipart
    @POST("upload.php")
    suspend fun uploadImage
                (
        @Part image: MultipartBody.Part,//file format
        @Part("emp_name") emp_name: RequestBody?,//text format
        @Part("emp_mobile") emp_mobile: RequestBody?,
        @Part("emp_email") emp_email: RequestBody?,
        @Part("emp_pass") emp_pass: RequestBody?,
    ): ResponseBody

}
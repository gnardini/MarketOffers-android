package com.gnardini.marketoffers.repository.services

import com.gnardini.marketoffers.model.LoginObject
import com.gnardini.marketoffers.model.SignUpObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersServices {

    @POST("/login")
    fun login(@Body login: LoginObject): Call<String>

    @POST("/signup")
    fun signup(@Body signup: SignUpObject): Call<String>

}

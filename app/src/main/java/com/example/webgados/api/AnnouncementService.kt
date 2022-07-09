package com.example.webgados.api

import com.example.webgados.response.ListOfAdsResponse
import retrofit2.Call
import retrofit2.http.GET

interface AnnouncementService {

    @GET("v1/53440e27-26d1-4dbc-a6cb-5b4bdef177c2")
    fun getListAnnouncement(): Call<ListOfAdsResponse>

}
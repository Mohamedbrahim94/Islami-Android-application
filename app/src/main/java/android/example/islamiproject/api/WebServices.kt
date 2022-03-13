package android.example.islamiproject.api

import android.example.islamiproject.api.responses.RadiosChannelResponse
import retrofit2.Call
import retrofit2.http.GET

interface WebServices {

    @GET("radios/radio_arabic.json")
    fun getRadiosChannel() : Call<RadiosChannelResponse>



}
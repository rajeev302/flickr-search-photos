package com.example.flicker_search_photos.service

import com.example.flicker_search_photos.constants.Constants
import com.example.flicker_search_photos.model.FlickerSearchTagApiResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    @GET("rest/?&tags=dogs&format=json&nojsoncallback=1")
    fun getPhotosBySearchTerm(@Query("method") method: String = Constants.FLICKR_SEARCH_METHOD,
                              @Query("api_key") api_key: String = Constants.FLICKR_API_KEY,
                              @Query("tags")tags: String, @Query("format") format: String = "json",
                              @Query("nojsoncallback")nojsoncallback: String = "1"): Call<FlickerSearchTagApiResponseModel>
}
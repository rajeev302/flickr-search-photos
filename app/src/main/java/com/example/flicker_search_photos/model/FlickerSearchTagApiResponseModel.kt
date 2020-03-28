package com.example.flicker_search_photos.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlickerSearchTagApiResponseModel(
    @Json(name = "photos")
    var photos: Photos?,
    @Json(name = "stat")
    var stat: String?
)
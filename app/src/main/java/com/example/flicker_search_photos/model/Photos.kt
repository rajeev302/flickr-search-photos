package com.example.flicker_search_photos.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photos(
    @Json(name = "page")
    var page: Int?,
    @Json(name = "pages")
    var pages: String?,
    @Json(name = "perpage")
    var perpage: Int?,
    @Json(name = "photo")
    var flickrPhotoResponseModel: List<FlickrPhotoResponseModel>?,
    @Json(name = "total")
    var total: String?
)
package com.example.flicker_search_photos.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlickrPhotoResponseModel(
    @Json(name = "farm")
    var farm: Int?,
    @Json(name = "id")
    var id: String?,
    @Json(name = "isfamily")
    var isfamily: Int?,
    @Json(name = "isfriend")
    var isfriend: Int?,
    @Json(name = "ispublic")
    var ispublic: Int?,
    @Json(name = "owner")
    var owner: String?,
    @Json(name = "secret")
    var secret: String?,
    @Json(name = "server")
    var server: String?,
    @Json(name = "title")
    var title: String?
)
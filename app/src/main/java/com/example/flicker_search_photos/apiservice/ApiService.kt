package com.example.flicker_search_photos.apiservice

import com.example.flicker_search_photos.constants.Constants
import com.example.flicker_search_photos.manager.NetworkManager
import com.example.flicker_search_photos.model.FlickerSearchTagApiResponseModel
import com.example.flicker_search_photos.service.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class ApiService{
    suspend fun getPhotosBySearchTerm(coroutineContext: CoroutineContext, tags: String): FlickerSearchTagApiResponseModel? = CoroutineScope(coroutineContext).async {
        val apiService = NetworkManager.makeRetrofitObject(Service::class.java, Constants.BASE_URL)
        val callResult = apiService.getPhotosBySearchTerm(tags = tags)
        callResult.execute().body()
    }.await()
}
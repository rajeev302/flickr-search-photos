package com.example.flicker_search_photos.repsitory

import android.content.Context
import com.example.flicker_search_photos.apiservice.ApiService
import com.example.flicker_search_photos.extensions.isInternetAvailable
import com.example.flicker_search_photos.model.FlickerSearchTagApiResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class Respository: CoroutineScope {
    private val job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    suspend fun getPhotosBySearchTerm(context: Context, tags: String): FlickerSearchTagApiResponseModel? {
        if (context.isInternetAvailable()){
            val issueList = CoroutineScope(coroutineContext).async {
                return@async ApiService().getPhotosBySearchTerm(coroutineContext, tags = tags)
            }.await()
            return issueList
        } else {
            return null
        }
    }
}
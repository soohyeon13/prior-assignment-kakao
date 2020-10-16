package kr.ac.jejunu.priorassignment.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {
    @GET("v2/search/image")
    fun loadImages(@Query("query") query:String):Single<Document>
}
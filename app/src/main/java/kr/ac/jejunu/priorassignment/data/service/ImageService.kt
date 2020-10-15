package kr.ac.jejunu.priorassignment.data.service

import io.reactivex.Single
import kr.ac.jejunu.priorassignment.data.response.Document
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {
    @GET
    fun loadImages(@Query("query") query:String):Single<Document>
}
package kr.ac.jejunu.priorassignment.domain

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import kr.ac.jejunu.priorassignment.domain.model.ImageItem

interface ImageRepository {
    fun loadImageItems(searchWord:String):Completable
    fun getImages():Observable<List<ImageItem>>
}
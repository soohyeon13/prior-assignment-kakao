package kr.ac.jejunu.priorassignment.data

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kr.ac.jejunu.priorassignment.data.service.ImageService
import kr.ac.jejunu.priorassignment.domain.ImageRepository
import kr.ac.jejunu.priorassignment.domain.model.ImageItem

class ImageRepositoryImpl(val imageService: ImageService) : ImageRepository {
    private val imageItems = BehaviorSubject.create<List<ImageItem>>()
    override fun loadImageItems(searchWord: String): Completable {
        return imageService.loadImages(searchWord).doOnSuccess {
            val items = it.meta.documents.map { ImageItem(it.collection, it.thumbnailUrl) }
            imageItems.onNext(items)
        }.ignoreElement()
    }

    override fun getImages(): Observable<List<ImageItem>> {
        return imageItems.hide()
    }
}
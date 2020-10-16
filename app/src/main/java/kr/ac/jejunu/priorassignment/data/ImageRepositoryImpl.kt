package kr.ac.jejunu.priorassignment.data

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kr.ac.jejunu.priorassignment.domain.ImageRepository
import kr.ac.jejunu.priorassignment.domain.model.ImageItem

class ImageRepositoryImpl(private val imageService: ImageService) : ImageRepository {
    companion object {
        private const val TAG = "ImageRepositoryImpl"
    }

    private val imageItems = BehaviorSubject.create<LinkedHashMap<String, List<ImageItem>>>()
    private val collections = BehaviorSubject.create<List<String>>()
    override fun loadImageItems(searchWord: String): Completable {
        return imageService
            .loadImages(searchWord)
            .doOnSuccess { document ->
                val itemMap = linkedMapOf<String, List<ImageItem>>()
                itemMap["all"] = document.documents
                    .map { ImageItem(it.collection, it.thumbnailUrl) }
                document
                    .documents
                    .map { ImageItem(it.collection, it.thumbnailUrl) }
                    .groupBy { it.collection }
                    .forEach { item ->
                        itemMap[item.key] = item.value
                    }
                collections.onNext(itemMap.keys.toList())
                imageItems.onNext(itemMap)
            }
            .doOnError { error -> Log.e(TAG, "$error") }
            .ignoreElement()
    }

    override fun getImages(): Observable<LinkedHashMap<String, List<ImageItem>>> {
        return imageItems
            .distinct()
            .hide()
    }

    override fun getCollections(): Observable<List<String>> {
        return collections.hide()
    }
}
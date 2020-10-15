package kr.ac.jejunu.priorassignment.ui.viewmodel

import android.util.Log
import androidx.lifecycle.toLiveData
import io.reactivex.BackpressureStrategy
import kr.ac.jejunu.priorassignment.base.BaseViewModel
import kr.ac.jejunu.priorassignment.domain.ImageRepository

class MainImageSearchViewModel(private val imageRepository: ImageRepository) : BaseViewModel() {
    companion object {
        private const val TAG = "ImageSearchViewModel"
    }

    val imageItemsLiveData = imageRepository
        .getImages()
        .toFlowable(BackpressureStrategy.BUFFER)
        .toLiveData()

    fun loadImages(searchWord: String) {
        imageRepository
            .loadImageItems(searchWord)
            .subscribe({}, {
                Log.e(TAG, "error $it")
            }).let { addDisposable(it) }
    }
}
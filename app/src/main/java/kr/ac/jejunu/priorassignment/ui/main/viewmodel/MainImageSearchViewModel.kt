package kr.ac.jejunu.priorassignment.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.toLiveData
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kr.ac.jejunu.priorassignment.base.BaseViewModel
import kr.ac.jejunu.priorassignment.domain.ImageRepository

class MainImageSearchViewModel(private val imageRepository: ImageRepository) : BaseViewModel() {
    companion object {
        private const val TAG = "ImageSearchViewModel"
        private const val DEFAULT_SPINNER_VALUE = "선택 없음"
        private const val INIT_VALUE = "all"
    }

    val _inputValue = MutableLiveData<String>()
    private val inputValue: LiveData<String>
        get() = _inputValue

    val selectCollections = BehaviorSubject.createDefault(INIT_VALUE)

    val spinnerEntries = MutableLiveData<List<String>>(listOf(DEFAULT_SPINNER_VALUE))

    val imageItemsLiveData = imageRepository
        .getImages()
        .toFlowable(BackpressureStrategy.BUFFER)
        .toLiveData()

    private val collectionsLiveData = imageRepository
        .getCollections()
        .toFlowable(BackpressureStrategy.BUFFER)

    fun onSearchBtnClick() {
        loadImages()
    }

    fun onSelectedValue(value: String) {
        if (value != DEFAULT_SPINNER_VALUE) selectCollections.onNext(value)
    }

    private fun setSpinnerEntries() {
        collectionsLiveData
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ collections ->
                spinnerEntries.postValue(collections)
            }, { error ->
                Log.e(TAG, "$error")
            }).let { addDisposable(it) }
    }

    private fun loadImages() {
        inputValue.value?.let { word ->
            imageRepository
                .loadImageItems(word)
                .subscribeOn(Schedulers.io())
                .doOnComplete { setSpinnerEntries() }
                .subscribe({}, { error ->
                    Log.e(TAG, "error $error")
                }).let { addDisposable(it) }
        }
    }
}
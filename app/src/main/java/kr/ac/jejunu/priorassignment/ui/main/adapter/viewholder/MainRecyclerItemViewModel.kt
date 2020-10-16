package kr.ac.jejunu.priorassignment.ui.main.adapter.viewholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kr.ac.jejunu.priorassignment.base.BaseItemViewModel
import kr.ac.jejunu.priorassignment.domain.model.ImageItem

class MainRecyclerItemViewModel:BaseItemViewModel<ImageItem>() {
    private var _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String>
        get() = _imageUrl
    override fun bind(data: ImageItem) {
        _imageUrl.value = data.thumbnailUrl
    }
}
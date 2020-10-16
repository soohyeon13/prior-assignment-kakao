package kr.ac.jejunu.priorassignment.util

import androidx.recyclerview.widget.DiffUtil
import kr.ac.jejunu.priorassignment.domain.model.ImageItem

class DiffUtil(
    private val oldList: List<ImageItem>,
    private val newList: List<ImageItem>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].thumbnailUrl == newList[newItemPosition].thumbnailUrl
                && oldList[oldItemPosition].collection == newList[newItemPosition].collection
    }

}
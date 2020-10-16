package kr.ac.jejunu.priorassignment.ui.main.adapter.viewholder

import kr.ac.jejunu.priorassignment.base.BaseViewHolder
import kr.ac.jejunu.priorassignment.databinding.LayoutImageItemBinding
import kr.ac.jejunu.priorassignment.domain.model.ImageItem

class MainRecyclerViewHolder(private val binding: LayoutImageItemBinding) :
    BaseViewHolder<ImageItem>(binding.root) {
    private val itemViewModel = MainRecyclerItemViewModel()
    override fun bind(item: ImageItem) {
        binding.itemViewModel = itemViewModel
        itemViewModel.bind(item)
    }
}
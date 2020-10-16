package kr.ac.jejunu.priorassignment.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.ac.jejunu.priorassignment.R
import kr.ac.jejunu.priorassignment.databinding.LayoutImageItemBinding
import kr.ac.jejunu.priorassignment.domain.model.ImageItem
import kr.ac.jejunu.priorassignment.ui.main.adapter.viewholder.MainRecyclerViewHolder
import kr.ac.jejunu.priorassignment.util.DiffUtil

class MainRecyclerViewAdapter : RecyclerView.Adapter<MainRecyclerViewHolder>() {

    private var imageItems = mutableListOf<ImageItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        val binding: LayoutImageItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_image_item, parent, false
        )
        return MainRecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int = imageItems.size ?: 0

    fun setImageItems(items: List<ImageItem>) {
        val diffCallback = DiffUtil(this.imageItems,items)
        val diffResult = androidx.recyclerview.widget.DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        this.imageItems.clear()
        this.imageItems.addAll(items)
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        holder.bind(imageItems[position])
    }
}
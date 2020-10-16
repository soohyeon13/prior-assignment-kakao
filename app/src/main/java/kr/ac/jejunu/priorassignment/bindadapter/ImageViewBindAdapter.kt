package kr.ac.jejunu.priorassignment.bindadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class ImageViewBindAdapter {
    @BindingAdapter("image")
    fun ImageView.setImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}
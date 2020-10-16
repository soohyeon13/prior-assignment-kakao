package kr.ac.jejunu.priorassignment.bindadapter

import androidx.databinding.DataBindingComponent

class BindComponent:DataBindingComponent {
    override fun getSpinnerBindAdapter(): SpinnerBindAdapter {
        return SpinnerBindAdapter()
    }

    override fun getImageViewBindAdapter(): ImageViewBindAdapter {
        return ImageViewBindAdapter()
    }
}
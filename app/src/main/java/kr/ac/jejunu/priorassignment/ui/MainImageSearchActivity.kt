package kr.ac.jejunu.priorassignment.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import kr.ac.jejunu.priorassignment.R
import kr.ac.jejunu.priorassignment.base.BaseActivity
import kr.ac.jejunu.priorassignment.databinding.ActivityMainImageSearchBinding
import kr.ac.jejunu.priorassignment.ui.viewmodel.MainImageSearchViewModel
import org.koin.android.ext.android.inject

class MainImageSearchActivity :
    BaseActivity<ActivityMainImageSearchBinding>(R.layout.activity_main_image_search) {

    private val viewModel : MainImageSearchViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        observe()
    }

    private fun init() {
        viewModel.loadImages("sad")
        //TODO 리사이클러뷰 설정
    }

    private fun observe() {
        viewModel.imageItemsLiveData.observe(this, Observer {
            //TODO 리사이클러뷰 어댑터에 데이터 셋
        })
    }
}
package kr.ac.jejunu.priorassignment.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kr.ac.jejunu.priorassignment.R
import kr.ac.jejunu.priorassignment.base.BaseActivity
import kr.ac.jejunu.priorassignment.databinding.ActivityMainImageSearchBinding
import kr.ac.jejunu.priorassignment.ui.main.adapter.MainRecyclerViewAdapter
import kr.ac.jejunu.priorassignment.ui.main.viewmodel.MainImageSearchViewModel
import org.koin.android.ext.android.inject

class MainImageSearchActivity :
    BaseActivity<ActivityMainImageSearchBinding>(R.layout.activity_main_image_search) {

    companion object {
        private const val TAG = "MainImageSearchActivity"
    }

    private val mainImageViewModel: MainImageSearchViewModel by inject()
    private val mainImageAdapter: MainRecyclerViewAdapter by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        observe()
    }

    private fun init() {
        binding.viewModel = mainImageViewModel
        val mainLayoutManager = GridLayoutManager(this@MainImageSearchActivity, 6)
        mainLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position % 5) {
                    3 -> 3
                    4 -> 3
                    else -> 2
                }
            }
        }
        binding.rvMainImages.apply {
            layoutManager = mainLayoutManager
            adapter = mainImageAdapter
        }
    }

    private fun observe() {
        mainImageViewModel.imageItemsLiveData.observe(this, Observer { item ->
            mainImageViewModel.selectCollections.subscribe({ collection ->
                item[collection]?.let {
                    mainImageAdapter.setImageItems(it)
                }
            }, {})
        })
    }
}
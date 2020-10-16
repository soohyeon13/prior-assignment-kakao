package kr.ac.jejunu.priorassignment.bindadapter

import android.widget.Spinner
import androidx.databinding.BindingAdapter
import kr.ac.jejunu.priorassignment.bindadapter.SpinnerExt.setSpinnerEntries
import kr.ac.jejunu.priorassignment.bindadapter.SpinnerExt.setSpinnerItemSelectedListener

class SpinnerBindAdapter {
    @BindingAdapter("entries")
    fun Spinner.setEntries(entries: List<Any>?) {
        setSpinnerEntries(entries)
    }

    @BindingAdapter("onItemSelected")
    fun Spinner.setOnItemSelectedListener(itemSelectedListener: SpinnerExt.ItemSelectedListener?) {
        setSpinnerItemSelectedListener(itemSelectedListener)
    }
}
package com.gtsl.tflroadstatus.databinding

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("hideSoftKeyboard")
    fun hideSoftKeyboard(view: Button, requestFocus: Boolean) {
        if (requestFocus) {
            val imm: InputMethodManager =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.applicationWindowToken, 0)
        }
    }
}
package com.firentistfw.kindlehighlights.utils

import android.content.Context
import android.widget.Toast

object ToastUtils {
    fun showSimpleToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}
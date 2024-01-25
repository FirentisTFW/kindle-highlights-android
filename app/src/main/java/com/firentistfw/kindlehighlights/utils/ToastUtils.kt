package com.firentistfw.kindlehighlights.utils

import android.content.Context
import android.widget.Toast
import com.firentistfw.kindlehighlights.R

object ToastUtils {
    fun showFeatureUnavailable(context: Context) {
        showSimpleToast(context, context.getString(R.string.toastUtils_featureNotAvailable))
    }

    private fun showSimpleToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}
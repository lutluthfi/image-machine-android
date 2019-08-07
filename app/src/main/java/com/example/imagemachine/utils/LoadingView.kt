package com.example.imagemachine.utils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.widget.TextView
import com.example.imagemachine.R

class LoadingView(var context: Context?) {

    private var dialog: Dialog? = null

    fun addCancelListener(listener: DialogInterface.OnCancelListener) {
        dialog?.run { setOnCancelListener(listener) }
    }

    fun dismissLoadingView() = dialog?.dismiss()

    fun isLoadingDialogDidShow() = dialog?.isShowing ?: run { false }

    fun showLoadingView(message: String?) {
        context?.let {
            dialog = Dialog(it).apply {
                setCancelable(true)
                setCanceledOnTouchOutside(false)
                window?.run {
                    setContentView(R.layout.view_loading)
                    setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                }
                val messageTextView = findViewById<TextView>(R.id.text_util_loading_message)
                message?.let { msg ->
                    when {
                        msg.isBlank() ->
                            messageTextView.text = ""
                        else ->
                            messageTextView.text = msg
                    }
                }
                show()
            }
        }
    }
}
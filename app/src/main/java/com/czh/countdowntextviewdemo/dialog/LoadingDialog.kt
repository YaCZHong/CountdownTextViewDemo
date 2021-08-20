package com.czh.countdowntextviewdemo.dialog

import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.czh.countdowntextviewdemo.R

class LoadingDialog(
    mContext: Context,
    msg: String? = null,
    cancelable: Boolean = true
) : Dialog(mContext) {

    init {
        setContentView(R.layout.dialog_loading)
        val imageView = findViewById<ImageView>(R.id.iv_loading)
        val textView = findViewById<TextView>(R.id.tv_loading_hint)
        imageView.setImageResource(R.drawable.icon_loading)
        val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.anim_loading)
        imageView.startAnimation(animation)
        if (TextUtils.isEmpty(msg)) {
            textView.visibility = View.GONE
        } else {
            textView.text = msg
        }
        if (!cancelable) {
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
        window?.apply {
            val params = attributes
            params.gravity = Gravity.CENTER
            attributes = params
            setBackgroundDrawable(null)
        }
    }
}
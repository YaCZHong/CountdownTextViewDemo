package com.czh.countdowntextviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.czh.countdowntextview.CountdownTextView
import com.czh.countdowntextviewdemo.dialog.LoadingDialog
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var cdtv: CountdownTextView
    private lateinit var et: EditText
    private var loading: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        cdtv = findViewById(R.id.cdtv)
        et = findViewById(R.id.et)
        cdtv.bindEt(et)
        cdtv.setOnClickListener {
            getCode()
        }
    }

    // 模拟接口请求验证码
    private fun getCode() {
        showLoading()
        thread {
            Thread.sleep(3000)

            //请求成功，开始倒计时
            runOnUiThread {
                hideLoading()
                cdtv.startTask()
            }
        }
    }

    private fun showLoading() {
        loading?.dismiss()
        loading = LoadingDialog(this, "请稍候...", false).apply {
            show()
        }
    }

    private fun hideLoading() {
        loading?.dismiss()
    }
}
# 可倒计时的 TextView

**_应用场景：类似于获取短信验证码_**

### 演示视频

<iframe height=498 width=510 src="https://github.com/YaCZHong/CountdownTextViewDemo/blob/master/SVID_20210821_134501_1.mp4" frameborder=0 allowfullscreen></iframe>

<iframe src="https://github.com/YaCZHong/CountdownTextViewDemo/blob/master/SVID_20210821_134501_1.mp4" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>

## 使用

**简单的布局，一个输入手机号的 EditText 和我们用于显示倒计时的 CountdownTextView**

```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <EditText
        android:id="@+id/et"
        android:layout_width="0px"
        android:layout_height="48dp"
        android:hint="请输入手机号码"
        android:text="13800138000"
        android:inputType="phone"
        app:layout_constraintEnd_toStartOf="@id/cdtv"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <com.czh.countdowntextview.CountdownTextView
        android:id="@+id/cdtv"
        android:layout_width="wrap_content"
        android:layout_height="48dp"
        android:gravity="center"
        android:paddingHorizontal="16dp"
        app:countTime="10"
        app:countdownColor="#C1C1C1"
        app:enableLength="11"
        app:interval="1"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@id/et"
        app:layout_constraintTop_toTopOf="@id/et"
        app:normalColor="#03A9F4" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

**然后在 Activity 当中进行简单的设置**

```
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

        // 绑定对应的Edittext
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
```

## 完。

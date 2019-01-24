package com.taobao.taobao

import android.content.Intent
import android.content.Intent.getIntent
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.KeyEvent
import android.view.WindowManager
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import java.net.URI

class JdActivity : AppCompatActivity() {
    private var webView: WebView? = null
    private var refreshLayout: SwipeRefreshLayout? = null
    private val firstTime: Long = 0

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "載入速度取決於當前網絡狀態", Toast.LENGTH_SHORT).show()
        webView = findViewById<WebView>(R.id.taobao)

//        if (intent.data != null){
//            val intent = getIntent()
//            val uri :Uri =intent.data
//            if (uri !=null){
//                val url :String =uri.getQueryParameter("h5Url")
//                Log.i("??", url)
//                webView!!.loadUrl(url)
//            }
//        }else{
            webView!!.loadUrl("https://m.jd.com")
//        }

        webView!!.settings.javaScriptEnabled = true
        webView!!.settings.domStorageEnabled = true

        webView!!.canGoBack()
        webView!!.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                handler.proceed()
            }
        }
        //        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        webView!!.settings.userAgentString="AliApp(TB/4.9.2)"
        refreshLayout = findViewById<SwipeRefreshLayout>(R.id.refresh)
        refreshLayout!!.setOnRefreshListener {
            Handler().postDelayed({
                webView!!.reload()
                refreshLayout!!.isRefreshing = false
            }, 2000)
        }



    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView!!.canGoBack()) {
                webView!!.goBack()
                return true
            } else {
                finish()

            }
        }
        return super.onKeyDown(keyCode, event)
    }
}

package com.taobao.taobao


import android.content.pm.ShortcutInfo
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent

import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private var webView: WebView? = null
    private var refreshLayout: SwipeRefreshLayout? = null
    private val firstTime: Long = 0
    private var searchShortcut:ShortcutInfo ?=null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "載入速度取決於當前網絡狀態", Toast.LENGTH_SHORT).show()
        webView = findViewById<WebView>(R.id.taobao)

        if (intent.data != null){
            val intent = intent
            val uri :Uri =intent.data
            if (uri !=null){
                val url:String=uri.toString()
                Log.i("??", url)
                webView!!.loadUrl(url)
            }
        }else{
            webView!!.loadUrl("https://s.m.taobao.com/h5")
        }

        webView!!.settings.javaScriptEnabled = true
        webView!!.canGoBack()
        webView!!.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                handler.proceed()
            }
        }

//        webView!!.setWebViewClient(NewWebViewClient())

//        webView.setOnScrollChangedCallback(QiWebView.OnScrollChangedCallback(){
//
//        })

        //        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        webView!!.settings.userAgentString = "Mozilla/5.0 (Linux; Google; Android 9.0; zh-CN; Nexus 6 Build) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/71.0.3578.98 /2.13.1.10 Mobile Safari/537.36 AliApp(TB/7.7.3)"

        webView!!.settings.setAppCacheEnabled(true)
//        webView!!.settings.blockNetworkImage=true
        webView!!.settings.safeBrowsingEnabled=true
//        webView!!.settings.loadsImagesAutomatically=true
        webView!!.settings.domStorageEnabled=true

        refreshLayout = findViewById<SwipeRefreshLayout>(R.id.refresh)
        refreshLayout!!.setOnRefreshListener {
            Handler().postDelayed({
                webView!!.reload()
                refreshLayout!!.isRefreshing = false
            }, 2000)
        }


//        val shortcutManager = getSystemService(ShortcutManager::class.java)
//
//        val shortcut = ShortcutInfo.Builder(this, "id1")
//                .setShortLabel("12")
//                .setLongLabel("123")
//                .setIcon(Icon.createWithResource(this, R.drawable.abc_ic_search_api_material))
//                .setIntent(Intent(Intent.ACTION_MAIN,
//                        Uri.parse("https://")))
//                .build()
//
//        shortcutManager!!.dynamicShortcuts = Arrays.asList(shortcut)

//        val shortcutManager = getSystemService<ShortcutManager>(ShortcutManager::class.java)
//
//       searchShortcut=ShortcutInfo.Builder(this,"id")
//               .setShortLabel("搜索")
//               .setLongLabel("搜索")
//               .setIcon(Icon.createWithResource(this,R.drawable.abc_ic_search_api_material))
//               .setIntent(Intent("com.taobao.taobao.MainActivity", Uri.parse("https://s.m.taobao.com")))
//               .build()
//       shortcutManager!!.dynamicShortcuts=Arrays.asList(searchShortcut!!)

//        fab= findViewById(R.id.floatingActionButton) as FloatingActionButton
//        fab!!.setOnClickListener {
//            val intent = Intent(Intent.ACTION_SEND)
//            intent.putExtra(Intent.EXTRA_SUBJECT,"Share")
//            intent.putExtra(Intent.EXTRA_TEXT, webView!!.url)
//            intent.type = "text"
//            startActivity(Intent.createChooser(intent, webView!!.title))
//
//        }

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

//    private open class NewWebViewClient : WebViewClient() {
//        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//           //view.loadUrl(url);
//        return false
//        }
//}

//http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0718/3197.html
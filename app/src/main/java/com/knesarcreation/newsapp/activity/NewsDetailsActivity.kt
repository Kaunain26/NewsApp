package com.knesarcreation.newsapp.activity

import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.knesarcreation.newsapp.R

class NewsDetailsActivity : AppCompatActivity() {

    lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        mToolbar = findViewById(R.id.mToolbar)

        /*setting up toolbar*/
        setUpToolbar()

        val url = intent?.getStringExtra("url")

        val mWebView: WebView = findViewById(R.id.webView)
        mWebView.loadUrl(url!!)

        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        mWebView.webViewClient = WebViewClient()

        mWebView.canGoBack()
        mWebView.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == MotionEvent.ACTION_UP
                && mWebView.canGoBack()
            ) {
                mWebView.goBack()
                return@setOnKeyListener true
            }
            false
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(mToolbar)
        supportActionBar?.title = "News Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
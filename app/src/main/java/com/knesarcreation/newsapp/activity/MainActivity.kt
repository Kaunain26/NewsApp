package com.knesarcreation.newsapp.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.knesarcreation.newsapp.R
import com.knesarcreation.newsapp.adapter.PageAdapter
import com.knesarcreation.newsapp.model.TabItems

class MainActivity : AppCompatActivity() {
    lateinit var mToolbar: Toolbar
    lateinit var mTabLayout: TabLayout
    lateinit var mViewPager: ViewPager
    lateinit var pagerAdapter: PageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*initializing views*/
        init()

        /*setting up toolbar*/
        setUpToolbar()

        /*Adding tabs to tab layout*/
        addTabs()
        mTabLayout.setupWithViewPager(mViewPager)

        pagerAdapter = PageAdapter(supportFragmentManager, listOfTabItems())
        mViewPager.adapter = pagerAdapter

        mViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTabLayout))
    }

    private fun listOfTabItems(): List<TabItems> {
        return listOf(
            TabItems("Top Headlines\nBusiness"),
            TabItems("Top Headlines\nSports"),
            TabItems("The Wall Street Journal"),
            TabItems("Fox News"),
            TabItems("The Hill"),
            TabItems("Tech Crunch"),
            TabItems("Reuters"),
            TabItems("Engadget"),
            TabItems("TechRadar"),
            TabItems("Mashable")
        )
    }

    private fun addTabs() {
        mTabLayout.addTab(mTabLayout.newTab().setText("Top Headlines\nBusiness"))
        mTabLayout.addTab(mTabLayout.newTab().setText("Top Headlines\nSports"))
        mTabLayout.addTab(mTabLayout.newTab().setText("The Wall Street Journal"))
        mTabLayout.addTab(mTabLayout.newTab().setText("Fox News"))
        mTabLayout.addTab(mTabLayout.newTab().setText("The Hill"))
        mTabLayout.addTab(mTabLayout.newTab().setText("Tech Crunch"))
        mTabLayout.addTab(mTabLayout.newTab().setText("Reuters"))
        mTabLayout.addTab(mTabLayout.newTab().setText("Engadget"))
        mTabLayout.addTab(mTabLayout.newTab().setText("TechRadar"))
        mTabLayout.addTab(mTabLayout.newTab().setText("Mashable"))
    }

    private fun init() {
        mToolbar = findViewById(R.id.mToolbar)
        mTabLayout = findViewById(R.id.mTabLayout)
        mViewPager = findViewById(R.id.mViewPager)
    }

    private fun setUpToolbar() {
        setSupportActionBar(mToolbar)
        supportActionBar?.title = "News app"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
        }
        return true
    }
}
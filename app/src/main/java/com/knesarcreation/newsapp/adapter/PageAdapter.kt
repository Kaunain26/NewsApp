package com.knesarcreation.newsapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.knesarcreation.newsapp.fragment.NewsFragment
import com.knesarcreation.newsapp.model.TabItems

class PageAdapter(fragmentManager: FragmentManager, private val tabItemList: List<TabItems>) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return NewsFragment.newInstance(position)
    }

    override fun getCount() = tabItemList.size

    override fun getPageTitle(position: Int): CharSequence? {
        return tabItemList[position].title
    }
}
package com.example.codelab_5_shubhendra.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class PageAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    private val list=ArrayList<Fragment>()



    fun addFragment(fragment:Fragment)
    {
        list.add(fragment)
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return list[position]
    }


}
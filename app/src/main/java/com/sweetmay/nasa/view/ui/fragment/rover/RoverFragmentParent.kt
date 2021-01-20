package com.sweetmay.nasa.view.ui.fragment.rover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.sweetmay.nasa.App
import com.sweetmay.nasa.R
import com.sweetmay.nasa.databinding.FragmentRoverBinding
import com.sweetmay.nasa.view.ui.base.BaseFragment

class RoverFragmentParent: BaseFragment<FragmentRoverBinding>() {

    private lateinit var adapter: FragmentAdapter
    private lateinit var viewPager: ViewPager2


    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRoverBinding {
        return FragmentRoverBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FragmentAdapter(this)
        viewPager = binding.pager
        viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, viewPager){tab, position ->
            when(position){
                0->tab.text = getString(R.string.fhaz_tab)
                1->tab.text = getString(R.string.rhaz_tab)
                2->tab.text = getString(R.string.nav_cam_tab)
            }
        }.attach()
    }

    override fun showLoading() {
        //nothing to do
    }

    override fun hideLoading() {
        //nothing to do
    }
}

class FragmentAdapter(fragment: Fragment): FragmentStateAdapter(fragment){

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = RoverChildFragment()
        fragment.arguments = Bundle().apply {
            when(position){
                0-> putString(App.ROVER_FRAGMENT_KEY, "fhaz")
                1-> putString(App.ROVER_FRAGMENT_KEY, "rhaz")
                2-> putString(App.ROVER_FRAGMENT_KEY, "navcam")
            }

        }
        return fragment
    }

}
package com.sweetmay.nasa.view.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.sweetmay.nasa.view.BaseView
import moxy.MvpAppCompatFragment

abstract class BaseFragment<VB: ViewBinding>: MvpAppCompatFragment(), BaseView {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = setBinding(inflater, container)
        return _binding!!.root
    }

    abstract fun setBinding(inflater: LayoutInflater, container: ViewGroup?): VB?

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
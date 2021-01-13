package com.sweetmay.nasa.view.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import coil.load
import com.sweetmay.nasa.App
import com.sweetmay.nasa.databinding.ApodFragmentBinding
import com.sweetmay.nasa.model.entity.APOD
import com.sweetmay.nasa.model.repo.NasaRepo
import com.sweetmay.nasa.presenter.APODPresenter
import com.sweetmay.nasa.view.ui.base.BaseFragment
import moxy.ktx.moxyPresenter

class APODFragment: BaseFragment<ApodFragmentBinding>(), APODView {

    lateinit var navController: NavController

    val presenter: APODPresenter by moxyPresenter {
        APODPresenter(NasaRepo(), App.base_url, App.api_key)
    }

    override fun showApod(apod: APOD) {
        with(binding){
            apodImage.load(apod.url)
            explanationApod.text = apod.explanation
        }

    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): ApodFragmentBinding {
        navController = findNavController()
        return ApodFragmentBinding.inflate(inflater, container, false)
    }


}
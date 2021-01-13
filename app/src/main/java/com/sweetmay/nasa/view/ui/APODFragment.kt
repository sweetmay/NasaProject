package com.sweetmay.nasa.view.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import coil.load
import com.sweetmay.nasa.App
import com.sweetmay.nasa.R
import com.sweetmay.nasa.databinding.FragmentApodBinding
import com.sweetmay.nasa.model.entity.APOD
import com.sweetmay.nasa.model.repo.NasaRepo
import com.sweetmay.nasa.presenter.APODPresenter
import com.sweetmay.nasa.utils.ApiHolder
import com.sweetmay.nasa.view.APODView
import com.sweetmay.nasa.view.ui.base.BaseFragment
import moxy.ktx.moxyPresenter

class APODFragment: BaseFragment<FragmentApodBinding>(), APODView {

    lateinit var navController: NavController

    val presenter: APODPresenter by moxyPresenter {
        APODPresenter(NasaRepo(ApiHolder(App.base_url)), App.api_key)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inputLayout.setEndIconOnClickListener {
            //TODO wikiseacrh
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun showApod(apod: APOD) {
        with(binding){
            if(apod.media_type == "video"){
                apodVideo.visibility = View.VISIBLE
                apodVideo.settings.javaScriptEnabled = true
                apodVideo.loadUrl(apod.url)
            }else{
                apodImage.visibility = View.VISIBLE
                apodImage.load(apod.url)
            }
            explanationApod.text = apod.explanation
        }
    }

    override fun setTitle() {
        binding.title.text = getString(R.string.picture_of_the_day_title)
    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentApodBinding {
        navController = findNavController()
        return FragmentApodBinding.inflate(inflater, container, false)
    }


}
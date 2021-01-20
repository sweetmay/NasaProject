package com.sweetmay.nasa.view.ui.fragment.rover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sweetmay.nasa.App
import com.sweetmay.nasa.databinding.FragmentRoverChildBinding
import com.sweetmay.nasa.model.entity.rover.PhotoData
import com.sweetmay.nasa.model.repo.NasaRepo
import com.sweetmay.nasa.presenter.RoverPresenter
import com.sweetmay.nasa.utils.ApiHolder
import com.sweetmay.nasa.utils.date.DateUtil
import com.sweetmay.nasa.utils.image.IImageLoader
import com.sweetmay.nasa.utils.image.ImageLoader
import com.sweetmay.nasa.utils.image.OnImageSuccessListener
import com.sweetmay.nasa.view.RoverView
import com.sweetmay.nasa.view.ui.base.BaseFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.ktx.moxyPresenter

class RoverChildFragment: BaseFragment<FragmentRoverChildBinding>(), RoverView {

    private lateinit var imageLoader: IImageLoader

    private val presenter: RoverPresenter by moxyPresenter {
        RoverPresenter(NasaRepo(ApiHolder(App.BASE_URL),
            DateUtil()),
            AndroidSchedulers.mainThread(),
            App.API_KEY) }

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRoverChildBinding {
        imageLoader = ImageLoader()
        return FragmentRoverChildBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(App.ROVER_FRAGMENT_KEY)?.let { presenter.loadData(it) }
    }

    override fun renderData(photoData: PhotoData) {
        binding.caption.text = photoData.camera.full_name
        imageLoader.loadImageIntoView(photoData.img_src,
            requireContext(),
            binding.roverPic,
            object : OnImageSuccessListener {
                override fun onSuccess() {
                    hideLoading()
                }
            })
    }

    override fun showLoading() {
        binding.progressBar.show()
    }

    override fun hideLoading() {
        binding.progressBar.hide()
    }
}
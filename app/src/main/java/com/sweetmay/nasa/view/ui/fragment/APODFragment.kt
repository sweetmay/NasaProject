package com.sweetmay.nasa.view.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.sweetmay.nasa.App
import com.sweetmay.nasa.R
import com.sweetmay.nasa.databinding.FragmentApodBinding
import com.sweetmay.nasa.model.entity.APOD
import com.sweetmay.nasa.model.repo.NasaRepo
import com.sweetmay.nasa.presenter.APODPresenter
import com.sweetmay.nasa.utils.ApiHolder
import com.sweetmay.nasa.utils.date.DateUtil
import com.sweetmay.nasa.utils.image.IImageLoader
import com.sweetmay.nasa.utils.image.ImageLoader
import com.sweetmay.nasa.utils.image.OnImageSuccessListener
import com.sweetmay.nasa.view.APODView
import com.sweetmay.nasa.view.ui.base.BaseFragment
import moxy.ktx.moxyPresenter

class APODFragment: BaseFragment<FragmentApodBinding>(), APODView {

    lateinit var navController: NavController
    lateinit var imageLoader: IImageLoader

    val presenter: APODPresenter by moxyPresenter {
        APODPresenter(NasaRepo(ApiHolder(App.BASE_URL), DateUtil()), App.API_KEY)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.wiki_base_url)
                    .plus(binding.inputEditText.text.toString()))))
        }
    }

    override fun showApodImage(apod: APOD) {
        with(binding){
                apodImage.visibility = View.VISIBLE
                imageLoader.loadImageIntoView(apod.url,
                        requireContext(),
                        apodImage, object : OnImageSuccessListener {
                    override fun onSuccess() {
                        hideLoading()
                    }
                })
                explanationApod.text = apod.explanation
            }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun showApodVideo(apod: APOD) {
        with(binding){
            apodVideo.visibility = View.VISIBLE
            apodVideo.settings.javaScriptEnabled = true
            apodVideo.loadUrl(apod.url)
        }
    }

    override fun setTitle() {
        binding.title.text = getString(R.string.picture_of_the_day_title)
    }

    override fun showLoading() {
        binding.progressBar.show()
    }

    override fun hideLoading() {
        binding.progressBar.hide()
    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentApodBinding {
        navController = findNavController()
        imageLoader = ImageLoader()
        return FragmentApodBinding.inflate(inflater, container, false)
    }
}
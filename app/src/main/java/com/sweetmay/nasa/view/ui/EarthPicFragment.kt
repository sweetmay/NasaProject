package com.sweetmay.nasa.view.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.sweetmay.nasa.App
import com.sweetmay.nasa.R
import com.sweetmay.nasa.databinding.FragmentEarthPicBinding
import com.sweetmay.nasa.model.repo.NasaRepo
import com.sweetmay.nasa.presenter.EarthPicPresenter
import com.sweetmay.nasa.utils.ApiHolder
import com.sweetmay.nasa.view.EarthPicView
import com.sweetmay.nasa.view.ui.base.BaseFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.ktx.moxyPresenter

class EarthPicFragment: BaseFragment<FragmentEarthPicBinding>(), EarthPicView {

    val presenter: EarthPicPresenter by moxyPresenter {
        EarthPicPresenter(NasaRepo(ApiHolder(App.BASE_URL)),
                App.API_KEY,
                AndroidSchedulers.mainThread())
    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentEarthPicBinding {
        return FragmentEarthPicBinding.inflate(inflater, container, false)
    }

    override fun setImage(url: String) {
        binding.earthPic.load(url)
    }

    override fun setTitle() {
        binding.title.text = getString(R.string.earth_imagery)
    }

    override fun showLoading() {
        binding.progressBar.show()
    }

    override fun hideLoading() {
        binding.progressBar.hide()
    }

}
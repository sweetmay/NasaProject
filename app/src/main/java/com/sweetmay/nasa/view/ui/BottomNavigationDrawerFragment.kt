package com.sweetmay.nasa.view.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView
import com.sweetmay.nasa.R

class BottomNavigationDrawerFragment(): BottomSheetDialogFragment() {

    lateinit var navigationView: NavigationView
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        navigationView = view.findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.earth_pic_menu->{
                    navController.navigate(R.id.earthPicFragment)
                    dismiss()
                }
                R.id.apod_menu->{
                    navController.navigate(R.id.APODFragment)
                    dismiss()
                }
            }
            true
        }
    }
}
package com.firas.smartshop.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.firas.smartshop.R
import com.firas.smartshop.databinding.ProfileLayoutBinding
import com.firas.smartshop.presentation.viewModels.ViewModelProfile
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: ProfileLayoutBinding
    val viewModelProfil by viewModels<ViewModelProfile>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ProfileLayoutBinding.bind(view)
        navigation()
    }


    private fun navigation() {
        binding.profileEdit.setOnClickListener {
            Snackbar.make(binding.root, "coming soon ..", Snackbar.LENGTH_SHORT).show()
        }

        binding.profileLogout.setOnClickListener {
            viewModelProfil.logout()
            findNavController().navigate(R.id.splashScreenFragment)
        }

        binding.profileBack.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }
}
package com.firas.smartshop.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.firas.smartshop.R
import com.firas.smartshop.databinding.SplashScreenLayoutBinding
import com.firas.smartshop.presentation.viewModels.ViewModelSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreenFragment : Fragment() {
    private val TAG = "SplashScreenFragment"

    private val viewModelSplash by viewModels<ViewModelSplashScreen>()
    private lateinit var binding: SplashScreenLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_screen_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SplashScreenLayoutBinding.bind(view)
        viewModelSplash.direction()
        lifecycleScope.launchWhenStarted  {
            viewModelSplash.uiStateFlow.collect() {
                when (it) {
                    true -> {

                        Log.d(TAG,"true")
                        delay(1000)
                        findNavController().navigate(R.id.homeFragment)

                    }
                    false -> {
                        Log.d(TAG,"false")

                        delay(1000)
                        findNavController().navigate(R.id.loginFragment)

                    }
                    null -> {
                        Log.d(TAG,"null")

                    }
                }

            }
        }
    }
}
package com.firas.smartshop.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.firas.smartshop.R
import com.firas.smartshop.data.utils.LoginUiState
import com.firas.smartshop.databinding.AuthentificationLayoutBinding
import com.firas.smartshop.presentation.viewModels.ViewModelAuthentification
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val TAG = "LoginFragment"
    private val viewModelAuthentification by viewModels<ViewModelAuthentification>()
    private lateinit var binding: AuthentificationLayoutBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.authentification_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AuthentificationLayoutBinding.bind(view)
        binding.loginUsername.setText("mor_2314")
        binding.loginPassword.setText("83r5^_")
        binding.loginButton.setOnClickListener {
            initView()

        }

    }


    private fun initView() {


        val name = binding.loginUsername.text.toString()
        val password = binding.loginPassword.text.toString()

        viewModelAuthentification.loginUser(name, password)
        lifecycleScope.launchWhenStarted() {

            viewModelAuthentification.response.collect() {
                when (it) {

                    LoginUiState.Success -> {
                        Log.d(TAG,"succeed :  "+ it.toString())
                        binding.loginProgress.isVisible = false
                        findNavController().navigate(R.id.homeFragment)
                    }

                    LoginUiState.Loading -> {
                        Log.d(TAG,"loading :  ")

                        binding.loginProgress.isVisible = true
                    }

                    is LoginUiState.Error -> {
                        Log.d(TAG,"error :  "+ it.message.toString())

                        binding.loginProgress.isVisible = false
                        Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                    }
                    LoginUiState.Empty -> {

                    }
                }


            }

        }
    }


}


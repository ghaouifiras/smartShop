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
import androidx.recyclerview.widget.GridLayoutManager
import com.firas.smartshop.R
import com.firas.smartshop.data.models.CategoryProduct2
import com.firas.smartshop.data.utils.ResponseState
import com.firas.smartshop.databinding.HomeFragmentBinding
import com.firas.smartshop.presentation.adapters.ProductAdapter
import com.firas.smartshop.presentation.viewModels.HomeFragmentViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {


    private val homeFragmentViewModel by viewModels<HomeFragmentViewModel>()
    private lateinit var binding: HomeFragmentBinding
    private lateinit var homeAdapter: ProductAdapter
    private var category2 = mutableListOf<CategoryProduct2>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = HomeFragmentBinding.bind(view)
        initRecyclerView()
        bindingIntent()
        getAllCategories()
        getAllProducts()


    }


    private fun getAllProducts() {
        homeFragmentViewModel.getAllArticles()

        lifecycleScope.launchWhenCreated {
            homeFragmentViewModel.stateFlowResult.collect() {
                when (it) {
                    is ResponseState.Error -> {
                        Log.d("HomeFragment Error : ", it.msg.toString())
                        binding.progressbar.isVisible = false

                        showMsg(it.msg.toString())
                    }
                    is ResponseState.Loading -> {
                        binding.progressbar.isVisible = true
                        Log.d("HomeFragment Loading : ", "isLoading")

                    }
                    is ResponseState.Success -> {
                        binding.progressbar.isVisible = false
                        it.data?.let { it1 -> homeAdapter.setData(it1) }

                        Log.d("HomeFragment Succes : ", it.data.toString())

                    }
                    null -> {
                        Log.d("HomeFragment nulll : ", "nuuul")

                    }
                }

            }

        }
    }

    private fun showMsg(msg: String) {
        Snackbar.make(binding.homeFragmentRoot, msg, Snackbar.LENGTH_SHORT).show()

    }

    private fun initRecyclerView() {
        homeAdapter = ProductAdapter(ArrayList())
        binding.homeRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@HomeFragment.context, 2)
            adapter = homeAdapter

        }
    }

    private fun bindingIntent() {

        homeAdapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment3(it)
            findNavController().navigate(action)
        }

        binding.homeProfile.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }

        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            group.isSingleSelection = true
            val chipid = group.checkedChipId
            val category = category2[chipid].name
            getAllProductsBYCategory(category)

        }

    }

    private fun getAllCategories() {

        homeFragmentViewModel.getAllCategories()
        lifecycleScope.launchWhenStarted() {
            homeFragmentViewModel.stateFlowResultCategories.collect {
                when (it) {
                    is ResponseState.Error -> {
                        Snackbar.make(binding.root, it.msg.toString(), Snackbar.LENGTH_SHORT).show()
                    }
                    is ResponseState.Loading -> {

                    }
                    is ResponseState.Success -> {
                        it.data?.let { response ->
                            val chip = Chip(requireContext())
                            chip.text = "all"
                            chip.id = 0
                            chip.isChecked = true
                            category2.clear()
                            binding.chipGroup.removeAllViews()
                            binding.chipGroup.addView(chip)
                            category2.add(CategoryProduct2(0, "all"))

                            it.data.forEachIndexed() { index, s ->
                                val chip = Chip(requireContext())
                                chip.apply {
                                    text = s
                                    id = index + 1
                                    category2.add(CategoryProduct2(index, s))

                                }
                                binding.chipGroup.addView(chip)
                            }

                        }
                    }
                    null -> {
                       // Snackbar.make(binding.root, "isNull", Snackbar.LENGTH_SHORT).show()

                    }
                }

            }
        }

    }

    private fun getAllProductsBYCategory(category: String) {

        homeFragmentViewModel.getProductsByCategory(category)
        lifecycleScope.launchWhenCreated {
            homeFragmentViewModel.stateFlowResult.collect() {
                when (it) {
                    is ResponseState.Error -> {
                        showMsg(it.msg.toString())
                    }
                    is ResponseState.Loading -> {
                    }
                    is ResponseState.Success -> {
                        it.data?.let { it ->
                            homeAdapter.setData(it)
                        }
                    }
                    null -> {

                    }
                }
            }
        }
    }
}
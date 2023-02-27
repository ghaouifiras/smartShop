package com.firas.smartshop.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firas.smartshop.data.models.CategoryProduct
import com.firas.smartshop.data.models.Product
import com.firas.smartshop.data.utils.ResponseState
import com.firas.smartshop.domain.useCase.HomeFragmentCategoryUseCase
import com.firas.smartshop.domain.useCase.HomeFragmentGetAllArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val homeFragmentGetAllArticleUseCase: HomeFragmentGetAllArticleUseCase,
    private val homeFragmentCategoryUseCase: HomeFragmentCategoryUseCase

) :
    ViewModel() {

    private val _stateProductsResult: MutableStateFlow<ResponseState<List<Product>>?> =
        MutableStateFlow(null)

    val stateFlowResult = _stateProductsResult.asStateFlow()


    private val _stateCategoriesResult: MutableStateFlow<ResponseState<CategoryProduct>?> =
        MutableStateFlow(null)

    val stateFlowResultCategories = _stateCategoriesResult.asStateFlow()


    fun getAllArticles() {

        viewModelScope.launch {
            homeFragmentGetAllArticleUseCase.getAllProducts().collect() {

                _stateProductsResult.value = it

            }
        }


    }


    fun getAllCategories() {

        viewModelScope.launch {
            homeFragmentCategoryUseCase.getAllCategories().collect() {
                _stateCategoriesResult.value = it
            }
        }


    }


    fun getProductsByCategory(category: String) {
        viewModelScope.launch {
            homeFragmentGetAllArticleUseCase.getAllProductsByCategory(category).collect() {
                if (category != "all")
                    _stateProductsResult.value = it
                else
                    getAllArticles()
            }
        }
    }


}
package com.ramesh.baseproject.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramesh.baseproject.data.remote.response.DataResponse
import com.ramesh.baseproject.data.repository.UserRepository
import com.ramesh.baseproject.utils.common.Resource
import com.ramesh.baseproject.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 *  Author : @Ramesh Racharla
 *
 * */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkHelper: NetworkHelper, private val userRepository: UserRepository
) : ViewModel() {
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val xAuth = userRepository.getAccessToken()
    val getDataResponse: MutableLiveData<Resource<DataResponse>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()

    fun getEmployeeData() = viewModelScope.launch {
        if (networkHelper.isNetworkConnected()) {
            loading.postValue(true)

            val response = userRepository.getData(
                ""
            )
            if (response.isSuccessful) {
                getDataResponse.postValue(Resource.success(response.body()))
            } else {

                messageString.postValue(Resource.error(response.body()?.message.toString()))
            }
            loading.postValue(false)
        }
    }
}
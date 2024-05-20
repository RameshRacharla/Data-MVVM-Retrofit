package com.ramesh.baseproject.ui.employeedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramesh.baseproject.R
import com.ramesh.baseproject.data.remote.response.EmployeeDetailsResponse
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
class EmployeeDetailsViewModel @Inject constructor(
    private val networkHelper: NetworkHelper, private val userRepository: UserRepository
) : ViewModel() {
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val getDataResponse: MutableLiveData<Resource<EmployeeDetailsResponse>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()

    fun getEmployeeDetails(id: String) = viewModelScope.launch {
        if (networkHelper.isNetworkConnected()) {
            loading.postValue(true)

            val response = userRepository.getEmployeeDetails(id)
            if (response.isSuccessful) {
                getDataResponse.postValue(Resource.success(response.body()))
            } else {
                messageString.postValue(Resource.error(response.body()?.status.toString()))
            }
            loading.postValue(false)
        }
    }


}
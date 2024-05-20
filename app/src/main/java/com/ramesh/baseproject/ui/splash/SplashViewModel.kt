package com.ramesh.baseproject.ui.splash

import androidx.lifecycle.ViewModel
import com.ramesh.baseproject.data.repository.UserRepository
import com.ramesh.baseproject.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *
 *  Author : @Ramesh Racharla
 *
 * */
@HiltViewModel
class SplashViewModel @Inject constructor(
    networkHelper: NetworkHelper, private val userRepository: UserRepository
) : ViewModel() {


}
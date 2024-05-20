package com.ramesh.baseproject.data.remote.response

import com.google.gson.annotations.SerializedName


data class EmployeeDetailsResponse(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("data")
    val list: DataList? = null
)
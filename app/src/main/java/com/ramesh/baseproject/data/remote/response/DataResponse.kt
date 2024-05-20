package com.ramesh.baseproject.data.remote.response

import com.google.gson.annotations.SerializedName


data class DataResponse(
    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("data")
    val list: List<DataList?>? = null
)
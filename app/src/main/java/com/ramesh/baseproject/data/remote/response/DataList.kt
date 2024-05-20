package com.ramesh.baseproject.data.remote.response

import com.google.gson.annotations.SerializedName


data class DataList(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("employee_name")
    val employee_name: String? = null,

    @field:SerializedName("employee_salary")
    val employee_salary: Int? = null,

    @field:SerializedName("employee_age")
    val employee_age: Int? = null,

    @field:SerializedName("profile_image")
    val profile_image: String? = null
)
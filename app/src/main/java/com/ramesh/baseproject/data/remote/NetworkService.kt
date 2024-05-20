package com.ramesh.baseproject.data.remote

import com.ramesh.baseproject.data.remote.request.DataRequest
import com.ramesh.baseproject.data.remote.response.DataResponse
import com.ramesh.baseproject.data.remote.response.EmployeeDetailsResponse
import retrofit2.Response
import retrofit2.http.*

/**
 *
 *  Author : @Ramesh Racharla
 *
 * */
interface NetworkService {

    @GET(Endpoints.GET_EMPLOYEES)
    suspend fun getEmployeesData(
    ): Response<DataResponse>

    @GET(Endpoints.GET_EMPLOYEE_DETAILS)
    suspend fun getEmployeeDetails(
        @Path("id") id: String
    ): Response<EmployeeDetailsResponse>

    @POST(Endpoints.GET_DATA)
    suspend fun getData(
        @Header("Authorization") token: String, @Body request: DataRequest
    ): Response<DataResponse>
}

package com.mungeun.data.api


import com.mungeun.data.response.gymresponse.Data

import retrofit2.http.GET

interface GymApiService {

    @GET("api/gyms")
    suspend fun getGym(
    ) : List<Data>


}
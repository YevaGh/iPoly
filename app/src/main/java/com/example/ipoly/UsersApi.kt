package com.example.ipoly

import com.example.ipoly.models.AbsenceGpa
import com.example.ipoly.models.UserData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {

    @GET("Users/authenticate")
    suspend fun isAuthenticated(
        @Query("email") email: String,
        @Query("password") password: String):Response<String>

    @GET("Users/get_user_data")
    suspend fun getUsersData(@Query("user_id")studentId:Long):Response<UserData>

    @GET("Users/get_user_id_by_session")
    suspend fun getUserId(@Query("session_id")sessionId:String):Response<Long>

    @GET("Users/get_absence_gpa")
    suspend fun getAbsenceGpa(@Query("user_id")studentId:Long):Response<AbsenceGpa>
}
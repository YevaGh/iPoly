package com.example.ipoly.viewmodels

import androidx.lifecycle.ViewModel
import com.example.ipoly.RetrofitInstance
import com.example.ipoly.models.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountViewModel : ViewModel() {
    private val usersApi = RetrofitInstance.api

    suspend fun getData(stId: Long): UserData {
        return withContext(Dispatchers.IO) {

            val response = usersApi.getUsersData(stId)
            if (response.isSuccessful && response.body() != null) {
                val user = response.body()!!
                UserData(user.name, user.lastname, user.bloodGroup, user.mobile, user.sex)
            } else {
                UserData("", "", "", "", "")
            }
        }
    }
}
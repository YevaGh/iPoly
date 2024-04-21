package com.example.ipoly.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ipoly.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthUserViewModel(application: Application) : AndroidViewModel(application) {
    private val usersApi = RetrofitInstance.api

    fun cacheSessionIdAndEmail(sessionId: String,email:String) {
        val sharedPreferences =
            getApplication<Application>().getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("sessionId", sessionId)
        editor.putString("email",email)
        editor.apply()
    }

    fun getSessionId(): String {
        val sharedPreferences =
            getApplication<Application>().getSharedPreferences("user_data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("sessionId", "").toString()
    }

    fun getEmail(): String {
        val sharedPreferences =
            getApplication<Application>().getSharedPreferences("user_data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("email", "").toString()
    }


  suspend fun getUserId(): Long {
      return withContext(Dispatchers.IO) {
          val response = usersApi.getUserId(getSessionId())
          if (response.isSuccessful && response.body() != null) {
              response.body() ?: 0
          } else {
              0
          }
      }
  }

}
package com.example.ipoly.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ipoly.RetrofitInstance
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val usersApi = RetrofitInstance.api

    suspend fun authenticate(email: String, password: String, successCallback: (String) -> Unit) {

        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Coroutine", "Inside coroutine block")

            val response = usersApi.isAuthenticated(email, password)
            Log.d("Coroutine", "authenticated")

            if (response.isSuccessful && response.body() != null) {
                val sessionId = response.body() ?: return@launch
                launch(Dispatchers.Main) {
                    successCallback.invoke(sessionId)
                }
            }
        }
    }
}
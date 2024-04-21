package com.example.ipoly.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ipoly.RetrofitInstance
import com.example.ipoly.models.AbsenceGpa
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import kotlin.math.abs

class AbsenceGpaViewModel : ViewModel() {
    private val usersApi = RetrofitInstance.api

    suspend fun getAbsenceAndGpa(userId: Long): AbsenceGpa {
        val absGpa = AbsenceGpa(1, 1.0)
        return withContext(Dispatchers.IO) {
            Log.d("Coroutine", "Absence and gpa")

            val response = usersApi.getAbsenceGpa(userId)
            if (response.isSuccessful && response.body() != null) {
                val responseBody = response.body()!!
                AbsenceGpa(responseBody.absenceCount, responseBody.gpa)

            } else {
                absGpa
            }
        }
    }
}
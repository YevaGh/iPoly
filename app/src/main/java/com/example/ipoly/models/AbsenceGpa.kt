package com.example.ipoly.models

import com.google.gson.annotations.SerializedName

data class AbsenceGpa(
    @SerializedName("absenceCount") val absenceCount: Int,
    @SerializedName("gpa") val gpa: Double
)

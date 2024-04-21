package com.example.ipoly.models

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("name")val name: String,
    @SerializedName("lastname")val lastname: String,
    @SerializedName("bloodGroup")val bloodGroup: String,
    @SerializedName("mobile")val mobile: String,
    @SerializedName("sex")val sex:String
)

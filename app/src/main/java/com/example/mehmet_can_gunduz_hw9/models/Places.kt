package com.example.mehmet_can_gunduz_hw9.models

data class Place(
    val key : String = "",
    val item : Places? = null
)

data class Places(

    val title : String = "",
    val city : String="",
    val note : String="")

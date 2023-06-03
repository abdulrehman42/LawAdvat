package com.example.lawadvat.ui.appintment.appointment

data class Appoint(
    val name_lawyer: String? =null,
    val lawyer_img:String?=null,
    val fee:String?=null,
    var check:String?="false",
    val lawyer_location:String?=null,
    val client_name:String?=null,
    val client_img:String?=null,
    val time:String?=null,
    val date:String?=null
)
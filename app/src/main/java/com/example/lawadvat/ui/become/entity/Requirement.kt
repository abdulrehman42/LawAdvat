package com.example.lawadvat.ui.become.entity

import com.example.lawadvat.ui.review.Review_entity

data class Requirement(
    val img: String? = null,
    val name: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val cnic: String? = null,
    val gender: String? = null,
    val general__id: String? = null,
    val high_court_id: String? = null,
    val supreme_court_id: String? = null,
    val country: String? = null,
    val province: String? = null,
    val city: String? = null,
    val office_location: String? = null,
    val description: String? = null,
    val experince: String? = null,
    val fee: String? = null,
    val first_specilize: String? = null,
    val second_specilize: String? = null,
    val third_specilize: String? = null,
    val fourth_specilize: String? = null,
    val llb: String? = null,
    val llm: String? = null,
    val bar_of_law: String? = null,
    val phd: String? = null,
    val id:String?=null,
    var layer: Boolean? = false,
    var satisfy:Int? =null

    )

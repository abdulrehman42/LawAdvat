package com.example.lawadvat.ui.become.entity

import android.os.Parcel
import android.os.Parcelable


data class Requirement(
    val img: String? = null,
    var name: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var cnic: String? = null,
    var gender: String? = null,
    var general__id: String? = null,
    var high_court_id: String? = null,
    var supreme_court_id: String? = null,
    var country: String? = null,
    var province: String? = null,
    var city: String? = null,
    var office_location: String? = null,
    var description: String? = null,
    var experince: String? = null,
    var fee: String? = null,
    var first_specilize: String? = null,
    var second_specilize: String? = null,
    var third_specilize: String? = null,
    var fourth_specilize: String? = null,
    var llb: String? = null,
    var llm: String? = "--",
    var bar_of_law: String? = "--",
    var phd: String? = "--",
    val id:String?=null,
    var layer: Boolean? = false,
    var satisfy:Int? =null

    ) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt().toString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(img)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(phone)
        parcel.writeString(cnic)
        parcel.writeString(gender)
        parcel.writeString(general__id)
        parcel.writeString(high_court_id)
        parcel.writeString(supreme_court_id)
        parcel.writeString(country)
        parcel.writeString(province)
        parcel.writeString(city)
        parcel.writeString(office_location)
        parcel.writeString(description)
        parcel.writeString(experince)
        parcel.writeString(fee)
        parcel.writeString(first_specilize)
        parcel.writeString(second_specilize)
        parcel.writeString(third_specilize)
        parcel.writeString(fourth_specilize)
        parcel.writeString(llb)
        parcel.writeString(llm)
        parcel.writeString(bar_of_law)
        parcel.writeString(phd)
        parcel.writeString(id)
        parcel.writeInt(satisfy ?: 0)
        parcel.writeValue(layer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Requirement> {
        override fun createFromParcel(parcel: Parcel): Requirement {
            return Requirement(parcel)
        }

        override fun newArray(size: Int): Array<Requirement?> {
            return arrayOfNulls(size)
        }
    }

}

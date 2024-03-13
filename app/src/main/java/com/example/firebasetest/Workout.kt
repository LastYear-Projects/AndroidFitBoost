package com.example.firebasetest

import android.os.Parcel
import android.os.Parcelable

data class Workout(
    val image: String, val name: String, val subtitle: String,
    val duration: String,
    val owner:String,
    val exercises: ArrayList<*>
)  : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        arrayListOf<Execerise>().apply {
            parcel.readList(this as List<*>,Execerise::class.java.classLoader)
        }
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(image)
        dest.writeString(name)
        dest.writeString(subtitle)
        dest.writeString(duration)
        dest.writeString(owner)
        dest.writeList(exercises as List<*>?)

    }

    companion object CREATOR : Parcelable.Creator<Workout> {
        override fun createFromParcel(parcel: Parcel): Workout {
            return Workout(parcel)
        }

        override fun newArray(size: Int): Array<Workout?> {
            return arrayOfNulls(size)
        }
    }
}

package com.example.firebasetest

import android.os.Parcel
import android.os.Parcelable

data class Gym(val image: String, val name: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(image)
        dest.writeString(name)
    }

    companion object CREATOR : Parcelable.Creator<Gym> {
        override fun createFromParcel(parcel: Parcel): Gym {
            return Gym(parcel)
        }

        override fun newArray(size: Int): Array<Gym?> {
            return arrayOfNulls(size)
        }
    }
}

package com.example.firebasetest

import android.os.Parcel
import android.os.Parcelable

data class Execerise(val name: String, val reps: String, val sets: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(reps)
        dest.writeString(sets)
    }

    companion object CREATOR : Parcelable.Creator<Execerise> {
        override fun createFromParcel(parcel: Parcel): Execerise {
            return Execerise(parcel)
        }

        override fun newArray(size: Int): Array<Execerise?> {
            return arrayOfNulls(size)
        }
    }
}

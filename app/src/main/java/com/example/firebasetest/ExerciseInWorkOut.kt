package com.example.firebasetest

import android.os.Parcel
import android.os.Parcelable

data class ExerciseInWorkOut (
    var name: String,
    var reps: String,
    var sets: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(reps)
        parcel.writeString(sets)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ExerciseInWorkOut> {
        override fun createFromParcel(parcel: Parcel): ExerciseInWorkOut {
            return ExerciseInWorkOut(parcel)
        }

        override fun newArray(size: Int): Array<ExerciseInWorkOut?> {
            return arrayOfNulls(size)
        }
    }
}
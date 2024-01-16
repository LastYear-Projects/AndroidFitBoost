package com.example.firebasetest.user.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class RoomUser(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fullName: String,
    val email: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(fullName)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RoomUser> {
        override fun createFromParcel(parcel: Parcel): RoomUser {
            return RoomUser(parcel)
        }

        override fun newArray(size: Int): Array<RoomUser?> {
            return arrayOfNulls(size)
        }
    }
}

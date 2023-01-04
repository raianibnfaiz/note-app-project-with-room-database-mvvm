package com.raian.noteapproomdatabase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "writer_table")
@Parcelize
data class Writer(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val age: Int
): Parcelable
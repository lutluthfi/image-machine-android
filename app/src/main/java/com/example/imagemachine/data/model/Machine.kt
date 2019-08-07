package com.example.imagemachine.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "machine")
data class Machine(@PrimaryKey @ColumnInfo(name = "id") var id: Long,
                   @ColumnInfo(name = "name") var name: String,
                   @ColumnInfo(name = "type") var type: String,
                   @ColumnInfo(name = "qrCode")var qrCode: String,
                   @ColumnInfo(name = "date")var date: String)
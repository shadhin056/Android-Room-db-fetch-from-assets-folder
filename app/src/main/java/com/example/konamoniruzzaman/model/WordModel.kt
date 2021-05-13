package com.shadhin.android_jetpack.view.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "wordmodel")
data class WordModel(
    @NonNull
    @ColumnInfo(name = "word")
    var word: String?,

) {

    @ColumnInfo(name = "uuid")
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}

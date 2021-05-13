package com.shadhin.android_jetpack.view.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDataDao {
    @Insert
    suspend fun insertAll(vararg word: WordModel): List<Long>

    @Query("SELECT * FROM wordmodel Where word  LIKE '%' || :search || '%'  ORDER BY uuid DESC")
    suspend fun getAllWord(search: String): List<WordModel>

    @Query("DELETE FROM wordmodel")
    suspend fun deleteData()


}
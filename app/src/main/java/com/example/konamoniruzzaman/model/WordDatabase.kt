package com.shadhin.android_jetpack.view.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = arrayOf(WordModel::class), version = 2)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDataDao

    companion object {
        @Volatile
        private var instance: WordDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            WordDatabase::class.java,
            "worddatabase"
        ) .createFromAsset("databases/words.db") .fallbackToDestructiveMigration().build()


    }
}
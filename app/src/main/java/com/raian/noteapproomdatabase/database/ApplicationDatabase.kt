package com.raian.noteapproomdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.raian.noteapproomdatabase.dao.ApplicationDao
import com.raian.noteapproomdatabase.model.Note
import com.raian.noteapproomdatabase.model.Writer

@Database(entities = [Note::class , Writer::class],version = 1, exportSchema = false )
abstract class ApplicationDatabase: RoomDatabase() {
    abstract fun getDao(): ApplicationDao

    companion object{

    @Volatile
    private var INSTANCE:ApplicationDatabase?=null
        fun getDatabase(context: Context): ApplicationDatabase? {
            val temInstance = INSTANCE
            if(temInstance != null){
                return temInstance
            }
            synchronized(this){
                val instance  = Room.databaseBuilder(
                    context.applicationContext,
                    ApplicationDatabase::class.java,
                    "application_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
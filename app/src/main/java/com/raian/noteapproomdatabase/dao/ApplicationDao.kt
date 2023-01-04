package com.raian.noteapproomdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.raian.noteapproomdatabase.model.Note
import com.raian.noteapproomdatabase.model.Writer

@Dao
interface ApplicationDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note:Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWriter(writer:Writer)

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun readAllNote(): LiveData<List<Note>>

    @Query("SELECT * FROM writer_table ORDER BY id DESC")
    fun readAllWriter(): LiveData<List<Writer>>

    @Update
    suspend fun updateNote(note:Note)

    @Delete
    suspend fun deleteNote(note:Note)

    @Update
    suspend fun updateWriter(writer:Writer)

    @Delete
    suspend fun deleteWriter(writer: Writer)
}
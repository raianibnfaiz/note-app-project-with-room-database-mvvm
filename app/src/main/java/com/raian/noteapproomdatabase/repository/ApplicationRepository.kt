package com.raian.noteapproomdatabase.repository

import androidx.lifecycle.LiveData
import com.raian.noteapproomdatabase.dao.ApplicationDao
import com.raian.noteapproomdatabase.model.Note
import com.raian.noteapproomdatabase.model.Writer

class ApplicationRepository(private val applicationDao: ApplicationDao) {
//    val readAllNoteData: LiveData<List<Note>> = applicationDao.readAllNote()
//    val readAllWriterData: LiveData<List<Writer>> = applicationDao.readAllWriter()

    fun readAllNoteData(): LiveData<List<Note>>
    {
        return applicationDao.readAllNote()
    }
    fun readAllWriterData(): LiveData<List<Writer>> {
        return applicationDao.readAllWriter()
    }

    suspend fun addNote(note: Note){
        applicationDao.addNote(note)
    }

    suspend fun addWriter(writer: Writer){
        applicationDao.addWriter(writer)
    }

    suspend fun updateNote(note:Note){
        applicationDao.updateNote(note)
    }
    suspend fun deleteNote(note: Note){
        applicationDao.deleteNote(note)
    }

    suspend fun updateWriter(writer: Writer){
        applicationDao.updateWriter(writer)
    }

    suspend fun deleteWriter(writer: Writer){
        applicationDao.deleteWriter(writer)
    }



}
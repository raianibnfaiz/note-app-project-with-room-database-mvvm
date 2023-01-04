package com.raian.noteapproomdatabase.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.raian.noteapproomdatabase.database.ApplicationDatabase
import com.raian.noteapproomdatabase.repository.ApplicationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApplicationViewModel(application: Application): AndroidViewModel(application) {
    val readAllNoteData : LiveData<List<Note>>
    val readAllWriterData : LiveData<List<Writer>>
    private val repository: ApplicationRepository


    init {
        val applicationDao = ApplicationDatabase.getDatabase(application)?.getDao()
        repository = applicationDao?.let { ApplicationRepository(it) }!!
        readAllNoteData = repository.readAllNoteData()
        readAllWriterData = repository.readAllWriterData()
    }

    fun addNote(note:Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }
    fun addWriter(writer: Writer){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addWriter(writer)
        }
    }

    fun updateNote(note:Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateNote(note)
        }
    }
    fun deleteNote(note:Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

    fun updateWriter(writer: Writer){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateWriter(writer)
        }
    }

    fun deleteWriter(writer: Writer){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWriter(writer)
        }
    }

}
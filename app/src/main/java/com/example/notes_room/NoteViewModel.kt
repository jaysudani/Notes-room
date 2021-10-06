package com.example.notes_room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notes_room.NoteDatabase.Companion.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application){
    private val repository : NotesRepository
    val allNotes : LiveData<List<Note>>

    init{
        val dao = getDatabase(application).getNoteDao()
        repository = NotesRepository(dao)
        allNotes=repository.allNote
    }

    fun deletNote(note : Note)=viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }

    fun inserttNote(note : Note)=viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }
}
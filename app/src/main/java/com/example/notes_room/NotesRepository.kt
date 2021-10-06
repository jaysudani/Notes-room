package com.example.notes_room

import androidx.lifecycle.LiveData

class NotesRepository(private val notedao : NoteDao) {

    val allNote : LiveData<List<Note>> = notedao.getAllnotes()

    suspend fun insert(note : Note){
        notedao.insert(note)
    }

    suspend fun delete(note :Note){
        notedao.delete(note)
    }
}
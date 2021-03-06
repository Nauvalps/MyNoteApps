package com.dicoding.picodiploma.mynoteapps.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.mynoteapps.db.Note
import com.dicoding.picodiploma.mynoteapps.repository.NoteRepository

class MainViewModel(application: Application) : ViewModel() {
    private val noteRepository: NoteRepository = NoteRepository(application)

    fun getAllNotes() : LiveData<List<Note>> = noteRepository.getAllNotes()
}
package com.dicoding.picodiploma.mynoteapps.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.mynoteapps.db.Note
import com.dicoding.picodiploma.mynoteapps.repository.NoteRepository

class NoteAddUpdateViewModel(application: Application) : ViewModel() {
    private val noteRepository: NoteRepository = NoteRepository(application)

    fun insert(note: Note) {
        noteRepository.insert(note)
    }

    fun update(note: Note) {
        noteRepository.update(note)
    }

    fun delete(note: Note) {
        noteRepository.delete(note)
    }
}
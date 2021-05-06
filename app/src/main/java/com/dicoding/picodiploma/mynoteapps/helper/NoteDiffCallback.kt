package com.dicoding.picodiploma.mynoteapps.helper

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.picodiploma.mynoteapps.db.Note

class NoteDiffCallback(private val oldNoteList: List<Note>, private val newNoteList: List<Note>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldNoteList.size
    }

    override fun getNewListSize(): Int {
        return newNoteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNoteList[oldItemPosition].id == newNoteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = oldNoteList[oldItemPosition]
        val newEmployee = newNoteList[newItemPosition]
        return oldEmployee.title == newEmployee.title && oldEmployee.description == newEmployee.description
    }

}
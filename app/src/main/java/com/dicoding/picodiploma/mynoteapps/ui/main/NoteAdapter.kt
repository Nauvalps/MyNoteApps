package com.dicoding.picodiploma.mynoteapps.ui.main

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.mynoteapps.databinding.ItemNoteBinding
import com.dicoding.picodiploma.mynoteapps.db.Note
import com.dicoding.picodiploma.mynoteapps.helper.NoteDiffCallback
import com.dicoding.picodiploma.mynoteapps.ui.insert.NoteAddUpdateActivity

class NoteAdapter(private val activity: Activity) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    private val listNotes = ArrayList<Note>()

    fun setListNotes(lisNote: List<Note>) {
        val diffCallback = NoteDiffCallback(this.listNotes, lisNote)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.listNotes.clear()
        this.listNotes.addAll(lisNote)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            with(binding) {
                textTitle.text = note.title
                textDate.text = note.date
                textDescription.text = note.description
                cardItemNote.setOnClickListener {
                    val intent = Intent(activity, NoteAddUpdateActivity::class.java)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, bindingAdapterPosition)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                    activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE)
                }
            }
        }

    }
}
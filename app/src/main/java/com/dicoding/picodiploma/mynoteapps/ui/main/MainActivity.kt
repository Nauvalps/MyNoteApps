package com.dicoding.picodiploma.mynoteapps.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.mynoteapps.R
import com.dicoding.picodiploma.mynoteapps.databinding.ActivityMainBinding
import com.dicoding.picodiploma.mynoteapps.db.Note
import com.dicoding.picodiploma.mynoteapps.helper.ViewModelFactory
import com.dicoding.picodiploma.mynoteapps.ui.insert.NoteAddUpdateActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {


    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding

    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val mainViewModel = obtainViewModel(this@MainActivity)
        mainViewModel.getAllNotes().observe(this, noteObserver)
        adapter = NoteAdapter(this)

        binding?.recyclerNote?.layoutManager = LinearLayoutManager(this)
        binding?.recyclerNote?.setHasFixedSize(true)
        binding?.recyclerNote?.adapter = adapter

        binding?.floatingAdd?.setOnClickListener {
            if (it.id == R.id.floating_add) {
                val intent = Intent(this, NoteAddUpdateActivity::class.java)
                startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_ADD)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (requestCode == NoteAddUpdateActivity.REQUEST_ADD) {
                if (resultCode == NoteAddUpdateActivity.RESULT_ADD) {
                    showSnackbarMessage(getString(R.string.added))
                }
            } else if (requestCode == NoteAddUpdateActivity.REQUEST_UPDATE) {
                if (resultCode == NoteAddUpdateActivity.RESULT_UPDATE) {
                    showSnackbarMessage(getString(R.string.changed))
                } else if (resultCode == NoteAddUpdateActivity.RESULT_DELETE) {
                    showSnackbarMessage(getString(R.string.deleted))
                }
            }
        }
    }

    private fun showSnackbarMessage(message: String) {
        Snackbar.make(binding?.root as View, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(MainViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }

    private val noteObserver = Observer<List<Note>> {
        if (it != null) {
            adapter.setListNotes(it)
        }
    }
}
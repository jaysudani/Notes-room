package com.example.notes_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesRVAdapter {
    lateinit var viewModel : NoteViewModel
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inputtext=findViewById<EditText>(R.id.input)
        val submit=findViewById<Button>(R.id.submit)

        recyclerView=findViewById(R.id.recyclerview)
        recyclerView.layoutManager= LinearLayoutManager(this)
        val adapter = NoteRVadapter(this,this)
        recyclerView.adapter=adapter
        viewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)
            }

        })
        submit.setOnClickListener {
            val text = inputtext.text.toString()
            if(text.isNotEmpty()){
                viewModel.inserttNote(Note(text))
                Toast.makeText(this,"${text} Deleted",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onItemClicked(note: Note) {
        viewModel.deletNote(note)
        Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_SHORT).show()
    }
}
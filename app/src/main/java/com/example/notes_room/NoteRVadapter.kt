package com.example.notes_room

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class NoteRVadapter(private val context : Context, private val listner : INotesRVAdapter) : RecyclerView.Adapter<NoteRVadapter.NotesViewHolder>() {

    val allNotes = ArrayList<Note>()
    inner class NotesViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
        val textview = itemview.findViewById<TextView>(R.id.text)
        val delet4eButton = itemview.findViewById<ImageView>(R.id.deleteboton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder=NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.delet4eButton.setOnClickListener {
            listner.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote= allNotes[position]
        holder.textview.text=currentNote.text
    }
    fun updateList(newList : List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = allNotes.size
}

interface INotesRVAdapter{
    fun onItemClicked(note: Note)
}
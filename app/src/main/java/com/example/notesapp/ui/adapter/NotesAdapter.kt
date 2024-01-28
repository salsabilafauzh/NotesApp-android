package com.example.notesapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R

class NotesAdapter(private val context: Context, private val notesList: List<String>) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_notes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notesList[position]
        holder.textViewNote.text = note
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNote: TextView = itemView.findViewById(R.id.noteTitle)
    }
}

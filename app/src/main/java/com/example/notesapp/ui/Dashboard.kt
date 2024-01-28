package com.example.notesapp.ui

import AuthTokenManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.databinding.ActivityDashboardBinding
import com.example.notesapp.network.RetfofitBuilder
import com.example.notesapp.repository.NotesRepository
import com.example.notesapp.ui.adapter.NotesAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var authTokenManager: AuthTokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.myRecyclerViewNotes
        recyclerView.layoutManager = LinearLayoutManager(this)
        authTokenManager = AuthTokenManager(this)
        val accessToken = authTokenManager.getAccessToken()
        val notes = NotesRepository(RetfofitBuilder.retrofit)

        GlobalScope.launch {
            try {
                val titleList = notes.getNotes().data!!.notes.map { it.title }
                Log.e("Dashboard", "cek list title: $titleList")
                notesAdapter = NotesAdapter(this@Dashboard, titleList)
                binding.myRecyclerViewNotes.adapter = notesAdapter
            } catch (e: Exception) {
                Log.e("Dashboard", "Exception: $accessToken")
                Log.e("Dashboard", "Exception: ${e!!.message}")
            }
        }
    }


}
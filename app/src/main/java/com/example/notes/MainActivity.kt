package com.example.notes

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Define the variables for the views
    private lateinit var editTextNote: EditText
    private lateinit var buttonAddNote: Button
    private lateinit var textViewNotes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the views
        editTextNote = findViewById(R.id.editTextNote)
        buttonAddNote = findViewById(R.id.buttonAddNote)
        textViewNotes = findViewById(R.id.textViewNotes)

        // Set a click listener on the "Add Note" button
        buttonAddNote.setOnClickListener { addNote() }
        loadSavedNotes()
    }

    private fun addNote() {
        // Get the note text from the EditText
        val noteText = editTextNote.text.toString().trim()

        if (noteText.isNotEmpty()) {
            // Append the new note to the existing text in TextView
            val currentNotes = textViewNotes.text.toString()
            val newNotes = "$currentNotes\n- $noteText"
            textViewNotes.text = newNotes

            saveNote(newNotes)

            // Clear the EditText
            editTextNote.text.clear()
        } else {
            Toast.makeText(this, "Please enter a note", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveNote(noteText: String) {
        val sharedPreferences = getSharedPreferences("MyNotes", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("notes", noteText)
        editor.apply()
    }

    private fun loadSavedNotes() {
        val sharedPreferences = getSharedPreferences("MyNotes", Context.MODE_PRIVATE)
        val savedNotes = sharedPreferences.getString("notes", "")
        textViewNotes.text = savedNotes
    }
}

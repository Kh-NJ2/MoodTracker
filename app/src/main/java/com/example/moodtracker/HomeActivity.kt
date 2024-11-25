package com.example.moodtracker
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var happyButton: Button
    private lateinit var neutralButton: Button
    private lateinit var sadButton: Button
    private lateinit var moodHistoryListView: ListView
    private lateinit var adapter: ArrayAdapter<String> // Adapter to manage list updates
    private val moodHistory = mutableListOf<String>() // To store the mood history

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN //hides status bar

        happyButton = findViewById(R.id.happyButton)
        neutralButton = findViewById(R.id.neutralButton)
        sadButton = findViewById(R.id.sadButton)
        moodHistoryListView = findViewById(R.id.moodHistoryListView)

        // Initialize adapter and bind it to the ListView
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, moodHistory)
        moodHistoryListView.adapter = adapter


        happyButton.setOnClickListener { addMood("Happy") }
        neutralButton.setOnClickListener { addMood("Neutral") }
        sadButton.setOnClickListener { addMood("Sad") }
    }

    private fun addMood(mood: String) {
        val timestamp = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault()).format(Date())

        // Add mood to the list with a timestamp
        moodHistory.add("$mood - $timestamp")

        // Notify the adapter that the data has changed
        adapter.notifyDataSetChanged()
    }
}

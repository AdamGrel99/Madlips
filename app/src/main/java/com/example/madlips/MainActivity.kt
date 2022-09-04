package com.example.madlips

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {

    var choice = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    fun onClickButton(view: View) {
        val intent = Intent(this, QuestionsActivity::class.java)
        intent.putExtra("choice", choice)
        // put extra string to next activity
        startActivity(intent)
    }

    fun setup(){
        val listOfStories = arrayOf("Duch", "Jaskinia", "Zwyczaje")
        val myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listOfStories)
        spinner.adapter = myAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                        position: Int, id: Long) {
                when (position) {
                    0 -> { choice = "duch" }
                    1 -> { choice = "jaskinia" }
                    2 -> { choice = "zwyczaje" }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@MainActivity, "Do something", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
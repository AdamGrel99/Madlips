package com.example.madlips

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_story.*

class StoryActivity : AppCompatActivity() {
    lateinit var reader: CharSequence
    var newreader = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        val choice = intent.getStringExtra("choice")
        val answersList = intent.getSerializableExtra("answersList")
        // get choice and array from previous activity
        val arrayOfAnswers = createArrayFromString(answersList.toString())
        if (choice != null) {
            readfile(choice,arrayOfAnswers)
        }
    }

    fun readfile(choice: String,arrayOfAnswers: ArrayList<String>){
        when (choice) {
            "duch" -> { reader = resources.openRawResource(R.raw.duch).bufferedReader().readText() }
            "jaskinia" -> { reader = resources.openRawResource(R.raw.jaskinia).bufferedReader().readText() }
            "zwyczaje" -> { reader = resources.openRawResource(R.raw.zwyczaje).bufferedReader().readText() }
        }
        newreader = reader.toString()
        for (i in 1..10){
            newreader = changeString("__${i}__",arrayOfAnswers[i - 1], newreader)
        }
        story.text = newreader
    }

    fun onClickButton(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun changeString(word: String,answer: String, reader: CharSequence): String {
        return reader.toString().replace(word, answer)
    }

    fun createArrayFromString(story: String): ArrayList<String>{
        var arrayOfWords = arrayListOf<String>()
        var word = ""
        for (i in story.indices){
            if(story[i] == ']'){
                arrayOfWords.add(word)
                word = ""
            }
            if(story[i] !== '['){
                if(story[i] !== ','){
                    word += story[i]
                }else{
                    arrayOfWords.add(word)
                    word = ""
                }
            }
        }
        return arrayOfWords
    }
}
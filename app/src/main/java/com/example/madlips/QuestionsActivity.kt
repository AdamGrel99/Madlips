package com.example.madlips

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_questions.*
import java.util.*

class QuestionsActivity : AppCompatActivity() {
    val questions = ArrayList<String>()
    var questions_left = 0
    val answers = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        readFile()
        firstQuestion()
    }

    fun onClickButton(view: View) {
        if(questions.isNotEmpty()){
            question_textview.text = questions.first()
            questions.removeFirst()
        }
        val answer = answer_editview.text.toString()
        answers.add(answer)
        answer_editview.text.clear()
        questions_left --
        how_many_left.text = "word(s) left : $questions_left"
        if(questions_left == 0){
            val choice = intent.getStringExtra("choice")
            // get choice from previous activity
            val intent = Intent(this, StoryActivity::class.java).apply {
                putExtra("answersList", answers)
                putExtra("choice", choice)
                // put array and choice forward
            }
            startActivity(intent)
        }
    }

    fun readFile(){
        val reader = Scanner(resources.openRawResource(R.raw.pytania))
        while(reader.hasNextLine()){
            val line = reader.nextLine()
            questions.add(line)
        }
    }

    fun firstQuestion(){
        questions_left = questions.size
        how_many_left.text = "word(s) left : $questions_left"
        question_textview.text = "${questions.first()}"
        questions.removeFirst()
    }
}
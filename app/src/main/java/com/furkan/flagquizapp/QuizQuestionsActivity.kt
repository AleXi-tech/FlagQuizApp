package com.furkan.flagquizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.furkan.flagquizapp.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizQuestionsBinding

    private var currentPosition = 1

    private var selectedOption = 0

    private var points = 0

    private var correctAnswer = 0;

    private var questionsList: ArrayList<Question>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name").toString()

        questionsList = Constants.getQuestions()

        onOptionsClick()
        setQuestions(currentPosition)
        onSubmitButtonClick(name)
    }

    private fun onOptionsClick() {
        binding.run {
            tvOptonOne.setOnClickListener {
                setSelectedQuestion(tvOptonOne)
            }
            tvOptonTwo.setOnClickListener {
                setSelectedQuestion(tvOptonTwo)
            }
            tvOptonThree.setOnClickListener {
                setSelectedQuestion(tvOptonThree)
            }
            tvOptonFour.setOnClickListener {
                setSelectedQuestion(tvOptonFour)
            }
        }
    }

    private fun setQuestions(currentPosition: Int): Question {
        val question: Question = questionsList!![currentPosition - 1]
        correctAnswer = question.correctAnswer
        binding.run {
            pb.progress = currentPosition
            pb.max = questionsList!!.size
            tvProgress.text = "$currentPosition/${pb.max}"
            tvQuestion.text = question.question
            tvOptonOne.text = question.optionOne
            tvOptonTwo.text = question.optionTwo
            tvOptonThree.text = question.optionThree
            tvOptonFour.text = question.optionFour
            ivImage.setImageResource(question.image)
        }
        return question
    }

    private fun onSubmitButtonClick(name: String) {
        binding.btnSubmit.setOnClickListener {

            when(binding.btnSubmit.text){
                "SUBMIT" -> {
                    if (correctAnswer == selectedOption) points++
                    setQuestionColorOnSubmitButton()
                    if (currentPosition == questionsList!!.size) binding.btnSubmit.text = "FINISH"
                    else binding.btnSubmit.text = "NEXT QUESTION"
                }
                "NEXT QUESTION", "FINISH" -> {
                    currentPosition++
                    showResultOrNextQuestion(name)
                    binding.btnSubmit.text = "SUBMIT"
                }
            }
        }
    }

    private fun showResult(name: String) {
        Intent(this, ResultActivity::class.java).apply {
            putExtra("result", points.toString())
            putExtra("name", name)
            startActivity(this)
            finish()
        }
    }

    private fun showResultOrNextQuestion(name: String) {
        if (currentPosition == questionsList!!.size + 1) {
            showResult(name)
        } else {
            setSelectedQuestion(null)
            setQuestions(currentPosition)
        }
    }


    private fun setQuestionColorOnSubmitButton() {
        if (selectedOption != 0) {
            val options = arrayOf(
                binding.tvOptonOne, binding.tvOptonTwo,
                binding.tvOptonThree, binding.tvOptonFour
            )
            options[selectedOption - 1].setBackgroundColor(getColor(R.color.red))
            options[correctAnswer - 1].setBackgroundColor(getColor(R.color.green))
        }
    }


    private fun setSelectedQuestion(view: View?) {
        val pickedColor = getColor(R.color.picked)
        val grayColor = getColor(R.color.white)
        binding.tvOptonOne.setBackgroundColor(if (view == binding.tvOptonOne) pickedColor else grayColor)
        binding.tvOptonTwo.setBackgroundColor(if (view == binding.tvOptonTwo) pickedColor else grayColor)
        binding.tvOptonThree.setBackgroundColor(if (view == binding.tvOptonThree) pickedColor else grayColor)
        binding.tvOptonFour.setBackgroundColor(if (view == binding.tvOptonFour) pickedColor else grayColor)
        selectedOption = when (view) {
            binding.tvOptonOne -> 1
            binding.tvOptonTwo -> 2
            binding.tvOptonThree -> 3
            binding.tvOptonFour -> 4
            else -> 0
        }
    }

}
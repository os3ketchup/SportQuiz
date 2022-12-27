package com.bumpy.sport

import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bumpy.sport.Constants.USER_NAME
import com.bumpy.sport.databinding.ActivityQuestionBinding
import com.google.android.material.snackbar.Snackbar

class QuestionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityQuestionBinding

    private var mCurrentPosition = 1

    private var mQuestionList: ArrayList<Question>? = null

    private var mSelectedOptionPosition = 0

    private var mCorrectAnswer = 0

    private var isAnswered = false

    private var doubleBackToExitPressedOnce = false

    private var dialogShowed = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mQuestionList = Constants.getQuestion()
        setQuestion()
        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        val question = mQuestionList!![mCurrentPosition - 1]
        defaultOptionsView()
        if (mCurrentPosition == mQuestionList!!.size) {
            binding.btnSubmit.text = "finish"
        } else {
            binding.btnSubmit.text = "Submit"

        }

        binding.apply {
            progressBar.progress = mCurrentPosition
            tvAmount.text = "$mCurrentPosition" + "/" + "${progressBar.max} "
            tvQuestion.text = question.question
            ivImage.setImageResource(question.image)
            tvOptionOne.text = question.optionOne
            tvOptionTwo.text = question.optionTwo
            tvOptionThree.text = question.optionThree
            tvOptionFour.text = question.optionFour
            toolbar.setNavigationOnClickListener {
                val dialog = Dialog(this@QuestionActivity)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setContentView(R.layout.dialog_finish)
                val tvDialogHeader = dialog.findViewById<TextView>(R.id.tv_dialog_header)
                val tvTotalQuestion = dialog.findViewById<TextView>(R.id.tv_dialog_total_question)
                val tvNo = dialog.findViewById<TextView>(R.id.tv_dialog_quit)
                val tvYes = dialog.findViewById<TextView>(R.id.tv_dialog_restart)
                tvDialogHeader.text = "Hey"
                tvTotalQuestion.text = "Do you really want to exit?"
                tvNo.text = "No"
                tvYes.text = "Yes"
                dialog.setCancelable(false)
                dialog.setCanceledOnTouchOutside(false)
                tvYes.setOnClickListener {
                    startActivity(Intent(this@QuestionActivity,MainActivity::class.java))
                    finish()
                }
                tvNo.setOnClickListener {
                    dialog.cancel()
                }
                dialog.show()
                val window = dialog.window
                window!!.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)

            }
            ivHint.setOnClickListener {
                Snackbar.make(binding.root,question.hint,3000).show()
            }
            btnSubmit.setOnClickListener{
                    isAnswered = !isAnswered
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            showDialog()
                        }
                    }
                } else {
                    val question = mQuestionList!![mCurrentPosition - 1]
                    if (question.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_background)
                    } else {
                        mCorrectAnswer++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_background)

                    if (mCurrentPosition == mQuestionList!!.size){
                        binding.btnSubmit.text = "Finish"
                    }else{
                        binding.btnSubmit.text = "Go to next question"

                    }
                    mSelectedOptionPosition = 0

                }
            }
        }
    }

    private fun defaultOptionsView() {
        isAnswered = false
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)
        options.forEach {
            it.setTextColor(ContextCompat.getColor(this,R.color.military_blue))
            val typeface = ResourcesCompat.getFont(this,R.font.fjala)
            it.typeface = typeface
            it.background = ContextCompat.getDrawable(this, R.drawable.border_background)
        }
    }

    override fun onClick(view: View?) {
        if (!isAnswered){
            when (view?.id) {
                R.id.tv_option_one -> {
                    selectedOptionView(binding.tvOptionOne, 1)

                }
                R.id.tv_option_two -> {
                    selectedOptionView(binding.tvOptionTwo, 2)

                }
                R.id.tv_option_three -> {
                    selectedOptionView(binding.tvOptionThree, 3)

                }
                R.id.tv_option_four -> {
                    selectedOptionView(binding.tvOptionFour, 4)

                }
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun showDialog() {
        dialogShowed = true
        val userName =  intent.getStringExtra(USER_NAME)
        val dialog = Dialog(this)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_result)
        val buttonFinish = dialog.findViewById<Button>(R.id.button_finish)
        val tvUserName = dialog.findViewById<TextView>(R.id.tv_user_name)
        val tvResult = dialog.findViewById<TextView>(R.id.tv_result)
        val ivTrophy = dialog.findViewById<ImageView>(R.id.iv_trophy)
        val tvCongrats = dialog.findViewById<TextView>(R.id.tv_congrats)
        when(mCorrectAnswer){
            in 0..8->{
                ivTrophy.setImageResource(R.drawable.remove)
                tvCongrats.text = "Too low! "
            }
            in 9..10->{
                ivTrophy.setImageResource(R.drawable.trophy)
                tvCongrats.text = "Congratulations! "
            }

        }
        tvResult.text = "Your score is $mCorrectAnswer out of 10"
        tvUserName.text = userName
        buttonFinish.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
        val window = dialog.window
        window!!.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(this, drawableView)

            }
            2 -> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView)

            }
            3 -> {
                binding.tvOptionThree.background = ContextCompat.getDrawable(this, drawableView)

            }
            4 -> {
                binding.tvOptionFour.background = ContextCompat.getDrawable(this, drawableView)

            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNumber


        tv.setTextColor(ContextCompat.getColor(this,R.color.milk))
        val typeface = ResourcesCompat.getFont(this,R.font.fjala)
        tv.typeface = typeface
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_background)
    }

    override fun onBackPressed() {
        if (!dialogShowed){
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }

            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler(Looper.getMainLooper()).postDelayed(
                { doubleBackToExitPressedOnce = false },
                2000
            )
        }


    }
}
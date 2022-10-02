package com.example.stackapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stackapp.databinding.NotationFragmentBinding

class NotationFragment : Fragment(R.layout.notation_fragment) {
    private lateinit var notBinding :NotationFragmentBinding
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notBinding = NotationFragmentBinding.inflate(layoutInflater)
        notBinding.RadioGrp.check(notBinding.infix.id)
        notBinding.expressionInput.hint = "Evaluate Infix Expression!"
        notBinding.expressionInput.text = null
        notBinding.calButton.setOnClickListener {
            try {
                val value = Evaluate.evaluateInfix(notBinding.expressionInput.text.toString()).toString()
                if (value==Evaluate.error.toString()) {
                    notBinding.answerView.text = "Invalid Infix"
                }
                else{
                    notBinding.answerView.text = value
                    notBinding.precedureView.textSize = notBinding.expressionInput.textSize
                    notBinding.precedureView.text = Evaluate.steps.toString()
                }
            }catch (e:Exception){
                notBinding.answerView.text="Invalid Infix"
            }
        }
        notBinding.RadioGrp.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                notBinding.prefix.id -> {
                    notBinding.expressionInput.hint = "Evaluate Prefix Expression!"
                    notBinding.expressionInput.text = null
                    notBinding.calButton.setOnClickListener {
                        try {
                            val value = Evaluate.evaluatePrefix(notBinding.expressionInput.text.toString()).toString()
                            if (value==Evaluate.error.toString()) {
                                notBinding.answerView.text = "Invalid Prefix"
                            }
                            else{
                                notBinding.answerView.text = value
                                notBinding.precedureView.textSize = notBinding.expressionInput.textSize
                                notBinding.precedureView.text = Evaluate.steps.toString()
                            }

                        }catch (e :Exception){
                            notBinding.answerView.text = "Invalid Prefix"
                        }
                    }
                }
                notBinding.postfix.id -> {
                    notBinding.expressionInput.hint = "Evaluate Postfix Expression"
                    notBinding.expressionInput.text = null
                    notBinding.calButton.setOnClickListener {
                        try{
                            val value = Evaluate.evaluatePostfix(notBinding.expressionInput.text.toString()).toString()
                            if (value==Evaluate.error.toString()) {
                                notBinding.answerView.text = "Invalid Postfix"
                            }
                            else{
                                notBinding.answerView.text = value
                                notBinding.precedureView.textSize = notBinding.expressionInput.textSize
                                notBinding.precedureView.text = Evaluate.steps.toString()
                            }
                        }catch (e:Exception){
                            notBinding.answerView.text="Invalid Postfix"
                        }
                    }
                }
                notBinding.infix.id ->{
                    notBinding.expressionInput.hint = "Evaluate Infix Expression!"
                    notBinding.expressionInput.text = null
                    notBinding.calButton.setOnClickListener {
                        try {
                            val value = Evaluate.evaluateInfix(notBinding.expressionInput.text.toString()).toString()
                            if (value==Evaluate.error.toString()) {
                                notBinding.answerView.text = "Invalid Infix"
                            }
                            else{
                                notBinding.answerView.text = value
                                notBinding.precedureView.textSize = notBinding.expressionInput.textSize
                                notBinding.precedureView.text = Evaluate.steps.toString()
                            }
                        }catch (e:Exception){
                        notBinding.answerView.text="Invalid Infix"
                        }
                    }
                }
            }
        }

        return notBinding.root
    }
}
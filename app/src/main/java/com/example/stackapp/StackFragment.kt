package com.example.stackapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.stackapp.databinding.AboutAppBinding
import com.example.stackapp.databinding.StackFragmentBinding

class StackFragment : Fragment(R.layout.stack_fragment) {
    private fun View.hideKey(){
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken,0)
    }
    private lateinit var staBinding : StackFragmentBinding
    private lateinit var aboutBinding : AboutAppBinding
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        staBinding = StackFragmentBinding.inflate(layoutInflater)
        setStack("5")
        var lenVal = "5"
        staBinding.setButton.setOnClickListener {
            try {
                lenVal = staBinding.lengthInput.text.toString()
                setStack(lenVal)
            }catch (e :Exception){
                setStack(lenVal)
            }
        }
        staBinding.help.setOnClickListener{
            Toast.makeText(activity, "Provide Length Of Stack from 1 to 6", Toast.LENGTH_SHORT).show()
        }
        val stack= ArrayDeque<String>()
        staBinding.pushButton.setOnClickListener{
            try {
                val value = staBinding.valueInput.text.toString()
                if (stack.size == staBinding.lengthInput.text.toString().toInt()) {
                    Toast.makeText(activity, "Stack Is Full", Toast.LENGTH_SHORT).show()
                } else if("" !in value || " " !in value) {
                    stack.add(value)
                    staBinding.valueInput.text = null
                    when (lenVal) {
                        "1" -> if (stack.size == 1) staBinding.stackValue.text = stack[0]
                        "2" -> {
                            if (stack.size == 1) staBinding.stackValue.text = stack[0]
                            else if (stack.size == 2) staBinding.stackValue4.text = stack[1]
                        }
                        "3" -> {
                            when (stack.size) {
                                1 -> staBinding.stackValue3.text = stack[0]
                                2 -> staBinding.stackValue.text = stack[1]
                                3 -> staBinding.stackValue4.text = stack[2]
                            }
                        }
                        "4" -> {
                            when (stack.size) {
                                1 -> staBinding.stackValue3.text = stack[0]
                                2 -> staBinding.stackValue.text = stack[1]
                                3 -> staBinding.stackValue4.text = stack[2]
                                4 -> staBinding.stackValue5.text = stack[3]
                            }
                        }
                        "5" -> { //1/23045
                            when (stack.size) {
                                1 -> staBinding.stackValue2.text = stack[0]
                                2 -> staBinding.stackValue3.text = stack[1]
                                3 -> staBinding.stackValue.text = stack[2]
                                4 -> staBinding.stackValue4.text = stack[3]
                                5 -> staBinding.stackValue5.text = stack[4]
                            }
                        }
                        "6" -> {
                            when (stack.size) {
                                1 -> staBinding.stackValue1.text = stack[0]
                                2 -> staBinding.stackValue2.text = stack[1]
                                3 -> staBinding.stackValue3.text = stack[2]
                                4 -> staBinding.stackValue.text = stack[3]
                                5 -> staBinding.stackValue4.text = stack[4]
                                6 -> staBinding.stackValue5.text = stack[5]
                            }
                        }
                    }
                }
            }catch (e:Exception){
                Toast.makeText(activity, "Please Provide a Valid Value !!", Toast.LENGTH_SHORT).show()
            }
            view?.hideKey()

        }
        staBinding.popButton.setOnClickListener {
            staBinding.poppedValue.text = "The value popped is : "
            if (!stack.isEmpty()) {
                // empty stack
                when(lenVal){
                    "1" -> if (stack.size==1) staBinding.stackValue.text = null
                    "2" ->{ if (stack.size==1)staBinding.stackValue.text = null
                    else if (stack.size==2) staBinding.stackValue4.text = null
                    }
                    "3"->{
                        when (stack.size) {
                            1 -> staBinding.stackValue3.text = null
                            2 -> staBinding.stackValue.text  = null
                            3 -> staBinding.stackValue4.text = null
                        }
                    }
                    "4"-> {
                        when (stack.size) {
                            1 -> staBinding.stackValue3.text = null
                            2 -> staBinding.stackValue.text  = null
                            3 -> staBinding.stackValue4.text = null
                            4 -> staBinding.stackValue5.text = null
                        }
                    }
                    "5"->{ //1/23045
                        when (stack.size) {
                            1 -> staBinding.stackValue2.text = null
                            2 -> staBinding.stackValue3.text = null
                            3 -> staBinding.stackValue.text  = null
                            4 -> staBinding.stackValue4.text = null
                            5 -> staBinding.stackValue5.text = null
                        }
                    }
                    "6"->{
                        when (stack.size) {
                            1 -> staBinding.stackValue1.text = null
                            2 -> staBinding.stackValue2.text = null
                            3 -> staBinding.stackValue3.text = null
                            4 -> staBinding.stackValue.text  = null
                            5 -> staBinding.stackValue4.text = null
                            6 -> staBinding.stackValue5.text = null
                        }
                    }
                }
                val popped = stack.removeLast()
                staBinding.poppedValueFiled.text = popped
            }
            else{
                Toast.makeText(activity, "Stack Empty", Toast.LENGTH_SHORT).show()
            }
        }
        staBinding.peekButton.setOnClickListener {
            if(!stack.isEmpty()) {
                val peeked = stack.last()
                staBinding.poppedValueFiled.text = peeked
                staBinding.poppedValue.text = "The last value in inserted: "
            }
            else Toast.makeText(activity, "The Stack is empty!", Toast.LENGTH_SHORT).show()
        }
        staBinding.aboutApp.setOnClickListener{
           val dialogBinding = layoutInflater.inflate(R.layout.about_app,null)
            val myDialog = Dialog(requireContext())
            myDialog.setContentView(dialogBinding)
            myDialog.show()
        }
        return staBinding.root
    }
        private fun setStack(lenVal : String){
            when (lenVal) {
                "1" -> {
                    staBinding.stackValue.setBackgroundResource(R.drawable.one_stack)
                    staBinding.stackValue6.setBackgroundResource(R.drawable.unchecked_stack)
                    staBinding.stackValue5.setBackgroundResource(R.drawable.unchecked_stack)
                    staBinding.stackValue4.setBackgroundResource(R.drawable.unchecked_stack)
                    staBinding.stackValue3.setBackgroundResource(R.drawable.unchecked_stack)
                    staBinding.stackValue2.setBackgroundResource(R.drawable.unchecked_stack)
                    staBinding.stackValue1.setBackgroundResource(R.drawable.unchecked_stack)
                }
                "2" -> {
                    staBinding.stackValue4.setBackgroundResource(R.drawable.main_stack_round) // 0 4
                    staBinding.stackValue.setBackgroundResource(R.drawable.main_stack_round_down)
                    staBinding.stackValue6.setBackgroundResource(R.drawable.unchecked_stack)
                    staBinding.stackValue5.setBackgroundResource(R.drawable.unchecked_stack)
                    staBinding.stackValue3.setBackgroundResource(R.drawable.unchecked_stack)
                    staBinding.stackValue2.setBackgroundResource(R.drawable.unchecked_stack)
                    staBinding.stackValue1.setBackgroundResource(R.drawable.unchecked_stack)

                }
                "3" ->{
                    staBinding.stackValue4.setBackgroundResource(R.drawable.main_stack_round) // 3 0 4
                    staBinding.stackValue.setBackgroundResource(R.drawable.main_stack)
                    staBinding.stackValue3.setBackgroundResource(R.drawable.main_stack_round_down)
                    staBinding.stackValue6.setBackgroundResource(R.drawable.unchecked_stack)
                    staBinding.stackValue5.setBackgroundResource(R.drawable.unchecked_stack)
                    staBinding.stackValue2.setBackgroundResource(R.drawable.unchecked_stack)
                    staBinding.stackValue1.setBackgroundResource(R.drawable.unchecked_stack)


                }
                "4" -> {
                    staBinding.stackValue5.setBackgroundResource(R.drawable.main_stack_round) // 3 0 4 5
                    staBinding.stackValue4.setBackgroundResource(R.drawable.main_stack)
                    staBinding.stackValue.setBackgroundResource(R.drawable.main_stack)
                    staBinding.stackValue3.setBackgroundResource(R.drawable.main_stack_round_down)
                    staBinding.stackValue2.setBackgroundResource(R.drawable.unchecked_stack)
                    staBinding.stackValue1.setBackgroundResource(R.drawable.unchecked_stack)
                    staBinding.stackValue6.setBackgroundResource(R.drawable.unchecked_stack)

                }
                "5" ->{
                    staBinding.stackValue5.setBackgroundResource(R.drawable.main_stack_round)// 2 3 0 4 5
                    staBinding.stackValue4.setBackgroundResource(R.drawable.main_stack)
                    staBinding.stackValue.setBackgroundResource(R.drawable.main_stack)
                    staBinding.stackValue3.setBackgroundResource(R.drawable.main_stack)
                    staBinding.stackValue2.setBackgroundResource(R.drawable.main_stack_round_down)
                    staBinding.stackValue1.setBackgroundResource(R.drawable.unchecked_stack)
                    staBinding.stackValue6.setBackgroundResource(R.drawable.unchecked_stack)

                }
                "6" -> {
                    staBinding.stackValue5.setBackgroundResource(R.drawable.main_stack_round) // 1 2 3 0 4 5
                    staBinding.stackValue4.setBackgroundResource(R.drawable.main_stack)
                    staBinding.stackValue.setBackgroundResource(R.drawable.main_stack)
                    staBinding.stackValue3.setBackgroundResource(R.drawable.main_stack)
                    staBinding.stackValue2.setBackgroundResource(R.drawable.main_stack)
                    staBinding.stackValue1.setBackgroundResource(R.drawable.main_stack_round_down)
                    staBinding.stackValue6.setBackgroundResource(R.drawable.unchecked_stack)

                }

                else -> {
                    Toast.makeText(activity, "Length Of Stack should be more than 0 less than 7!", Toast.LENGTH_SHORT).show()
                    staBinding.lengthInput.text = null
                }
            }
            view?.hideKey()
        }
}


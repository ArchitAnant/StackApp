package com.example.stackapp

import java.lang.UnsupportedOperationException
import java.util.*

object Evaluate {
    val error = -1.9337150011
    val steps = StringBuilder()
    private fun isOperand(c: Char): Boolean {
        // If the character is a digit
        // then it must be an operand
        return c.code in 48..57
    }
    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        if (op2 == '(' || op2 == ')') return false
        return !((op1 == '*' || op1 == '/') &&
                (op2 == '+' || op2 == '-'))
    }
    private fun applyOp(op: Char, b: Double, a: Double): Double {
        when (op) {
            '+' -> {
                steps.append("$a+$b\n")
                return a + b
            }
            '-' ->{
                steps.append("$a-$b\n")
                return a - b
            }
            '*' ->{
                steps.append("$a*$b\n")
                return a * b
            }
            '/' -> {
                if (b == 0.0) throw UnsupportedOperationException(
                    "Cannot divide by zero"
                )
                steps.append("$a/$b\n")
                return a / b
            }
        }
        return 0.0
    }


    fun evaluateInfix(expression: String): Double {
        steps.clear()
        val tokens = expression.toCharArray()

        // Stack for numbers: 'values'
        val values = Stack<Double>()

        // Stack for Operators: 'ops'
        val ops = Stack<Char>()
        var i = 0
        while (i < tokens.size) {


            // Current token is a
            // whitespace, skip it
            if (tokens[i] == ' ') {
                i++
                continue
            }

            // Current token is a number,
            // push it to stack for numbers
            try{
            if (tokens[i] in '0'..'9'
            ) {
                val sbuf = StringBuffer()

                // There may be more than one
                // digits in number
                while (i < tokens.size && tokens[i] >= '0' && tokens[i] <= '9') sbuf.append(tokens[i++])
                values.push(sbuf.toString().toDouble())
                i--
            } else if (tokens[i] == '(') ops.push(tokens[i]) else if (tokens[i] == ')') {
                while (ops.peek() != '(') values.push(
                    applyOp(
                        ops.pop(),
                        values.pop(),
                        values.pop()
                    )
                )
                ops.pop()
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!ops.empty() &&
                    hasPrecedence(
                        tokens[i],
                        ops.peek()
                    )
                ) values.push(
                    applyOp(
                        ops.pop(),
                        values.pop(),
                        values.pop()
                    )
                )

                // Push current token to 'ops'.
                ops.push(tokens[i])
            }
            }catch (e:EmptyStackException){
                return -1.9337150011
            }
            i++
        }
        while (!ops.empty()) values.push(
            applyOp(
                ops.pop(),
                values.pop(),
                values.pop()
            )
        )

        // Top of 'values' contains
        // result, return it
        return if(!values.empty()) values.peek()
        else -1.9337150011
    }
    fun evaluatePrefix(exp: String): Double {
        steps.clear()
        val stack = Stack<Double>()
        for (j in exp.length - 1 downTo 0) {

            if (isOperand(exp[j])) stack.push((exp[j].code - 48).toDouble())
            else if(!stack.empty()) {

                // Operator encountered
                // Pop two elements from Stack
                    val o1 = stack.peek()
                    stack.pop()
                    val o2 = stack.peek()
                    stack.pop()
                    when (exp[j]) {
                        '+' -> {
                            steps.append("$o1+$o2\n")
                            stack.push(o1 + o2)
                        }
                        '-' ->{
                            steps.append("$o1-$o2\n")
                            stack.push(o1 - o2)
                        }
                        '*' ->{
                            steps.append("$o1*$o2\n")
                            stack.push(o1 * o2)
                        }
                        '/' -> {
                            steps.append("$o1/$o2\n")
                            stack.push(o1 / o2)
                        }
                    }
            }
        }
        return if(!stack.empty()) stack.peek()
        else -1.9337150011


    }
    fun evaluatePostfix(exp: String): Double {
        steps.clear()
        //create a stack
        val stack = Stack<Double>()

        // Scan all characters one by one
        for (element in exp) {

            // If the scanned character is an operand (number here),
            // push it to the stack.
            if (Character.isDigit(element)) stack.push((element - '0').toDouble()) else if(!stack.isEmpty()){
                val val1 = stack.pop()
                val val2 = stack.pop()
                when (element) {
                    '+' -> {
                        steps.append("$val2+$val1\n")
                        stack.push(val2 + val1)
                    }
                    '-' ->{
                        steps.append("$val2-$val1\n")
                        stack.push(val2 - val1)
                    }
                    '/' -> {
                        steps.append("$val2/$val1\n")
                        stack.push(val2 / val1)
                    }
                    '*' -> {
                        steps.append("$val2*$val1\n")
                        stack.push(val2 * val1)
                    }
                }
            }
        }
        return if(!stack.empty()) stack.pop()
        else -1.9337150011
    }
}
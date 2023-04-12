package com.example.actividad01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private val expressionTextView: TextView by lazy {
        findViewById<TextView>(R.id.txt_expression)
    }
    private val resultTextView: TextView by lazy {
        findViewById<TextView>(R.id.txt_result)
    }

    private var isOperator = false
    private var hasOneOperator = false

    fun clickButton(v: View) {
        when (v.id) {
            R.id.btn_0 -> numericButton("0")
            R.id.btn_1 -> numericButton("1")
            R.id.btn_2 -> numericButton("2")
            R.id.btn_3 -> numericButton("3")
            R.id.btn_4 -> numericButton("4")
            R.id.btn_5 -> numericButton("5")
            R.id.btn_6 -> numericButton("6")
            R.id.btn_7 -> numericButton("7")
            R.id.btn_8 -> numericButton("8")
            R.id.btn_9 -> numericButton("9")

            R.id.btn_plus -> operatorButton("+")
            R.id.btn_minus -> operatorButton("-")
            R.id.btn_multi -> operatorButton("X")
            R.id.btn_div -> operatorButton("/")
        }
    }


    private fun numericButton(number: String) {
        if (isOperator) {
            expressionTextView.append(" ")
        }
        isOperator = false
        expressionTextView.append(number)
        resultTextView.text = calculateExpression()
    }

    private fun operatorButton(operator: String) {
        if (expressionTextView.text.isEmpty()) {
            return
        }

        when {
            isOperator -> {
                val text = expressionTextView.text.toString()
                expressionTextView.text = text.dropLast(1) + operator
            }
            hasOneOperator -> {
                return
            }
            else -> {
                expressionTextView.append(" $operator")
            }

        }
        val ssb = SpannableStringBuilder(expressionTextView.text)

        expressionTextView.text = ssb
        isOperator = true
        hasOneOperator = true
    }

    fun clickResult(v: View) {
        val expressionTexts = expressionTextView.text.split(" ")
        if (expressionTextView.text.isEmpty() || expressionTexts.size == 1) {
            return
        }
        if (expressionTexts.size != 3 && hasOneOperator) {
            return
        }
        if (expressionTexts[0].isNumber().not() || expressionTexts[2].isNumber().not()) {
            return
        }
        val resultText = calculateExpression()
        resultTextView.text = ""
        expressionTextView.text = resultText
        isOperator = false
        hasOneOperator = false
        openResult(resultText)
    }


    private fun calculateExpression(): String {
        val expressionTexts = expressionTextView.text.split(" ")
        if (hasOneOperator.not() || expressionTexts.size != 3) {
            return ""
        } else if (expressionTexts[0].isNumber().not() || expressionTexts[2].isNumber().not()) {
            return ""
        }
        val exp1 = expressionTexts[0].toBigInteger()
        val exp2 = expressionTexts[2].toBigInteger()
        val op = expressionTexts[1]
        return when (op) {
            "+" -> (exp1 + exp2).toString()
            "-" -> (exp1 - exp2).toString()
            "X" -> (exp1 * exp2).toString()
            "%" -> (exp1 % exp2).toString()
            "/" -> (exp1 / exp2).toString()
            else -> ""
        }
    }

    fun clickDot(v: View) {
        if (hasOneOperator != true) {
            if (expressionTextView.text.length == 0) {
                expressionTextView.append("0.")
            } else if (!expressionTextView.text.toString().contains(".")) {
                expressionTextView.append(".")
            }
        } else {
            if (resultTextView.text.length == 0) {
                resultTextView.append("0.")
            } else if (resultTextView.text.length != 0 && resultTextView.text.toString()
                    .contains(".")
            ) {
                resultTextView.append(".")
            }
        }

    }

    fun String.isNumber(): Boolean {
        return try {
            this.toBigInteger()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

    fun openResult(value: String) {
        val intent = Intent(this, Results::class.java)
        intent.putExtra("result", value)
        startActivity(intent)
    }

}
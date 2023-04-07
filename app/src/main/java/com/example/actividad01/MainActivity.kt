package com.example.actividad01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(Color.parseColor("#FF018786"))
        )

        plus.setOnClickListener {
            add()
        }

        minus.setOnClickListener {
            subtract()
        }

        multiply.setOnClickListener {
            multiply()
        }

        divide.setOnClickListener {
            divide()
        }

    }

    fun inputIsNotEmpty(): Boolean {
        var b = true;
        if (input1.text.toString().trim().isEmpty()) {
            input1.error = "Required"
            input1.requestFocus()
            b = false;
        }
        if (input2.text.toString().trim().isEmpty()) {
            input2.error = "Required"
            input2.requestFocus()
            b = false;
        }
        return b;
    }

    fun add() {
        if (inputIsNotEmpty()) {
            var inputdata1 = input1.text.toString().trim().toBigDecimal()
            var inputdata2 = input2.text.toString().trim().toBigDecimal()
            openResult(inputdata1.add(inputdata2).toString())
        }
    }

    fun subtract() {
        if (inputIsNotEmpty()) {
            var inputdata1 = input1.text.toString().trim().toBigDecimal()
            var inputdata2 = input2.text.toString().trim().toBigDecimal()
            openResult(inputdata1.subtract(inputdata2).toString())
        }
    }

    fun multiply() {
        if (inputIsNotEmpty()) {
            var inputdata1 = input1.text.toString().trim().toBigDecimal()
            var inputdata2 = input2.text.toString().trim().toBigDecimal()
            openResult(inputdata1.multiply(inputdata2).toString())
        }
    }


    fun divide() {
        if (inputIsNotEmpty()) {
            var inputdata1 = input1.text.toString().trim().toBigDecimal()
            var inputdata2 = input2.text.toString().trim().toBigDecimal()

            if (inputdata2.compareTo(BigDecimal.ZERO) == 0) {
                input2.error = "invalid input"
            } else {
                openResult(inputdata1.divide(inputdata2, 2, RoundingMode.HALF_UP).toString())
            }
        }
    }

    fun openResult(value: String){
        val intent = Intent(this, Results::class.java)
        intent.putExtra("result", value)
        startActivity(intent)
    }
}
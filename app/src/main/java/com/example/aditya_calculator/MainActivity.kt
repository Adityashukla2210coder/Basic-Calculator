package com.example.aditya_calculator

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set Action Bar title
        supportActionBar?.title = "Simple Calculator"

        val num1EditText = findViewById<EditText>(R.id.ed1)
        val num2EditText = findViewById<EditText>(R.id.ed2)
        val answerTextView = findViewById<TextView>(R.id.ans)

        val addButton = findViewById<Button>(R.id.b1)
        val subtractButton = findViewById<Button>(R.id.b2)
        val multiplyButton = findViewById<Button>(R.id.b3)
        val divideButton = findViewById<Button>(R.id.b4)
        val fullCalcButton = findViewById<Button>(R.id.fullcalc)

        fun getNumbers(): Pair<Double, Double>? {
            val num1Text = num1EditText.text.toString()
            val num2Text = num2EditText.text.toString()

            if (num1Text.isEmpty() || num2Text.isEmpty()) {
                Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show()
                return null
            }

            return Pair(num1Text.toDouble(), num2Text.toDouble())
        }

        addButton.setOnClickListener {
            val numbers = getNumbers()
            if (numbers != null) {
                val result = numbers.first + numbers.second
                answerTextView.text = result.toString()
            }
        }

        subtractButton.setOnClickListener {
            val numbers = getNumbers()
            if (numbers != null) {
                val result = numbers.first - numbers.second
                answerTextView.text = result.toString()
            }
        }

        multiplyButton.setOnClickListener {
            val numbers = getNumbers()
            if (numbers != null) {
                val result = numbers.first * numbers.second
                answerTextView.text = result.toString()
            }
        }

        divideButton.setOnClickListener {
            val numbers = getNumbers()
            if (numbers != null) {
                if (numbers.second == 0.0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                } else {
                    val result = numbers.first / numbers.second
                    answerTextView.text = result.toString()
                }
            }
        }

        fullCalcButton.setOnClickListener {
            val intent = Intent(this, FullCalculatorActivity::class.java)
            startActivity(intent)

        }
    }
}

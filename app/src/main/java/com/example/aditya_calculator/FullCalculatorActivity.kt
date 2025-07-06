package com.example.aditya_calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FullCalculatorActivity : AppCompatActivity() {

    private var input = ""
    private var operator = ""
    private var operand1 = ""
    private var operand2 = ""

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_calculator)

        supportActionBar?.title = "Full Calculator"

        // Enable the back button in the Action Bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val resultTextView = findViewById<TextView>(R.id.textView3)
        val intermediateTextView = findViewById<TextView>(R.id.textView5)

        val buttonIds = listOf(
            R.id.bt0, R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4,
            R.id.bt5, R.id.bt6, R.id.bt7, R.id.bt8, R.id.bt9
        )

        val buttons = buttonIds.map { findViewById<Button>(it) }

        buttons.forEach { button ->
            button.setOnClickListener {
                input += button.text
                intermediateTextView.text = input
            }
        }

        findViewById<Button>(R.id.btadd).setOnClickListener { onOperatorPressed("+", resultTextView, intermediateTextView) }
        findViewById<Button>(R.id.btsub).setOnClickListener { onOperatorPressed("-", resultTextView, intermediateTextView) }
        findViewById<Button>(R.id.btmul).setOnClickListener { onOperatorPressed("*", resultTextView, intermediateTextView) }
        findViewById<Button>(R.id.btdiv).setOnClickListener { onOperatorPressed("/", resultTextView, intermediateTextView) }

        findViewById<Button>(R.id.btequal).setOnClickListener {
            operand2 = input
            val result = performOperation()
            resultTextView.text = result.toString()
            intermediateTextView.text = ""
            resetCalculator()
        }

        findViewById<Button>(R.id.ACbtn).setOnClickListener {
            resetCalculator()
            resultTextView.text = "0.0"
            intermediateTextView.text = ""
        }

        findViewById<Button>(R.id.btdot).setOnClickListener {
            if (!input.contains(".")) {
                input += "."
                intermediateTextView.text = input
            }
        }
    }

    private fun onOperatorPressed(op: String, resultTextView: TextView, intermediateTextView: TextView) {
        if (input.isNotEmpty()) {
            operand1 = input
            operator = op
            input = ""
            intermediateTextView.text = "$operand1 $operator"
        } else {
            resultTextView.text = "Enter a number first"
        }
    }

    private fun performOperation(): Double {
        val num1 = operand1.toDoubleOrNull() ?: 0.0
        val num2 = operand2.toDoubleOrNull() ?: 0.0

        return when (operator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> if (num2 != 0.0) num1 / num2 else Double.NaN
            else -> 0.0
        }
    }

    private fun resetCalculator() {
        input = ""
        operator = ""
        operand1 = ""
        operand2 = ""
    }
}

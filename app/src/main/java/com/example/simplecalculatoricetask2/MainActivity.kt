package com.example.simplecalculatoricetask2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val numOne = findViewById<EditText>(R.id.numOne)
        val numTwo = findViewById<EditText>(R.id.numTwo)
        val btnSub = findViewById<Button>(R.id.btnSub)
        val btnDiv = findViewById<Button>(R.id.btnDiv)
        val btnMul = findViewById<Button>(R.id.btnMul)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val outAns = findViewById<TextView>(R.id.outAns)

        btnAdd.setOnClickListener { performOperation(Operation.ADD, numOne, numTwo, outAns) }
        btnSub.setOnClickListener { performOperation(Operation.SUBTRACT, numOne, numTwo, outAns) }
        btnMul.setOnClickListener { performOperation(Operation.MULTIPLY, numOne, numTwo, outAns) }
        btnDiv.setOnClickListener { performOperation(Operation.DIVIDE, numOne, numTwo, outAns) }
    }

    private fun performOperation(operation: Operation, numOne: EditText, numTwo: EditText, outAns: TextView) {
        val number1 = numOne.text.toString().trim()
        val number2 = numTwo.text.toString().trim()

        // Check if inputs are valid numbers
        if (number1.isEmpty() || number2.isEmpty()) {
            outAns.text = "Please enter both numbers"
            return
        }

        val num1 = number1.toDoubleOrNull()
        val num2 = number2.toDoubleOrNull()

        if (num1 == null || num2 == null) {
            outAns.text = "Please enter valid numbers"
            return
        }

        val result = when (operation) {
            Operation.ADD -> num1 + num2
            Operation.SUBTRACT -> num1 - num2
            Operation.MULTIPLY -> num1 * num2
            Operation.DIVIDE -> {
                if (num2 == 0.0) {
                    outAns.text = "Cannot divide by zero"
                    return
                } else {
                    num1 / num2
                }
            }
        }

        outAns.text = result.toString()
    }

    private enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}

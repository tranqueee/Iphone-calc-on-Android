package com.example.iphonecalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.ArrayList
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    var operand : String = ""
    var buttons : ArrayList<Button> = ArrayList()
    var operands : ArrayList<Button> = ArrayList()
    var firstNum : Double = 0.0
    var secondNum : Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonAC : Button = findViewById(R.id.buttonAC)
        val numField : TextView = findViewById(R.id.fieldOfNums)

        val btn1 : Button = findViewById(R.id.btn1)
        val btn2 : Button = findViewById(R.id.btn2)
        val btn3 : Button = findViewById(R.id.btn3)
        val btn4 : Button = findViewById(R.id.btn4)
        val btn5 : Button = findViewById(R.id.btn5)
        val btn6 : Button = findViewById(R.id.btn6)
        val btn7 : Button = findViewById(R.id.btn7)
        val btn8 : Button = findViewById(R.id.btn8)
        val btn9 : Button = findViewById(R.id.btn9)
        val btn0 : Button = findViewById(R.id.btn0)

        val btnPlus : Button = findViewById(R.id.btnPlus)
        val btnMinus : Button = findViewById(R.id.btnMin)
        val btnDiv : Button = findViewById(R.id.btnDiv)
        val btnMul : Button = findViewById(R.id.btnMultiply)
        val percentBtn : Button = findViewById(R.id.btnPerc)
        val equalsBtn : Button = findViewById(R.id.btnEquals)
        val dotBtn : Button = findViewById(R.id.btnDot)
        val plusMinBtn : Button = findViewById(R.id.btnPotential)

        buttonAC.setOnClickListener {
            numField.setText("0")
            operand = ""
        }

        percentBtn.setOnClickListener {
            val result : Double = (numField.text.toString().toDouble()) / 100
            numField.setText(result.toString())
        }

        dotBtn.setOnClickListener {
            val strNum : String = numField.text.toString()
            if (strNum.last() != '.' && havntDot(strNum)) {
                numField.setText(numField.text.toString() + ".")
            }
        }

        plusMinBtn.setOnClickListener {
            var strNum : String = numField.text.toString()
            if (strNum.first() != '-') {
                numField.setText("-" + numField.text.toString())
            }
            if (strNum.first() == '-') {
                strNum = strNum.replace("-", "")
                numField.setText(strNum)
            }
        }

        buttons.add(btn1)
        buttons.add(btn2)
        buttons.add(btn3)
        buttons.add(btn4)
        buttons.add(btn5)
        buttons.add(btn6)
        buttons.add(btn7)
        buttons.add(btn8)
        buttons.add(btn9)
        buttons.add(btn0)

        for (i in 0..9){
            val currentBtn : Button = buttons.get(i)
            var currentText : String
            currentBtn.setOnClickListener {

                if (numField.text.toString() == "0") {
                    numField.setText(currentBtn.text)
                } else {
                    currentText = (numField.text.toString()) + (currentBtn.text.toString())
                    numField.setText(currentText)
                }
            }
        }

        operands.add(btnPlus)
        operands.add(btnMinus)
        operands.add(btnDiv)
        operands.add(btnMul)

        for (i in 0..3) {
            val currentOperand : Button = operands.get(i)
            currentOperand.setOnClickListener {
                if (operand == "") {
                    firstNum = (numField.text.toString()).toDouble()
                    numField.setText("0")
                }
                operand = currentOperand.text.toString()
            }
        }

        var result : String = ""
        equalsBtn.setOnClickListener {
            secondNum = (numField.text.toString()).toDouble()
            if (operand != "") {
                when (operand) {
                    "+" -> result = (firstNum + secondNum).toString()
                    "-" -> result = (firstNum - secondNum).toString()
                    "/" -> result = (firstNum / secondNum).toString()
                    "*" -> result = (firstNum * secondNum).toString()
                }

                if (result.endsWith(".0")) {
                    result = result.replace(".0", "")
                }

                numField.setText(result)
                operand = ""
                firstNum = 0.0
                secondNum = 0.0
            }
        }

        numField.setOnClickListener {
            var numStr : String = numField.text.toString()
            numStr = numStr.replace(numStr.last().toString(), "")
            numField.setText(numStr)
        }
    }

    fun havntDot (str : String) : Boolean {
        for (let in str) {
            if (let == '.') {
                return false
            }
        }
        return true
    }
}
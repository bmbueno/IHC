package com.example.sum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var _num1 = findViewById<EditText>(R.id.valor1)
        var _num2 = findViewById<EditText>(R.id.valor2)

        btnSoma.setOnClickListener(){
            var num1 = _num1.text.toString().toInt()
            var num2 = _num2.text.toString().toInt()
            valorResultado.text = "$num1 + $num2 = " + (num1 + num2).toString()

        }
    }
}

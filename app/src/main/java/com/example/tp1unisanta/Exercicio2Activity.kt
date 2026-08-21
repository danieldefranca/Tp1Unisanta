package com.example.tp1unisanta

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Exercicio2Activity : AppCompatActivity() {

    private fun esconderTeclado() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = currentFocus ?: View(this)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercicio2)

        val edCelsius = findViewById<EditText>(R.id.editTextCelsius)
        val btnConverter = findViewById<Button>(R.id.btnConverte)
        val txResultado = findViewById<TextView>(R.id.textFahrenheit)
        val btnProximo2 = findViewById<Button>(R.id.btnProximo2)

        btnProximo2.visibility = View.INVISIBLE

        btnConverter.setOnClickListener {
            esconderTeclado()
            val celsiusStr = edCelsius.text.toString()

            if (celsiusStr.isBlank()) {
                txResultado.text = "Digite um valor em Celsius."
                return@setOnClickListener
            }

            val celsius = celsiusStr.toDouble()
            val fahrenheit = celsius * 9 / 5 + 32

            txResultado.text = "$celsius°C equivale a $fahrenheit°F."

            edCelsius.text.clear()

            btnProximo2.visibility = View.VISIBLE
        }

        btnProximo2.setOnClickListener {
            val intent = Intent(this, Exercicio3Activity::class.java)
            startActivity(intent)
        }

    }
}
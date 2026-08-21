package com.example.tp1unisanta

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edValor1 = findViewById<EditText>(R.id.editTextNome)
        val edValor2 = findViewById<EditText>(R.id.editTextIdade)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)
        val btnProximo = findViewById<Button>(R.id.btnProximo)
        val txResultado = findViewById<TextView>(R.id.textResposta)

        btnProximo.visibility = View.INVISIBLE

        btnEnviar.setOnClickListener {
            val valor1 = edValor1.text.toString()
            val valor2 = edValor2.text.toString()
            txResultado.setText("Bem vindo, $valor1! Você tem $valor2 anos de idade.")
            edValor1.text.clear()
            edValor2.text.clear()

            btnProximo.visibility = View.VISIBLE


        }

        btnProximo.setOnClickListener {
            val intent = Intent(this, Exercicio2Activity::class.java)
            startActivity(intent)
        }

    }
}
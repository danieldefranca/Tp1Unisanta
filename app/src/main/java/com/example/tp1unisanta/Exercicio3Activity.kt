package com.example.tp1unisanta

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.view.inputmethod.InputMethodManager

class Exercicio3Activity : AppCompatActivity() {

    private fun esconderTeclado() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = currentFocus ?: View(this)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercicio3)

        val edTensao = findViewById<EditText>(R.id.editTextTensao)
        val edResistencia = findViewById<EditText>(R.id.editTextResistencia)
        val edCorrente = findViewById<EditText>(R.id.editTextCorrente)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val txResultado = findViewById<TextView>(R.id.textResultadoOhm)

        btnCalcular.setOnClickListener {
            esconderTeclado()
            val tensaoStr = edTensao.text.toString()
            val resistenciaStr = edResistencia.text.toString()
            val correnteStr = edCorrente.text.toString()

            val camposPreenchidos = listOf(tensaoStr, resistenciaStr, correnteStr)
                .count { it.isNotBlank() }

            if (camposPreenchidos != 2) {
                txResultado.text = "Preencha exatamente 2 dos 3 campos."
                return@setOnClickListener
            }

            when {
                // tá faltando a CORRENTE
                tensaoStr.isNotBlank() && resistenciaStr.isNotBlank() && correnteStr.isBlank() -> {
                    val v = tensaoStr.toDouble()
                    val r = resistenciaStr.toDouble()

                    if (r == 0.0) {
                        txResultado.text = "Resistência não pode ser zero."
                        return@setOnClickListener
                    }

                    val i = v / r
                    txResultado.text = "Corrente calculada: %.2f A".format(i)
                    edCorrente.setText("%.2f".format(i))

                    edTensao.text.clear()
                    edCorrente.text.clear()
                    edResistencia.text.clear()
                }

                // tá faltando a RESISTÊNCIA
                tensaoStr.isNotBlank() && correnteStr.isNotBlank() && resistenciaStr.isBlank() -> {
                    val v = tensaoStr.toDouble()
                    val i = correnteStr.toDouble()

                    if (i == 0.0) {
                        txResultado.text = "Corrente não pode ser zero."
                        return@setOnClickListener
                    }

                    val r = v / i
                    txResultado.text = "Resistência calculada: %.2f Ω".format(r)
                    edResistencia.setText("%.2f".format(r))

                    edTensao.text.clear()
                    edCorrente.text.clear()
                    edResistencia.text.clear()
                }

                // tá faltando a TENSÃO
                resistenciaStr.isNotBlank() && correnteStr.isNotBlank() && tensaoStr.isBlank() -> {
                    val r = resistenciaStr.toDouble()
                    val i = correnteStr.toDouble()

                    val v = r * i
                    txResultado.text = "Tensão calculada: %.2f V".format(v)
                    edTensao.setText("%.2f".format(v))

                    edTensao.text.clear()
                    edCorrente.text.clear()
                    edResistencia.text.clear()
                }

                else -> {
                    txResultado.text = "Preencha exatamente 2 dos 3 campos."
                }
            }
        }

    }
}
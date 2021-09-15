

package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.widget.EditText

//import kotlin.androidx.synthetic.main.activity_main.*
import kotlin.math.roundToLong

class MainActivity : AppCompatActivity() {
    private var num1 = 0.0
    private var num2 = 0.0
    private var operacion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Resultado.text = "0"
        operacion = SIN_OPERACION

        botonUno.setOnClickListener { numberPressed("1") }
        botonDos.setOnClickListener { numberPressed("2") }
        botonTres.setOnClickListener { numberPressed("3") }
        botonCuatro.setOnClickListener { numberPressed("4") }
        botonCinco.setOnClickListener { numberPressed("5") }
        botobSeis.setOnClickListener { numberPressed("6") }
        botonSiete.setOnClickListener { numberPressed("7") }
        botonOcho.setOnClickListener { numberPressed("8") }
        botonNueve.setOnClickListener { numberPressed("9") }
        botonCero.setOnClickListener { numberPressed("0") }
        botonPunto.setOnClickListener { numberPressed(".") }

        botonAC.setOnClickListener { resetAll() }

        botonPorcenta.setOnClickListener { operationPressed(PORCENTAJE) }
        botonSuma.setOnClickListener { operationPressed(SUMA) }
        botonResta.setOnClickListener { operationPressed(RESTA) }
        botonMultiply.setOnClickListener { operationPressed(MULTIPLICACION) }
        botonDivision.setOnClickListener { operationPressed(DIVISION) }

        botonIgual.setOnClickListener { resolvePressed() }
    }

    private fun numberPressed(num: String){
        if(Resultado.text == "0" && num != ".") {
            Resultado.text = "$num"
        } else {
            Resultado.text = "${Resultado.text}$num"
        }

        if(operacion == SIN_OPERACION){
            num1 = Resultado.text.toString().toDouble()
        } else {
            num2 = Resultado.text.toString().toDouble()
        }
    }

    private fun operationPressed(operacion: Int){
        this.operacion = operacion
        num1 = Resultado.text.toString().toDouble()

        Resultado.text = "0"
    }

    private fun resolvePressed(){

        val result = when(operacion) {
            PORCENTAJE -> (num1/100)
            SUMA -> num1 + num2
            RESTA -> num1 - num2
            MULTIPLICACION -> num1 * num2
            DIVISION -> num1 / num2
            else -> 0
        }

        num1 = result as Double

        Resultado.text = if("$result".endsWith(".0")) { "$result".replace(".0","") } else { "%.2f".format(result) }
    }

    private fun resetAll(){
        Resultado.text = "0"
        num1 = 0.0
        num2 = 0.0
    }

    companion object {
        const val SUMA = 1
        const val RESTA = 2
        const val MULTIPLICACION = 3
        const val DIVISION = 4
        const val PORCENTAJE = 5
        const val SIN_OPERACION = 0
    }
}

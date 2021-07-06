package com.example.calculatorkotlin

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

class MainActivity : AppCompatActivity() {
    private lateinit var workingsTV: TextView
    private lateinit var resultsTV: TextView

    private var workings = ""
    var formula = ""
    var tempFormula = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTextViews()
    }
    private fun initTextViews() {
        workingsTV = findViewById<View>(R.id.workingsTextView) as TextView
        resultsTV = findViewById<View>(R.id.resultTextView) as TextView
    }
    private fun setWorkings(givenValue: String) {
        workings += givenValue
        workingsTV.text = workings
    }
    fun equalsOnClick(view: View?) {
        var result: Double? = null
        val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")
        checkForPowerOf()
        try {
            result = engine.eval(formula) as Double?
        } catch (e: ScriptException) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
        }
        if (result != null) resultsTV.text = result.toDouble().toString()
    }
    private fun checkForPowerOf() {
        val indexOfPowers = ArrayList<Int>()
        for (i in workings.indices) {
            if (workings[i] == '^') indexOfPowers.add(i)
        }
        formula = workings
        tempFormula = workings
        for (index in indexOfPowers) {
            changeFormula(index)
        }
        formula = tempFormula
    }
    private fun changeFormula(index: Int) {
        var numberLeft = ""
        var numberRight = ""
        for (i in index + 1 until workings.length) {
            numberRight = if (isNumeric(workings[i])) numberRight + workings[i] else break
        }
        for (i in index - 1 downTo 0) {
            numberLeft = if (isNumeric(workings[i])) numberLeft + workings[i] else break
        }
        val original = "$numberLeft^$numberRight"
        val changed = "Math.pow($numberLeft,$numberRight)"
        tempFormula = tempFormula.replace(original, changed)
    }
    private fun isNumeric(c: Char): Boolean {
        return c in '0'..'9' || c == '.'
    }
    fun clearOnClick(view: View?) {
        workingsTV.text = ""
        workings = ""
        resultsTV.text = ""
        leftBracket = true
    }
    var leftBracket = true
    fun bracketsOnClick(view: View?) {
        leftBracket = if (leftBracket) {
            setWorkings("(")
            false
        } else {
            setWorkings(")")
            true
        }
    }
    fun powerOfOnClick(view: View?) {
        setWorkings("^")
    }
    fun divisionOnClick(view: View?) {
        setWorkings("/")
    }
    fun sevenOnClick(view: View?) {
        setWorkings("7")
    }
    fun eightOnClick(view: View?) {
        setWorkings("8")
    }
    fun nineOnClick(view: View?) {
        setWorkings("9")
    }
    fun timesOnClick(view: View?) {
        setWorkings("*")
    }
    fun fourOnClick(view: View?) {
        setWorkings("4")
    }
    fun fiveOnClick(view: View?) {
        setWorkings("5")
    }
    fun sixOnClick(view: View?) {
        setWorkings("6")
    }
    fun minusOnClick(view: View?) {
        setWorkings("-")
    }
    fun oneOnClick(view: View?) {
        setWorkings("1")
    }
    fun twoOnClick(view: View?) {
        setWorkings("2")
    }

    fun threeOnClick(view: View?) {
        setWorkings("3")
    }

    fun plusOnClick(view: View?) {
        setWorkings("+")
    }

    fun decimalOnClick(view: View?) {
        setWorkings(".")
    }

    fun zeroOnClick(view: View?) {
        setWorkings("0")
    }
}
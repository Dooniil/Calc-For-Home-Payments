package com.example.fragments.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.fragments.Calculator
import com.example.fragments.R
import java.math.RoundingMode
import java.text.DecimalFormat

class GasFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_gas, container, false)

        val calc: Calculator = Calculator(view, Calculator.Type.GAS)
        calc.editTextListener()
        activity?.let { calc.buttonListener(it) }

        return view
    }
}
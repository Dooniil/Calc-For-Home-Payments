package com.example.fragments.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragments.R
import com.example.fragments.Calculator

class ElectricFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_electric, container, false)

        val calc: Calculator = Calculator(view, Calculator.Type.ELECTRIC)
        calc.editTextListener()
        activity?.let { calc.buttonListener(it) }
        calc.button2Listener()

        return view
    }


}
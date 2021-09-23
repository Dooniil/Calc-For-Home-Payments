package com.example.fragments.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragments.R
import com.example.fragments.Calculator
import kotlinx.android.synthetic.main.fragment_gas.*

class ElectricFragment : Fragment() {

    private var state : State? = null
    private var calc : Calculator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        state = savedInstanceState?.getParcelable(KEY_STATE) ?: State (
            text = "",
            visibility = View.GONE
                )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_electric, container, false)

        calc = Calculator(view, Calculator.Type.ELECTRIC)
        calc?.render(state!!)
        calc?.editTextListener()
        activity?.let { calc?.buttonListener(it) }
        activity?.let { calc?.button2Listener(it) }
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, state!!)
    }

    override fun onStop() {
        super.onStop()
        state?.text = calc?.resultText?.text.toString()
        state?.visibility = calc?.resultText!!.visibility
    }
    companion object {
        var KEY_STATE = "STATE"
    }
}
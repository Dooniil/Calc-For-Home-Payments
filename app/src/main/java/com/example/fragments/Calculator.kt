package com.example.fragments

import android.app.Activity
import android.os.Parcelable
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentActivity
import com.example.fragments.fragments.State
import kotlinx.android.parcel.Parcelize
import java.math.RoundingMode
import java.text.DecimalFormat

class Calculator(private val view: View, private val type: Type) {

    enum class Type {
        ELECTRIC, WATER, GAS
    }

    fun render(state : State) {
        resultText.text = state.text
        resultText.visibility = state.visibility
    }

    private val prev: EditText = view.findViewById(R.id.prev)
    private val actual: EditText = view.findViewById(R.id.actual)
    private val button: Button = view.findViewById(R.id.button)
    val resultText: TextView = view.findViewById(R.id.result)
    private val button2: AppCompatButton = view.findViewById(R.id.button2)

    fun editTextListener() {
        actual.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                return@setOnEditorActionListener calculate()
            }
            return@setOnEditorActionListener false
        }
    }
    fun buttonListener(activity: Activity) {
        button.setOnClickListener {
            calculate()
            hideSoftKeyboard(activity)
            prev.clearFocus()
            actual.clearFocus()
        }
    }

    fun button2Listener(activity: Activity) {
        button2.setOnClickListener {
            prev.text.clear()
            actual.text.clear()
            prev.requestFocus()
            resultText.visibility = View.GONE
            showSoftKeyboard(activity)
        }
    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
    }
    private fun showSoftKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(prev, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun calculate() : Boolean {
        if (actual.text.isBlank() || prev.text.isBlank()) {
            if (actual.text.isBlank()) actual.error = "Пустое поле"
            if (prev.text.isBlank()) prev.error = "Пустое поле"
            return true
        }
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING

        val diff: Float = actual.text.toString().toFloat() - prev.text.toString().toFloat()
        if (diff < 0) {
            actual.error = "Некорректные данные"
            prev.error = "Некорректные данные"
            return true
        }
        val result = when (type) {
            Type.ELECTRIC -> calcForElectric(diff)
            Type.GAS -> calcForGas(diff)
            Type.WATER -> calcForWater(diff)
            else -> ""
        }
        resultText.visibility = View.VISIBLE
        resultText.text = "Разница - $diff\nВам нужно заплатить - ${df.format(result)} рублей"
        return false
    }

    private fun calcForGas(diff: Float) : Float {
        var result: Float = 0.0f
        result = if (diff <= 370.1) {
            diff * 1.9f
        } else {
            diff * 2.53f
        }
        return result
    }

    private fun calcForWater(diff: Float) : Float {
        return diff * 29.1f
    }
    private fun calcForElectric(diff: Float) : Float {
        var result: Float = 0.0f
        if (diff <= 75) {
            result = diff * 0.9f
        } else if (diff > 75 && diff <= 150) {
            result = (75 * 0.9f) + ((diff - 75) * 1.2f)
        } else if (diff > 150 && diff <= 800) {
            result = (75 * 0.9f) + (75 * 1.2f) + ((diff - 150) * 1.63f)
        } else if (diff > 800) {
            result = (75 * 0.9f) + (75 * 1.2f) + (650 * 1.63f) + ((diff - 800) * 2.85f)
        }
        return result
    }


}
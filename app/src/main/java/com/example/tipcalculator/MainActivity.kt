package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {

    private val billValueEditText: EditText by lazy {
        findViewById(R.id.edit_text)
    }
    private val tipPercentSlider: Slider by lazy {
        findViewById(R.id.slider)
    }
    private val outputTextView: TextView by lazy {
        findViewById(R.id.text_view)
    }


    var billValue = ""
        @Synchronized set
    var tipPercent = 0
        @Synchronized set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        billValueEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                billValue = s?.toString() ?: ""
                runOnUiThread { updateOutputTextView() }
            }
        })

        tipPercentSlider.addOnChangeListener { _, value, _ ->
            tipPercent = value.toInt()
            runOnUiThread { updateOutputTextView() }
        }

    }

    fun updateOutputTextView() {
        outputTextView.text =
            if (billValue.isBlank()) ""
            else "Tip amount: ${"%.2f".format(billValue.toDouble() * tipPercent.toDouble() / 100)}"
    }
}
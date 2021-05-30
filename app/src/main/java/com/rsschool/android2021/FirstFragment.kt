package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var etMin: EditText? = null
    private var etMax: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        etMin = view.findViewById<EditText>(R.id.min_value)
        etMax = view.findViewById<EditText>(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener {
            clickGenerateButton(etMin?.text.toString(), etMax?.text.toString())
        }
    }

    private fun clickGenerateButton(minValueString: String, maxValueString: String) {
        val minValue = minValueString.toIntOrNull()
        val maxValue = maxValueString.toIntOrNull()
        if (minValue == null || maxValue == null) {
            Toast.makeText(requireActivity(), "Неверный формат ввода", Toast.LENGTH_LONG).show()
        } else if (minValue > maxValue) {
            Toast.makeText(
                requireActivity(),
                "Минимальное знаение больше максимального",
                Toast.LENGTH_LONG
            ).show()
        } else {
            (requireActivity() as MainInterface).sendValues(minValue, maxValue)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}
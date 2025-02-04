package com.rsschool.android2021

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rsschool.android2021.FirstFragment.Companion.newInstance


class MainActivity : AppCompatActivity(), MainInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFirstFragment(0)
    }

    private fun openFirstFragment(previousNumber: Int) {
        val firstFragment: Fragment = newInstance(previousNumber)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, firstFragment)
            .addToBackStack(null)
            .commit()

    }

    private fun openSecondFragment(min: Int, max: Int) {
        val secondFragment = SecondFragment.newInstance(min, max)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, secondFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun sendValues(minValue: Int, maxValue: Int) {
        openSecondFragment(min = minValue, max = maxValue)
    }

    override fun sentPreviousResult(previousResult: Int) {
        openFirstFragment(previousNumber = previousResult)
    }
}
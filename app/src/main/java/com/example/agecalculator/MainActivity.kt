package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import com.example.agecalculator.databinding.ActivityMainBinding
import com.example.agecalculator.databinding.ActivityMainShimmerBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.shimmerLayout.visibility = View.VISIBLE
        binding.shimmerLayout.startShimmer()
        binding.seconConstraint.visibility = View.GONE

        Handler().postDelayed({
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = View.GONE
            binding.seconConstraint.visibility = View.VISIBLE
            binding.buttonConvert.setOnClickListener {
                setDatePicker()
            }
        }, 10000)
    }

    private fun setDatePicker(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "${selectedMonth + 1}/$selectedDay/$selectedYear"
                binding.textviewSelectedDate.text = selectedDate

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val finalDate = dateFormat.parse(selectedDate)
                finalDate?.let {
                    val selectedDateInMinutes = finalDate.time / 60000

                    val currentDate = dateFormat.parse(dateFormat.format(System.currentTimeMillis()))

                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        binding.textviewAgeConverted.text = differenceInMinutes.toString()
                    }

                }

            },
            year,
            month,
            day
        ).show()
    }

}
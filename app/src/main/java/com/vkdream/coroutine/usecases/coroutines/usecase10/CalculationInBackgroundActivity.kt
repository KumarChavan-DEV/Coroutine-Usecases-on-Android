package com.vkdream.coroutine.usecases.coroutines.usecase10

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.vkdream.coroutine.R
import com.vkdream.coroutine.base.BaseActivity
import com.vkdream.coroutine.base.useCase10Description
import com.vkdream.coroutine.databinding.ActivityCalculationinbackgroundBinding
import com.vkdream.coroutine.utils.hideKeyboard
import com.vkdream.coroutine.utils.setGone
import com.vkdream.coroutine.utils.setVisible
import com.vkdream.coroutine.utils.toast

class CalculationInBackgroundActivity : BaseActivity() {

    override fun getToolbarTitle() = useCase10Description

    private val binding by lazy { ActivityCalculationinbackgroundBinding.inflate(layoutInflater) }
    private val viewModel: CalculationInBackgroundViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.uiState().observe(this, Observer { uiState ->
            if (uiState != null) {
                render(uiState)
            }
        })
        binding.btnCalculate.setOnClickListener {
            val factorialOf = binding.editTextFactorialOf.text.toString().toIntOrNull()
            if (factorialOf != null) {
                viewModel.performCalculation(factorialOf)
            }
        }
    }

    private fun render(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                onLoad()
            }
            is UiState.Success -> {
                onSuccess(uiState)
            }
            is UiState.Error -> {
                onError(uiState)
            }
        }
    }

    private fun onLoad() = with(binding) {
        progressBar.setVisible()
        textViewResult.text = ""
        textViewCalculationDuration.text = ""
        textViewStringConversionDuration.text = ""
        btnCalculate.isEnabled = false
        textViewResult.hideKeyboard()
    }

    private fun onSuccess(uiState: UiState.Success) = with(binding) {
        textViewCalculationDuration.text =
            getString(R.string.duration_calculation, uiState.computationDuration)

        textViewStringConversionDuration.text =
            getString(R.string.duration_stringconversion, uiState.stringConversionDuration)

        progressBar.setGone()
        btnCalculate.isEnabled = true
        textViewResult.text = if (uiState.result.length <= 150) {
            uiState.result
        } else {
            "${uiState.result.substring(0, 147)}..."
        }
    }

    private fun onError(uiState: UiState.Error) = with(binding) {
        progressBar.setGone()
        btnCalculate.isEnabled = true
        toast(uiState.message)
    }
}
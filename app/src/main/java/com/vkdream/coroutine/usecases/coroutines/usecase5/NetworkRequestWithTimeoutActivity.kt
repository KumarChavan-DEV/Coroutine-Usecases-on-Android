package com.vkdream.coroutine.usecases.coroutines.usecase5

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.vkdream.coroutine.base.BaseActivity
import com.vkdream.coroutine.base.useCase5Description
import com.vkdream.coroutine.databinding.ActivityNetworkrequestwithtimeoutBinding
import com.vkdream.coroutine.utils.fromHtml
import com.vkdream.coroutine.utils.setGone
import com.vkdream.coroutine.utils.setVisible
import com.vkdream.coroutine.utils.toast

class NetworkRequestWithTimeoutActivity : BaseActivity() {

    override fun getToolbarTitle() = useCase5Description

    private val binding by lazy { ActivityNetworkrequestwithtimeoutBinding.inflate(layoutInflater) }
    private val viewModel: NetworkRequestWithTimeoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.uiState().observe(this, Observer { uiState ->
            if (uiState != null) {
                render(uiState)
            }
        })
        binding.btnPerformSingleNetworkRequest.setOnClickListener {
            val timeOut = binding.editTextTimeOut.text.toString().toLongOrNull()
            if (timeOut != null) {
                viewModel.performNetworkRequest(timeOut)
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
        btnPerformSingleNetworkRequest.isEnabled = false
    }

    private fun onSuccess(uiState: UiState.Success) = with(binding) {
        progressBar.setGone()
        btnPerformSingleNetworkRequest.isEnabled = true
        val readableVersions = uiState.recentVersions.map { "API ${it.apiLevel}: ${it.name}" }
        textViewResult.text = fromHtml(
            "<b>Recent Android Versions</b><br>${readableVersions.joinToString(separator = "<br>")}"
        )
    }

    private fun onError(uiState: UiState.Error) = with(binding) {
        progressBar.setGone()
        btnPerformSingleNetworkRequest.isEnabled = true
        toast(uiState.message)
    }
}
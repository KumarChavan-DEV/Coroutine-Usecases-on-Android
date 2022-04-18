package com.vkdream.coroutine.usecases.coroutines.usecase6

import com.vkdream.coroutine.mock.AndroidVersion
import com.vkdream.coroutine.mock.MockApi
import com.vkdream.coroutine.mock.VersionFeatures
import com.vkdream.coroutine.mock.mockAndroidVersions
import com.vkdream.coroutine.utils.EndpointShouldNotBeCalledException
import kotlinx.coroutines.delay
import java.io.IOException

class FakeSuccessOnThirdAttemptApi(private val responseDelay: Long) : MockApi {

    var requestCount = 0

    override suspend fun getRecentAndroidVersions(): List<AndroidVersion> {
        requestCount++
        delay(responseDelay)
        if (requestCount < 3) {
            throw IOException()
        }
        return mockAndroidVersions
    }

    override suspend fun getAndroidVersionFeatures(apiLevel: Int): VersionFeatures {
        throw EndpointShouldNotBeCalledException()
    }
}
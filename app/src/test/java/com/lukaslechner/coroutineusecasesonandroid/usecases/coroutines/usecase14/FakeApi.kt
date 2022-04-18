package com.vkdream.coroutine.usecases.coroutines.usecase14

import com.vkdream.coroutine.mock.AndroidVersion
import com.vkdream.coroutine.mock.MockApi
import com.vkdream.coroutine.mock.VersionFeatures
import com.vkdream.coroutine.mock.mockAndroidVersions
import com.vkdream.coroutine.utils.EndpointShouldNotBeCalledException
import kotlinx.coroutines.delay

class FakeApi : MockApi {

    override suspend fun getRecentAndroidVersions(): List<AndroidVersion> {
        delay(1)
        return mockAndroidVersions
    }

    override suspend fun getAndroidVersionFeatures(apiLevel: Int): VersionFeatures {
        throw EndpointShouldNotBeCalledException()
    }
}
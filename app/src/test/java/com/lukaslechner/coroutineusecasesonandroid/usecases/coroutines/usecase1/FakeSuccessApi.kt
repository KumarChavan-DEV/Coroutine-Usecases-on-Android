package com.vkdream.coroutine.usecases.coroutines.usecase1

import com.vkdream.coroutine.mock.AndroidVersion
import com.vkdream.coroutine.mock.MockApi
import com.vkdream.coroutine.mock.VersionFeatures
import com.vkdream.coroutine.mock.mockAndroidVersions
import com.vkdream.coroutine.utils.EndpointShouldNotBeCalledException

class FakeSuccessApi() : MockApi {

    override suspend fun getRecentAndroidVersions(): List<AndroidVersion> {
        return mockAndroidVersions
    }

    override suspend fun getAndroidVersionFeatures(apiLevel: Int): VersionFeatures {
        throw EndpointShouldNotBeCalledException()
    }
}
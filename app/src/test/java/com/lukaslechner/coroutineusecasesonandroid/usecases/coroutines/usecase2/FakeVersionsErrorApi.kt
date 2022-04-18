package com.vkdream.coroutine.usecases.coroutines.usecase2

import com.vkdream.coroutine.mock.AndroidVersion
import com.vkdream.coroutine.mock.MockApi
import com.vkdream.coroutine.mock.VersionFeatures
import com.vkdream.coroutine.utils.EndpointShouldNotBeCalledException
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class FakeVersionsErrorApi : MockApi {

    override suspend fun getRecentAndroidVersions(): List<AndroidVersion> {
        throw HttpException(
            Response.error<List<AndroidVersion>>(
                500,
                ResponseBody.create(MediaType.parse("application/json"), "")
            )
        )
    }

    override suspend fun getAndroidVersionFeatures(apiLevel: Int): VersionFeatures {
        throw EndpointShouldNotBeCalledException()
    }
}
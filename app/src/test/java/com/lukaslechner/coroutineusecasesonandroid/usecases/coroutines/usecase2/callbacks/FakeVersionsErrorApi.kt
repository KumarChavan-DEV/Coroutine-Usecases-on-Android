package com.vkdream.coroutine.usecases.coroutines.usecase2.callbacks

import com.vkdream.coroutine.mock.AndroidVersion
import com.vkdream.coroutine.mock.VersionFeatures
import com.vkdream.coroutine.mock.mockAndroidVersions
import retrofit2.Call
import retrofit2.mock.Calls
import java.io.IOException

class FakeVersionsErrorApi : CallbackMockApi {

    override fun getRecentAndroidVersions(): Call<List<AndroidVersion>> {
        return Calls.response(mockAndroidVersions)
    }

    override fun getAndroidVersionFeatures(apiLevel: Int): Call<VersionFeatures> {
        return Calls.failure(IOException())
    }
}
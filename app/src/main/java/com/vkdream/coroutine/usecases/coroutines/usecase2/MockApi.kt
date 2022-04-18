package com.vkdream.coroutine.usecases.coroutines.usecase2

import com.google.gson.Gson
import com.vkdream.coroutine.mock.createMockApi
import com.vkdream.coroutine.mock.mockAndroidVersions
import com.vkdream.coroutine.mock.mockVersionFeaturesAndroid10
import com.vkdream.coroutine.utils.MockNetworkInterceptor

fun mockApi() = createMockApi(
    MockNetworkInterceptor()
        .mock(
            "http://localhost/recent-android-versions",
            Gson().toJson(mockAndroidVersions),
            200,
            1500
        )
        .mock(
            "http://localhost/android-version-features/29",
            Gson().toJson(mockVersionFeaturesAndroid10),
            200,
            1500
        )
)
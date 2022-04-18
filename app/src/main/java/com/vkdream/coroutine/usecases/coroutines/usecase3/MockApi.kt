package com.vkdream.coroutine.usecases.coroutines.usecase3

import com.google.gson.Gson
import com.vkdream.coroutine.mock.createMockApi
import com.vkdream.coroutine.mock.mockVersionFeaturesAndroid10
import com.vkdream.coroutine.mock.mockVersionFeaturesOreo
import com.vkdream.coroutine.mock.mockVersionFeaturesPie
import com.vkdream.coroutine.utils.MockNetworkInterceptor

fun mockApi() = createMockApi(
    MockNetworkInterceptor()
        .mock(
            "http://localhost/android-version-features/27",
            Gson().toJson(mockVersionFeaturesOreo),
            200,
            1000
        )
        .mock(
            "http://localhost/android-version-features/28",
            Gson().toJson(mockVersionFeaturesPie),
            200,
            1000
        )
        .mock(
            "http://localhost/android-version-features/29",
            Gson().toJson(mockVersionFeaturesAndroid10),
            200,
            1000
        )
)
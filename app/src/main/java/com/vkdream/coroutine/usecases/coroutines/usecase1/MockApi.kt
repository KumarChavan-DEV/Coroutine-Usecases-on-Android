package com.vkdream.coroutine.usecases.coroutines.usecase1

import com.google.gson.Gson
import com.vkdream.coroutine.mock.createMockApi
import com.vkdream.coroutine.mock.mockAndroidVersions
import com.vkdream.coroutine.utils.MockNetworkInterceptor

fun mockApi() =
    createMockApi(
        MockNetworkInterceptor()
            .mock(
                "http://localhost/recent-android-versions",
                Gson().toJson(mockAndroidVersions),
                200,
                1500
            )
    )
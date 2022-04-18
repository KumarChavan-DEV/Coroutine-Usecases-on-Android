package com.vkdream.coroutine.usecases.coroutines.usecase6

import com.google.gson.Gson
import com.vkdream.coroutine.mock.createMockApi
import com.vkdream.coroutine.mock.mockAndroidVersions
import com.vkdream.coroutine.utils.MockNetworkInterceptor

fun mockApi() = createMockApi(
    MockNetworkInterceptor()
        .mock(
            "http://localhost/recent-android-versions",
            "something went wrong on server side",
            500,
            1000,
            persist = false
        ).mock(
            "http://localhost/recent-android-versions",
            "something went wrong on server side",
            500,
            1000,
            persist = false
        ).mock(
            "http://localhost/recent-android-versions",
            Gson().toJson(mockAndroidVersions),
            200,
            1000
        )
)
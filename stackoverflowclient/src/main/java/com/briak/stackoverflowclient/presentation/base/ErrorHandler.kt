package com.briak.stackoverflowclient.presentation.base

import com.briak.stackoverflowclient.entities.server.ErrorResponse
import com.briak.stackoverflowclient.extensions.userMessage
import com.briak.stackoverflowclient.model.system.ResourceManager
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.nio.charset.Charset
import javax.inject.Inject

open class ErrorHandler @Inject constructor(private val resourceManager: ResourceManager) {
    fun proceed(error: Throwable): String {
        return if (error is HttpException) {
            val code = error.code()
            if (code in 400..407 || code == 407 || code in 409..500 || code in 502..503) {
                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter<ErrorResponse>(ErrorResponse::class.java)
                val errorResponse = jsonAdapter.fromJson(String(
                        error.response().errorBody()!!.source().readByteArray(),
                        Charset.defaultCharset()))
                errorResponse.errorMessage
            } else {
                error.userMessage(resourceManager)
            }
        } else {
            error.userMessage(resourceManager)
        }
    }
}
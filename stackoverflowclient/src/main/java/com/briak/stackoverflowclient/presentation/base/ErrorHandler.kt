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
        if (error is HttpException) {
            val code = error.code()
            if (code == 400 || code == 401 || code == 429 || code == 500) {
                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter<ErrorResponse>(ErrorResponse::class.java)
                val errorResponse = jsonAdapter.fromJson(String(
                        error.response().errorBody()!!.source().readByteArray(),
                        Charset.defaultCharset()))
                return errorResponse.message
            } else {
                return error.userMessage(resourceManager)
            }
        } else {
            return error.userMessage(resourceManager)
        }
    }
}
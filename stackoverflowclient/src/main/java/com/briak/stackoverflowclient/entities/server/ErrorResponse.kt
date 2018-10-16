package com.briak.stackoverflowclient.entities.server

import com.squareup.moshi.Json

data class ErrorResponse(
        @Json(name = "error_name")
        val errorName: String,

        @Json(name = "error_id")
        val errorId: String,

        @Json(name = "error_message")
        val errorMessage: String
)
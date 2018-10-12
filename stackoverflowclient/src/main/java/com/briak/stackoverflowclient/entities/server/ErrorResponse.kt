package com.briak.stackoverflowclient.entities.server

data class ErrorResponse(
        val status: String,
        val code: String,
        val message: String
)
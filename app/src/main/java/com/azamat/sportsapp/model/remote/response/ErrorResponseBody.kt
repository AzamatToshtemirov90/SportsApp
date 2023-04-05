package com.azamat.sportsapp.model.remote.response

data class ErrorResponseBody(
    val status: String?,
    val code: String?,
    val message: String?
)
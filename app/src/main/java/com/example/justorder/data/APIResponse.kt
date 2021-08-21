package com.example.justorder.data

class APIResponse<T> {

    private var response: T? = null
    private var errorMessage: String? = null
    private var error = false

    fun getResponse(): T? {
        return response
    }

    fun setResponse(response: T) {
        this.response = response
    }

    fun getErrorMessage(): String? {
        return errorMessage
    }

    fun setErrorMessage(errorMessage: String?) {
        this.errorMessage = errorMessage
    }

    fun isError(): Boolean {
        return error
    }

    fun setError(error: Boolean) {
        this.error = error
    }
}
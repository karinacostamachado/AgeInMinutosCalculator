package com.example.agecalculator.model

sealed class Results<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T?): Results<T>(data)
    class Error<T>(message: String?, data: T? = null): Results<T>(data)
    class Loading<T>(data: T? = null): Results<T>(data)
}

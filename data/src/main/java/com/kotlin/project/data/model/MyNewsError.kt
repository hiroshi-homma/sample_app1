package com.kotlin.project.data.model

sealed class MyNewsError(val alertMessage: String) :
    Throwable() {
    class UnrecognizedError : MyNewsError(alertMessage = "エラーが発生しました。再度お試しください。")
    // ToDo() ↓ add Error...
}
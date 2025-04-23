package com.ahrokholska.logging

expect object Logger {
    fun d(tag: String, message: String)
}
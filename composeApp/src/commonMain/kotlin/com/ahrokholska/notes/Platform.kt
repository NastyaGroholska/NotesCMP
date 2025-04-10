package com.ahrokholska.notes

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
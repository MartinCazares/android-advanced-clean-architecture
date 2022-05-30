package com.doepiccoding.advancedcleanarchitecture.utils

interface NetworkErrorInterpreter {
    fun interpret(status: Int): String
}
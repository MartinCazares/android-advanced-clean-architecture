package com.doepiccoding.domain.usecase

import com.doepiccoding.domain.entity.error.ErrorEntity

sealed interface UseCaseResult {
    data class Success (private val data: Any) : UseCaseResult {
        @Suppress("UNCHECKED_CAST")
        fun <R> getData() = data as R//Developer error let it throw exception...
    }
    data class Error(val error: ErrorEntity): UseCaseResult
}
package com.doepiccoding.advancedcleanarchitecture.ui.breeds.screen_state

import android.content.Context
import com.doepiccoding.advancedcleanarchitecture.R
import com.doepiccoding.advancedcleanarchitecture.utils.NetworkErrorInterpreter

class BreedNetworkErrorInterpreter(private val context: Context) : NetworkErrorInterpreter {

    companion object {
        const val UNAUTHORIZED = 401
        const val BREEDS_NOT_FOUND = 404
    }

    override fun interpret(status: Int): String =
        when (status) {
            UNAUTHORIZED -> context.getString(R.string.breeds_error_unauthorized)
            BREEDS_NOT_FOUND -> context.getString(R.string.breeds_not_found)
            else -> context.getString(R.string.breeds_error_unknown)
        }
}

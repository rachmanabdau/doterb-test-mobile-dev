package com.example.mymoviddb.core.utils.preference

interface Preference {
    fun setAuthState(state: LoginState)
    fun getAuthState(): Int
    fun setToken(token: String)
    fun getToken(): String
    fun logout()
}
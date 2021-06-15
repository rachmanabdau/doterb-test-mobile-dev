package com.example.mymoviddb.core.utils.preference

import android.app.Application
import android.content.Context
import javax.inject.Inject


class UserPreference @Inject constructor(application: Application) : Preference {

    private val USER_TOKEN_KEY =
        "package com.example.mymoviddb.core.utils.preference.UserPreference.userTokenKey"
    private val LOGIN_STATE_KEY =
        "package com.example.mymoviddb.core.utils.preference.UserPreference.loginStateKey"

    val sharedPrefsFile: String =
        "package com.example.mymoviddb.core.utils.preference.UserPreference"

    val sharedPreferences =
        application.applicationContext.getSharedPreferences(sharedPrefsFile, Context.MODE_PRIVATE)

    override fun setAuthState(state: LoginState) {
        val preferenceEditor = sharedPreferences.edit()
        preferenceEditor.putInt(LOGIN_STATE_KEY, state.stateId)
        preferenceEditor.apply()
    }

    override fun getAuthState(): Int {
        return sharedPreferences.getInt(LOGIN_STATE_KEY, LoginState.NOT_LOGIN.stateId)
    }

    override fun setToken(token: String) {
        val preferenceEditor = sharedPreferences.edit()
        preferenceEditor.putString(USER_TOKEN_KEY, token)
        preferenceEditor.apply()
    }

    override fun getToken(): String {
        return sharedPreferences.getString(USER_TOKEN_KEY, "").toString()
    }

    override fun logout() {
        sharedPreferences.edit().clear().apply()
    }
}

enum class LoginState(val stateId: Int) {
    NOT_LOGIN(0),
    AS_USER(1)
}
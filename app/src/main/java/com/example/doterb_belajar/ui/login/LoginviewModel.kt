package com.example.doterb_belajar.ui.login

import androidx.lifecycle.*
import com.example.doterb_belajar.R
import com.example.doterb_belajar.model.Event
import com.example.doterb_belajar.model.LoginResponse
import com.example.doterb_belajar.model.Result
import com.example.mymoviddb.core.utils.preference.LoginState
import com.example.mymoviddb.core.utils.preference.Preference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginviewModel @Inject constructor(
    private val userPreference: Preference,
    private val loginRepository: LoginRepository
) :
    ViewModel() {

    private val _login = MutableLiveData<Result<LoginResponse?>>()
    val loginResult: LiveData<Result<LoginResponse?>> = _login

    private val _usernameError = MutableLiveData<Event<Int>>()
    val usernameError: LiveData<Event<Int>> = _usernameError

    private val _passswordError = MutableLiveData<Event<Int>>()
    val passswordError: LiveData<Event<Int>> = _passswordError

    fun login(username: String, password: String) {
        viewModelScope.launch {
            if (username.isBlank()) {
                _usernameError.value = Event(R.string.error_username_empty)
                return@launch
            }
            if (password.isBlank()) {
                _usernameError.value = Event(R.string.error_empty_password)
                return@launch
            }

            val result = loginRepository.login(username, password)
            if (result is Result.Success) {
                userPreference.setAuthState(LoginState.AS_USER)
                userPreference.setToken(result.data?.result.toString())
            }

            _login.value = loginRepository.login(username, password)
        }
    }
}
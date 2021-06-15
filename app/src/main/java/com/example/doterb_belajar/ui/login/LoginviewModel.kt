package com.example.doterb_belajar.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.doterb_belajar.R
import com.example.doterb_belajar.model.Event
import com.example.doterb_belajar.model.LoginResponse
import com.example.doterb_belajar.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginviewModel @Inject constructor(
    private val app: Application,
    private val loginRepository: LoginRepository
) :
    AndroidViewModel(app) {

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

            _login.value = loginRepository.login(username, password)
        }
    }
}
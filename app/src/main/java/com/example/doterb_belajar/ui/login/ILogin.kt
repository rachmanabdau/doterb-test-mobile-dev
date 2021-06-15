package com.example.doterb_belajar.ui.login

import com.example.doterb_belajar.model.LoginResponse
import com.example.doterb_belajar.model.Result

interface ILogin {

    suspend fun login(username: String, password: String): Result<LoginResponse?>
}
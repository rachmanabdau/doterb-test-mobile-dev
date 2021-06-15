package com.example.doterb_belajar.ui.login

import com.example.doterb_belajar.model.LoginResponse
import com.example.doterb_belajar.model.Result
import com.example.doterb_belajar.source.remote.NetworkService
import com.example.doterb_belajar.util.Util
import javax.inject.Inject

class LoginRepository @Inject constructor(private val networkService: NetworkService) : ILogin {

    override suspend fun login(username: String, password: String): Result<LoginResponse?> {
        return try {
            val result = networkService.loginAsync(username, password).await()
            if (result.body() != null) {
                Result.Success(result.body())
            } else {
                Util.returnError(result)
            }
        } catch (e: Exception) {
            Result.Error(Exception(e.message))
        }
    }
}
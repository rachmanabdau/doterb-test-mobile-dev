package com.example.doterb_belajar.di

import com.example.doterb_belajar.ui.home.HomeRepository
import com.example.doterb_belajar.ui.home.IHome
import com.example.doterb_belajar.ui.login.ILogin
import com.example.doterb_belajar.ui.login.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class BelajarProvider {

    @Binds
    @ViewModelScoped
    abstract fun bindsLoginRepository(loginRepo: LoginRepository): ILogin

    @Binds
    @ViewModelScoped
    abstract fun bindsHomeRepository(homeRepo: HomeRepository): IHome
}
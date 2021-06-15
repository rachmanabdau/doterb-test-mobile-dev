package com.example.doterb_belajar.di

import com.example.mymoviddb.core.utils.preference.Preference
import com.example.mymoviddb.core.utils.preference.UserPreference
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class PreferenceBinder {

    @Singleton
    @Binds
    abstract fun bindsUserPreference(userPref: UserPreference): Preference
}
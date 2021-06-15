package com.example.doterb_belajar.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.doterb_belajar.model.Belajar
import com.example.mymoviddb.core.utils.preference.Preference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val userPreference: Preference
) : ViewModel() {

    private val _belajarList = MutableLiveData<PagingData<Belajar.Result.Data>>()
    val belajarLis: LiveData<PagingData<Belajar.Result.Data>> = _belajarList

    fun getBelajarList() {
        viewModelScope.launch {
            homeRepository.getBelajarList().mapLatest {
                _belajarList.value = it
            }
        }
    }

    fun getAuthState(): Int {
        return userPreference.getAuthState()
    }
}
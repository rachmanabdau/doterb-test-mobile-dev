package com.example.doterb_belajar.ui.home

import androidx.paging.PagingData
import com.example.doterb_belajar.model.Belajar
import kotlinx.coroutines.flow.Flow

interface IHome {

    suspend fun getBelajarList(): Flow<PagingData<Belajar.Result.Data>>
}
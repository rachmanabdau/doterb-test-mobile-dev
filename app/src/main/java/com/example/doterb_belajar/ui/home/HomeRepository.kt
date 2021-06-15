package com.example.doterb_belajar.ui.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.doterb_belajar.model.Belajar
import com.example.doterb_belajar.source.remote.BelajarDataSource
import com.example.doterb_belajar.source.remote.NetworkService
import com.example.mymoviddb.core.utils.preference.Preference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val networkService: NetworkService,
    private val userPreference: Preference
) : IHome {

    override suspend fun getBelajarList(): Flow<PagingData<Belajar.Result.Data>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 4,
                enablePlaceholders = false
            )
        ) {
            BelajarDataSource(networkService, userPreference)
        }.flow
    }

}
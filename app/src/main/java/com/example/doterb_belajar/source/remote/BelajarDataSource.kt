package com.example.doterb_belajar.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.doterb_belajar.model.Belajar
import com.example.doterb_belajar.model.Result
import com.example.doterb_belajar.util.Util
import com.example.mymoviddb.core.utils.preference.Preference

class BelajarDataSource(
    private val networkService: NetworkService,
    private val userPreference: Preference
) : PagingSource<Int, Belajar.Result.Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Belajar.Result.Data> {
        // Start refresh at page 1 if undefined.
        val nextPageNumber = params.key ?: 1
        return try {
            val belajarResult: Result<Belajar.Result?> = getData(userPreference.getToken())


            if (belajarResult is Result.Success) {
                val belajarList = belajarResult.data?.data ?: emptyList()
                val nextKey = if (belajarList.isEmpty()) null else nextPageNumber + 1
                LoadResult.Page(
                    data = belajarList,
                    prevKey = null,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(Exception(getErrorMessage(belajarResult)))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Belajar.Result.Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun getErrorMessage(result: Result<*>): String {
        return if (result is Result.Error) {
            result.exception.localizedMessage ?: "Unknown exception occured."
        } else {
            "Unknown exception occured."
        }
    }

    private suspend fun getData(token: String): Result<Belajar.Result?> {
        return try {
            val result = networkService.getBelajarListAsync(token).await()

            if (result.isSuccessful) {
                Result.Success(result.body()?.result)
            } else {
                Util.returnError(result)
            }

        } catch (e: Exception) {
            Result.Error(Exception(e.message))
        }
    }

    /*private suspend fun getShowData(
        showType: ShowCategoryIndex,
        pageNumber: Int
    ): Result<ShowResponse?> {
        return when (showType) {
            ShowCategoryIndex.SEARCH_MOVIES ->
                sourceDependency.searchMovie(pageNumber)

            ShowCategoryIndex.POPULAR_MOVIES ->
                sourceDependency.getPopularMovies(pageNumber)

            ShowCategoryIndex.NOW_PLAYING_MOVIES ->
                sourceDependency.getNowPlayiingMovies(pageNumber)

            ShowCategoryIndex.FAVOURITE_MOVIES ->
                sourceDependency.getFavouriteMovies(pageNumber)

            ShowCategoryIndex.WATCHLIST_MOVIES ->
                sourceDependency.getWatchListMovies(pageNumber)

            ShowCategoryIndex.SEARCH_TV_SHOWS ->
                sourceDependency.searchTvShows(pageNumber)

            ShowCategoryIndex.POPULAR_TV_SHOWS ->
                sourceDependency.getPopularTvShows(pageNumber)

            ShowCategoryIndex.ON_AIR_TV_SHOWS ->
                sourceDependency.getOnAirTvShows(pageNumber)

            ShowCategoryIndex.FAVOURITE_TV_SHOWS ->
                sourceDependency.getFavouriteTvShows(pageNumber)

            ShowCategoryIndex.WATCHLIST_TV_SHOWS ->
                sourceDependency.getWatchListTvShows(pageNumber)
        }
    }*/
}
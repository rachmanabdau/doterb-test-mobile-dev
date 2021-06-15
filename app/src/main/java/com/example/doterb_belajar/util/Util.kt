package com.example.doterb_belajar.util

import android.net.ParseException
import com.example.doterb_belajar.model.ErrorResponse
import com.example.doterb_belajar.model.Result
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

object Util {

    /**
     * convert time stamp in string in format "yyyy-MM-dd HH:mm:ss"
     * to time in milli second
     *
     * [timeInString] time in string
     */
    fun convertStringTimeToMills(timeInString: String): Long {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return try {
            val mDate: Date = sdf.parse(timeInString) as Date
            mDate.time
        } catch (e: ParseException) {
            e.printStackTrace()
            -1
        }
    }

    fun returnError(result: Response<*>): Result.Error {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val errorAdapter = moshi.adapter(ErrorResponse::class.java)
        val errorMessage = result.errorBody()?.string() ?: "Unknown error has occured"
        val errorJson =
            errorAdapter.fromJson(errorMessage)

        return Result.Error(Exception(errorJson?.errors.toString()))
    }
}
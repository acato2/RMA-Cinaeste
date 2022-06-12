package com.example.cinaeste.data

import androidx.room.*
import com.example.cinaeste.data.Cast

@Dao
interface CastDao {
    @Insert
    suspend fun insertAll(vararg cast: Cast)

    @Transaction
    @Delete
    suspend fun deleteCast(cast: List<Cast>)
}
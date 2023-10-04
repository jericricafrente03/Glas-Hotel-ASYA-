package com.ph.bittelasia.meshtv.tv.glasshoteldemo.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.database.item.AirmediaItem
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.database.item.AmenitiesItem
import kotlinx.coroutines.flow.Flow


@Dao
interface AirmediaDao {
    @Query("SELECT * FROM airmedia")
    fun getAllAirmedia(): Flow<List<AirmediaItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAirmedia(appData: List<AirmediaItem>)

    @Query("DELETE FROM airmedia")
    suspend fun deleteAllAirmedia()
}
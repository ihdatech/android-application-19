package io.github.ihdatech.myapplication.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ZodiacDao {

    @Query("SELECT * FROM zodiac_table ORDER BY name ASC")
    fun getAll(): LiveData<List<ZodiacEntity>>

    @Query("DELETE FROM zodiac_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg entity: ZodiacEntity)

    @Delete
    suspend fun delete(entity: ZodiacEntity)

    @Update
    suspend fun update(vararg entity: ZodiacEntity)
}
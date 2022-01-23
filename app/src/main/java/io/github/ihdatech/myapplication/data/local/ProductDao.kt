package io.github.ihdatech.myapplication.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_table ORDER BY id ASC")
    fun getAll(): LiveData<List<ProductEntity>>

    @Query("DELETE FROM product_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg entity: ProductEntity)

    @Delete
    suspend fun delete(entity: ProductEntity)

    @Update
    suspend fun update(vararg entity: ProductEntity)
}
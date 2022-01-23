package io.github.ihdatech.myapplication.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductImageDao {

    @Query("SELECT * FROM product_image_table ORDER BY product ASC")
    fun getAll(): LiveData<List<ProductImageEntity>>

    @Query("DELETE FROM product_image_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg entity: ProductImageEntity)

    @Delete
    suspend fun delete(entity: ProductImageEntity)

    @Update
    suspend fun update(vararg entity: ProductImageEntity)
}
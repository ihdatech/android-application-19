package io.github.ihdatech.myapplication.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_image_table")
data class ProductImageEntity(
    @ColumnInfo(name = "product") val product: Int,
    @PrimaryKey @ColumnInfo(name = "images") val images: String,
)

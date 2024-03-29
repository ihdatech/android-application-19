package io.github.ihdatech.myapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.ihdatech.myapplication.data.model.LoggedInProduct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

@Database(entities = [ProductEntity::class, ProductImageEntity::class], version = 1, exportSchema = false)
abstract class LocalRoomDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun productImageDao(): ProductImageDao

    companion object {
        @Volatile
        private var INSTANCE: LocalRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): LocalRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalRoomDatabase::class.java,
                    "myapplication_database"
                ).fallbackToDestructiveMigration()
                    .addCallback(LocalDataSourceCallback(context, scope))
                    .build()

                INSTANCE = instance
                instance
            }
        }

        private class LocalDataSourceCallback(
            private val context: Context,
            private val scope: CoroutineScope,
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(
                            context,
                            database.productDao(),
                            database.productImageDao(),
                        )
                    }
                }
            }
        }

        suspend fun populateDatabase(
            context: Context,
            productDao: ProductDao,
            productImageDao: ProductImageDao,
        ) {
            productDao.deleteAll()
            val jsonStringFile = "CoverProducts.json".getJsonFromAsset(context)
            val listCoverType = object : TypeToken<List<LoggedInProduct>>() {}.type
            val gson = Gson()
            val loggedInProduct: List<LoggedInProduct> = gson.fromJson(jsonStringFile, listCoverType)
            loggedInProduct.forEach {
                val productEntity = ProductEntity(it.id, it.price, it.title, it.description)
                productDao.insert(productEntity)

                it.images.forEach { image ->
                    val productImageEntity = ProductImageEntity(it.id, image)
                    productImageDao.insert(productImageEntity)
                }
            }
        }

        private fun String.getJsonFromAsset(context: Context): String? {
            return try {
                context.assets.open(this).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                null
            }
        }
    }
}
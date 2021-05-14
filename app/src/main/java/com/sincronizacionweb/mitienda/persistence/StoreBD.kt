package com.sincronizacionweb.mitienda.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sincronizacionweb.mitienda.persistence.dao.ProductDao
import com.sincronizacionweb.mitienda.persistence.entities.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
@TypeConverters(Converters::class )
abstract class StoreBD: RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object{
        private var instance:StoreBD? = null

        fun getInstance(context: Context): StoreBD?{
            if (instance==null){
                synchronized(StoreBD::class){
                    instance = Room.databaseBuilder(context.applicationContext, StoreBD::class.java, "store_data_base")
                        .fallbackToDestructiveMigration().build()
                }
            }
            return instance
        }
    }

}
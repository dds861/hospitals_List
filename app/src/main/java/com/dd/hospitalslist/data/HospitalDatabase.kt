package com.dd.hospitalslist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dd.hospitalslist.data.entities.Hospital
import com.dd.hospitalslist.data.entities.Region
import com.dd.hospitalslist.data.entities.Category
import kotlinx.coroutines.CoroutineScope

@Database(
        entities = [Hospital::class, Region::class, Category::class],
        version = 1,
        exportSchema = false
)
abstract class HospitalDatabase : RoomDatabase() {

    abstract fun hospitalsDao(): HospitalDao

    companion object {
        private var INSTANCE: HospitalDatabase? = null

        fun getInstance(context: Context, coroutineScope: CoroutineScope): HospitalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, HospitalDatabase::class.java, "hospitals")
                    .createFromAsset("database/hospitals.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
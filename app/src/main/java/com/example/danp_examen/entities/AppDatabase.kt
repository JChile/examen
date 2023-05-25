package com.example.danp_examen.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.danp_examen.model.PotholeDao
import com.example.danp_examen.model.SeverityDao
import com.example.danp_examen.model.UserDao

@Database(
    entities = [UserEntity::class, PotholeEntity::class, SeverityEntity::class],
    version = 3
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun potholeDao(): PotholeDao
    abstract fun severityDao(): SeverityDao

  

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-database-name"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance

                }
                return instance
            }
        }
    }
}

package com.example.danp_examen.entities

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.danp_examen.model.PotholeDao
import com.example.danp_examen.model.SeverityDao
import com.example.danp_examen.model.UserDao
import com.example.danp_examen.viewmodel.SeverityViewModel
import com.example.danp_examen.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

@Database(
    entities = [UserEntity::class, PotholeEntity::class, SeverityEntity::class],
    version = 5
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
                        .addCallback(roomCallback)
                        .build()
                    INSTANCE = instance

                }
                return instance
            }
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    val severityDao = INSTANCE?.severityDao()
                    severityDao?.addSeverity(SeverityEntity(sevName = "Grave"))
                    severityDao?.addSeverity(SeverityEntity(sevName = "Medio"))
                    severityDao?.addSeverity(SeverityEntity(sevName = "Leve"))
                }
            }
        }

    }
}

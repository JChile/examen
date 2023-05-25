package com.example.danp_examen.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.danp_examen.entities.SeverityEntity

@Dao
interface SeverityDao {
    @Query("SELECT * FROM Severity ORDER BY SevId")
    fun getAllSeverity() : LiveData<List<SeverityEntity>>

    @Insert
    suspend fun addSeverity(severityEntity: SeverityEntity)
}
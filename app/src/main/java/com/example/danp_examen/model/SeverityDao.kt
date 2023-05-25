package com.example.danp_examen.model

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.danp_examen.entities.PotholeAndSeverity
import com.example.danp_examen.entities.PotholeEntity
import com.example.danp_examen.entities.SeverityEntity
import com.example.danp_examen.entities.UserEntity

interface SeverityDao {
    @Insert
    suspend fun insertPothole(pothole: PotholeEntity)

    @Insert
    suspend fun insertSeverity(severityEntity: SeverityEntity)

    @Insert
    suspend fun addSeverity(severityEntity: SeverityEntity)

    @Transaction
    @Query("SELECT * FROM Pothole WHERE PotId = :potId")
    suspend fun getPotholeAndSeverity(potId: Int): List<PotholeAndSeverity>
}
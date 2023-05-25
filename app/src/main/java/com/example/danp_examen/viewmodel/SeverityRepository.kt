package com.example.danp_examen.viewmodel

import androidx.lifecycle.LiveData
import com.example.danp_examen.entities.SeverityEntity
import com.example.danp_examen.entities.UserEntity
import com.example.danp_examen.model.SeverityDao

class SeverityRepository(private val severityDao: SeverityDao) {

    val getAllUSeverity: LiveData<List<SeverityEntity>> = severityDao.getAllSeverity()

    suspend fun addSeverity(severity: SeverityEntity){
        severityDao.addSeverity(severity)
    }
}
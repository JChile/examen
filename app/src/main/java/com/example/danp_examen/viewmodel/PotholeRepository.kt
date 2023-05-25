package com.example.danp_examen.viewmodel

import androidx.lifecycle.LiveData
import com.example.danp_examen.entities.PotholeEntity
import com.example.danp_examen.entities.UserEntity
import com.example.danp_examen.model.PotholeDao
import com.example.danp_examen.model.UserDao

class PotholeRepository(private val potholeDao: PotholeDao) {

    val getAllPothole: LiveData<List<PotholeEntity>> = potholeDao.getAllPothole()

    suspend fun addPothole(pothole: PotholeEntity){
        potholeDao.addPothole(pothole)
    }
}
package com.example.danp_examen.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.danp_examen.entities.AppDatabase
import com.example.danp_examen.entities.PotholeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PotholeViewModel(application: Application): AndroidViewModel(application) {
    private val repository: PotholeRepository
    private val getAllPothole: LiveData<List<PotholeEntity>>

    init{
        val potholeDao = AppDatabase.getInstance(application).potholeDao()
        repository = PotholeRepository(potholeDao)
        getAllPothole = repository.getAllPothole
    }

    fun addPothole(pothole: PotholeEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.addPothole(pothole)
        }
    }
}
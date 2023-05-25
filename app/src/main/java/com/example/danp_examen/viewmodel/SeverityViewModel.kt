package com.example.danp_examen.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.danp_examen.entities.AppDatabase
import com.example.danp_examen.entities.SeverityEntity
import com.example.danp_examen.entities.UserEntity
import com.example.danp_examen.model.SeverityDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SeverityViewModel(application: Application): AndroidViewModel(application) {
    private val repository: SeverityRepository
    private val getAllSeverity: LiveData<List<SeverityEntity>>

    init{
        val severityDao = AppDatabase.getInstance(application).severityDao()
        repository = SeverityRepository(severityDao)
        getAllSeverity = repository.getAllUSeverity
    }

    fun addSeverity(severity: SeverityEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.addSeverity(severity)
        }
    }
}
package com.example.danp_examen.viewmodel

import androidx.lifecycle.LiveData
import com.example.danp_examen.entities.UserEntity
import com.example.danp_examen.model.UserDao

class UserRepository(private val userDao: UserDao) {

    val getAllUser: LiveData<List<UserEntity>> = userDao.getAllUser()

    suspend fun checkUser(email: String, password: String): Boolean {
        return userDao.checkUser(email, password)
    }

    suspend fun addUser(user: UserEntity){
        userDao.addUser(user)
    }

    suspend fun getUserName(userId: Int): String? {
        return userDao.getUserName(userId)
    }

    suspend fun getUserId(email: String, password: String): Int? {
        return userDao.getUserId(email, password)
    }
}
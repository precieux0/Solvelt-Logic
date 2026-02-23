package com.precieux.solvelt.logic.data.repositories

import com.precieux.solvelt.logic.data.dao.UserDao
import com.precieux.solvelt.logic.data.entities.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun getUserById(userId: String): User? {
        return userDao.getUserById(userId)
    }
    
    fun getUserByIdFlow(userId: String): Flow<User?> {
        return userDao.getUserByIdFlow(userId)
    }
    
    suspend fun getCurrentUser(): User? {
        return userDao.getCurrentUser()
    }
    
    fun getCurrentUserFlow(): Flow<User?> {
        return userDao.getCurrentUserFlow()
    }
    
    suspend fun saveUser(user: User) {
        userDao.insertUser(user)
    }
    
    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
    
    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
    
    suspend fun deleteUserById(userId: String) {
        userDao.deleteUserById(userId)
    }
    
    suspend fun incrementProblemsSolved(userId: String) {
        userDao.incrementProblemsSolved(userId)
    }
    
    suspend fun incrementCoursesCompleted(userId: String) {
        userDao.incrementCoursesCompleted(userId)
    }
    
    suspend fun addStudyTime(userId: String, minutes: Long) {
        userDao.addStudyTime(userId, minutes)
    }
    
    suspend fun updateLastLogin(userId: String) {
        userDao.updateLastLogin(userId)
    }
    
    suspend fun updatePreferredLanguage(userId: String, language: String) {
        userDao.updatePreferredLanguage(userId, language)
    }
    
    suspend fun updatePreferredTheme(userId: String, theme: String) {
        userDao.updatePreferredTheme(userId, theme)
    }
}
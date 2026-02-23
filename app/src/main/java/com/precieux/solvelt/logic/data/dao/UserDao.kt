package com.precieux.solvelt.logic.data.dao

import androidx.room.*
import com.precieux.solvelt.logic.data.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: String): User?
    
    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserByIdFlow(userId: String): Flow<User?>
    
    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getCurrentUser(): User?
    
    @Query("SELECT * FROM users LIMIT 1")
    fun getCurrentUserFlow(): Flow<User?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)
    
    @Update
    suspend fun updateUser(user: User)
    
    @Delete
    suspend fun deleteUser(user: User)
    
    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUserById(userId: String)
    
    @Query("UPDATE users SET problemsSolved = problemsSolved + 1 WHERE id = :userId")
    suspend fun incrementProblemsSolved(userId: String)
    
    @Query("UPDATE users SET coursesCompleted = coursesCompleted + 1 WHERE id = :userId")
    suspend fun incrementCoursesCompleted(userId: String)
    
    @Query("UPDATE users SET studyTimeMinutes = studyTimeMinutes + :minutes WHERE id = :userId")
    suspend fun addStudyTime(userId: String, minutes: Long)
    
    @Query("UPDATE users SET lastLoginAt = :timestamp WHERE id = :userId")
    suspend fun updateLastLogin(userId: String, timestamp: Long = System.currentTimeMillis())
    
    @Query("UPDATE users SET preferredLanguage = :language WHERE id = :userId")
    suspend fun updatePreferredLanguage(userId: String, language: String)
    
    @Query("UPDATE users SET preferredTheme = :theme WHERE id = :userId")
    suspend fun updatePreferredTheme(userId: String, theme: String)
}
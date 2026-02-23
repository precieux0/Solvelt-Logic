package com.precieux.solvelt.logic.data.dao

import androidx.room.*
import com.precieux.solvelt.logic.data.entities.Problem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProblemDao {
    @Query("SELECT * FROM problems WHERE userId = :userId ORDER BY createdAt DESC")
    suspend fun getProblemsByUser(userId: String): List<Problem>
    
    @Query("SELECT * FROM problems WHERE userId = :userId ORDER BY createdAt DESC")
    fun getProblemsByUserFlow(userId: String): Flow<List<Problem>>
    
    @Query("SELECT * FROM problems WHERE userId = :userId AND categoryId = :categoryId ORDER BY createdAt DESC")
    suspend fun getProblemsByCategory(userId: String, categoryId: String): List<Problem>
    
    @Query("SELECT * FROM problems WHERE id = :problemId")
    suspend fun getProblemById(problemId: String): Problem?
    
    @Query("SELECT * FROM problems WHERE userId = :userId AND isFavorite = 1 ORDER BY createdAt DESC")
    suspend fun getFavoriteProblems(userId: String): List<Problem>
    
    @Query("SELECT * FROM problems WHERE userId = :userId AND isFavorite = 1 ORDER BY createdAt DESC")
    fun getFavoriteProblemsFlow(userId: String): Flow<List<Problem>>
    
    @Query("SELECT * FROM problems WHERE userId = :userId AND isSynced = 0")
    suspend fun getUnsyncedProblems(userId: String): List<Problem>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProblem(problem: Problem)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProblems(problems: List<Problem>)
    
    @Update
    suspend fun updateProblem(problem: Problem)
    
    @Query("UPDATE problems SET isFavorite = :isFavorite WHERE id = :problemId")
    suspend fun updateFavoriteStatus(problemId: String, isFavorite: Boolean)
    
    @Query("UPDATE problems SET isSynced = 1 WHERE id = :problemId")
    suspend fun markAsSynced(problemId: String)
    
    @Delete
    suspend fun deleteProblem(problem: Problem)
    
    @Query("DELETE FROM problems WHERE id = :problemId")
    suspend fun deleteProblemById(problemId: String)
    
    @Query("DELETE FROM problems WHERE userId = :userId")
    suspend fun deleteAllUserProblems(userId: String)
    
    @Query("DELETE FROM problems WHERE userId = :userId AND createdAt < :timestamp")
    suspend fun deleteOldProblems(userId: String, timestamp: Long)
    
    @Query("SELECT COUNT(*) FROM problems WHERE userId = :userId")
    suspend fun getProblemCount(userId: String): Int
    
    @Query("SELECT COUNT(*) FROM problems WHERE userId = :userId AND isFavorite = 1")
    suspend fun getFavoriteCount(userId: String): Int
}
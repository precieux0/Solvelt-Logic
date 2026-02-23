package com.precieux.solvelt.logic.data.repositories

import com.precieux.solvelt.logic.data.dao.ProblemDao
import com.precieux.solvelt.logic.data.entities.Problem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProblemRepository @Inject constructor(
    private val problemDao: ProblemDao
) {
    suspend fun getProblemsByUser(userId: String): List<Problem> {
        return problemDao.getProblemsByUser(userId)
    }
    
    fun getProblemsByUserFlow(userId: String): Flow<List<Problem>> {
        return problemDao.getProblemsByUserFlow(userId)
    }
    
    suspend fun getProblemsByCategory(userId: String, categoryId: String): List<Problem> {
        return problemDao.getProblemsByCategory(userId, categoryId)
    }
    
    suspend fun getProblemById(problemId: String): Problem? {
        return problemDao.getProblemById(problemId)
    }
    
    suspend fun getFavoriteProblems(userId: String): List<Problem> {
        return problemDao.getFavoriteProblems(userId)
    }
    
    fun getFavoriteProblemsFlow(userId: String): Flow<List<Problem>> {
        return problemDao.getFavoriteProblemsFlow(userId)
    }
    
    suspend fun getUnsyncedProblems(userId: String): List<Problem> {
        return problemDao.getUnsyncedProblems(userId)
    }
    
    suspend fun saveProblem(problem: Problem) {
        problemDao.insertProblem(problem)
    }
    
    suspend fun saveProblems(problems: List<Problem>) {
        problemDao.insertProblems(problems)
    }
    
    suspend fun updateProblem(problem: Problem) {
        problemDao.updateProblem(problem)
    }
    
    suspend fun updateFavoriteStatus(problemId: String, isFavorite: Boolean) {
        problemDao.updateFavoriteStatus(problemId, isFavorite)
    }
    
    suspend fun markAsSynced(problemId: String) {
        problemDao.markAsSynced(problemId)
    }
    
    suspend fun deleteProblem(problem: Problem) {
        problemDao.deleteProblem(problem)
    }
    
    suspend fun deleteProblemById(problemId: String) {
        problemDao.deleteProblemById(problemId)
    }
    
    suspend fun deleteAllUserProblems(userId: String) {
        problemDao.deleteAllUserProblems(userId)
    }
    
    suspend fun deleteOldProblems(userId: String, timestamp: Long) {
        problemDao.deleteOldProblems(userId, timestamp)
    }
    
    suspend fun getProblemCount(userId: String): Int {
        return problemDao.getProblemCount(userId)
    }
    
    suspend fun getFavoriteCount(userId: String): Int {
        return problemDao.getFavoriteCount(userId)
    }
}
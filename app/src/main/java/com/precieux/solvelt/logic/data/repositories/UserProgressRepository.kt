package com.precieux.solvelt.logic.data.repositories

import com.precieux.solvelt.logic.data.dao.UserProgressDao
import com.precieux.solvelt.logic.data.entities.UserProgress
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProgressRepository @Inject constructor(
    private val userProgressDao: UserProgressDao
) {
    suspend fun getProgressByUser(userId: String): List<UserProgress> {
        return userProgressDao.getProgressByUser(userId)
    }
    
    fun getProgressByUserFlow(userId: String): Flow<List<UserProgress>> {
        return userProgressDao.getProgressByUserFlow(userId)
    }
    
    suspend fun getProgressForCourse(userId: String, courseId: String): UserProgress? {
        return userProgressDao.getProgressForCourse(userId, courseId)
    }
    
    fun getProgressForCourseFlow(userId: String, courseId: String): Flow<UserProgress?> {
        return userProgressDao.getProgressForCourseFlow(userId, courseId)
    }
    
    suspend fun saveProgress(progress: UserProgress) {
        userProgressDao.insertProgress(progress)
    }
    
    suspend fun updateProgress(progress: UserProgress) {
        userProgressDao.updateProgress(progress)
    }
    
    suspend fun updateCompletedLessons(progressId: String, lessonIds: List<String>) {
        userProgressDao.updateCompletedLessons(progressId, lessonIds)
    }
    
    suspend fun updateQuizScore(progressId: String, score: Int) {
        userProgressDao.updateQuizScore(progressId, score)
    }
    
    suspend fun addStudyTime(progressId: String, minutes: Long) {
        userProgressDao.addStudyTime(progressId, minutes)
    }
    
    suspend fun deleteProgress(progress: UserProgress) {
        userProgressDao.deleteProgress(progress)
    }
    
    suspend fun deleteAllUserProgress(userId: String) {
        userProgressDao.deleteAllUserProgress(userId)
    }
    
    suspend fun getProgressCount(userId: String): Int {
        return userProgressDao.getProgressCount(userId)
    }
}
package com.precieux.solvelt.logic.data.dao

import androidx.room.*
import com.precieux.solvelt.logic.data.entities.UserProgress
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProgressDao {
    @Query("SELECT * FROM user_progress WHERE userId = :userId ORDER BY lastAccessedAt DESC")
    suspend fun getProgressByUser(userId: String): List<UserProgress>
    
    @Query("SELECT * FROM user_progress WHERE userId = :userId ORDER BY lastAccessedAt DESC")
    fun getProgressByUserFlow(userId: String): Flow<List<UserProgress>>
    
    @Query("SELECT * FROM user_progress WHERE userId = :userId AND courseId = :courseId")
    suspend fun getProgressForCourse(userId: String, courseId: String): UserProgress?
    
    @Query("SELECT * FROM user_progress WHERE userId = :userId AND courseId = :courseId")
    fun getProgressForCourseFlow(userId: String, courseId: String): Flow<UserProgress?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: UserProgress)
    
    @Update
    suspend fun updateProgress(progress: UserProgress)
    
    @Query("UPDATE user_progress SET completedLessonIds = :lessonIds, lastAccessedAt = :timestamp WHERE id = :progressId")
    suspend fun updateCompletedLessons(progressId: String, lessonIds: List<String>, timestamp: Long = System.currentTimeMillis())
    
    @Query("UPDATE user_progress SET quizScore = :score, quizCompletedAt = :timestamp WHERE id = :progressId")
    suspend fun updateQuizScore(progressId: String, score: Int, timestamp: Long = System.currentTimeMillis())
    
    @Query("UPDATE user_progress SET totalStudyTimeMinutes = totalStudyTimeMinutes + :minutes WHERE id = :progressId")
    suspend fun addStudyTime(progressId: String, minutes: Long)
    
    @Delete
    suspend fun deleteProgress(progress: UserProgress)
    
    @Query("DELETE FROM user_progress WHERE userId = :userId")
    suspend fun deleteAllUserProgress(userId: String)
    
    @Query("SELECT COUNT(*) FROM user_progress WHERE userId = :userId")
    suspend fun getProgressCount(userId: String): Int
}
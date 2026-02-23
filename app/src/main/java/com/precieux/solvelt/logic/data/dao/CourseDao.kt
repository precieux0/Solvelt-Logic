package com.precieux.solvelt.logic.data.dao

import androidx.room.*
import com.precieux.solvelt.logic.data.entities.Course
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses WHERE isActive = 1 ORDER BY createdAt DESC")
    suspend fun getAllCourses(): List<Course>
    
    @Query("SELECT * FROM courses WHERE isActive = 1 ORDER BY createdAt DESC")
    fun getAllCoursesFlow(): Flow<List<Course>>
    
    @Query("SELECT * FROM courses WHERE categoryId = :categoryId AND isActive = 1 ORDER BY createdAt DESC")
    suspend fun getCoursesByCategory(categoryId: String): List<Course>
    
    @Query("SELECT * FROM courses WHERE categoryId = :categoryId AND isActive = 1 ORDER BY createdAt DESC")
    fun getCoursesByCategoryFlow(categoryId: String): Flow<List<Course>>
    
    @Query("SELECT * FROM courses WHERE id = :courseId")
    suspend fun getCourseById(courseId: String): Course?
    
    @Query("SELECT * FROM courses WHERE isDownloaded = 1")
    suspend fun getDownloadedCourses(): List<Course>
    
    @Query("SELECT * FROM courses WHERE isDownloaded = 1")
    fun getDownloadedCoursesFlow(): Flow<List<Course>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: Course)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourses(courses: List<Course>)
    
    @Update
    suspend fun updateCourse(course: Course)
    
    @Query("UPDATE courses SET isDownloaded = :isDownloaded WHERE id = :courseId")
    suspend fun updateDownloadStatus(courseId: String, isDownloaded: Boolean)
    
    @Delete
    suspend fun deleteCourse(course: Course)
    
    @Query("DELETE FROM courses")
    suspend fun deleteAllCourses()
    
    @Query("SELECT COUNT(*) FROM courses")
    suspend fun getCourseCount(): Int
}
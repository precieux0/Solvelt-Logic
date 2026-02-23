package com.precieux.solvelt.logic.data.repositories

import com.precieux.solvelt.logic.data.dao.CourseDao
import com.precieux.solvelt.logic.data.entities.Course
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseRepository @Inject constructor(
    private val courseDao: CourseDao
) {
    suspend fun getAllCourses(): List<Course> {
        return courseDao.getAllCourses()
    }
    
    fun getAllCoursesFlow(): Flow<List<Course>> {
        return courseDao.getAllCoursesFlow()
    }
    
    suspend fun getCoursesByCategory(categoryId: String): List<Course> {
        return courseDao.getCoursesByCategory(categoryId)
    }
    
    fun getCoursesByCategoryFlow(categoryId: String): Flow<List<Course>> {
        return courseDao.getCoursesByCategoryFlow(categoryId)
    }
    
    suspend fun getCourseById(courseId: String): Course? {
        return courseDao.getCourseById(courseId)
    }
    
    suspend fun getDownloadedCourses(): List<Course> {
        return courseDao.getDownloadedCourses()
    }
    
    fun getDownloadedCoursesFlow(): Flow<List<Course>> {
        return courseDao.getDownloadedCoursesFlow()
    }
    
    suspend fun saveCourse(course: Course) {
        courseDao.insertCourse(course)
    }
    
    suspend fun saveCourses(courses: List<Course>) {
        courseDao.insertCourses(courses)
    }
    
    suspend fun updateCourse(course: Course) {
        courseDao.updateCourse(course)
    }
    
    suspend fun updateDownloadStatus(courseId: String, isDownloaded: Boolean) {
        courseDao.updateDownloadStatus(courseId, isDownloaded)
    }
    
    suspend fun deleteCourse(course: Course) {
        courseDao.deleteCourse(course)
    }
    
    suspend fun deleteAllCourses() {
        courseDao.deleteAllCourses()
    }
    
    suspend fun getCourseCount(): Int {
        return courseDao.getCourseCount()
    }
}
package com.precieux.solvelt.logic.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_progress")
data class UserProgress(
    @PrimaryKey
    val id: String,
    val userId: String,
    val courseId: String,
    val completedLessonIds: List<String> = emptyList(),
    val quizScore: Int? = null,
    val quizCompletedAt: Long? = null,
    val lastAccessedAt: Long = System.currentTimeMillis(),
    val totalStudyTimeMinutes: Long = 0
) : Parcelable {
    fun getProgressPercentage(totalLessons: Int): Int {
        return if (totalLessons > 0) (completedLessonIds.size * 100 / totalLessons) else 0
    }
    
    fun isCourseCompleted(totalLessons: Int): Boolean {
        return completedLessonIds.size >= totalLessons
    }
}
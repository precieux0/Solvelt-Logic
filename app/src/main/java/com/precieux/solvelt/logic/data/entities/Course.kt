package com.precieux.solvelt.logic.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "courses",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("categoryId")]
)
data class Course(
    @PrimaryKey
    val id: String,
    val categoryId: String,
    val title: String,
    val titleFr: String,
    val description: String,
    val descriptionFr: String,
    val thumbnailUrl: String?,
    val durationMinutes: Int,
    val difficulty: DifficultyLevel,
    val lessons: List<Lesson>,
    val quiz: Quiz?,
    val isDownloaded: Boolean = false,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable {
    fun getLocalizedTitle(language: String): String {
        return if (language == "fr") titleFr else title
    }
    
    fun getLocalizedDescription(language: String): String {
        return if (language == "fr") descriptionFr else description
    }
    
    fun getLessonCount(): Int = lessons.size
    
    fun getCompletedLessonsCount(userProgress: UserProgress?): Int {
        return userProgress?.completedLessonIds?.size ?: 0
    }
    
    fun getProgressPercentage(userProgress: UserProgress?): Int {
        val total = lessons.size
        val completed = getCompletedLessonsCount(userProgress)
        return if (total > 0) (completed * 100 / total) else 0
    }
}

@Parcelize
enum class DifficultyLevel : Parcelable {
    BEGINNER, INTERMEDIATE, ADVANCED
}

@Parcelize
data class Lesson(
    val id: String,
    val title: String,
    val titleFr: String,
    val content: String,
    val contentFr: String,
    val codeExample: String? = null,
    val imageUrls: List<String> = emptyList(),
    val order: Int
) : Parcelable {
    fun getLocalizedTitle(language: String): String {
        return if (language == "fr") titleFr else title
    }
    
    fun getLocalizedContent(language: String): String {
        return if (language == "fr") contentFr else content
    }
}

@Parcelize
data class Quiz(
    val id: String,
    val title: String,
    val titleFr: String,
    val questions: List<Question>
) : Parcelable {
    fun getLocalizedTitle(language: String): String {
        return if (language == "fr") titleFr else title
    }
}

@Parcelize
data class Question(
    val id: String,
    val text: String,
    val textFr: String,
    val options: List<QuizOption>,
    val correctOptionId: String,
    val explanation: String,
    val explanationFr: String
) : Parcelable {
    fun getLocalizedText(language: String): String {
        return if (language == "fr") textFr else text
    }
    
    fun getLocalizedExplanation(language: String): String {
        return if (language == "fr") explanationFr else explanation
    }
}

@Parcelize
data class QuizOption(
    val id: String,
    val text: String,
    val textFr: String
) : Parcelable {
    fun getLocalizedText(language: String): String {
        return if (language == "fr") textFr else text
    }
}
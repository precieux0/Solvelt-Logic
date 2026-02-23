package com.precieux.solvelt.logic.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "problems",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("categoryId"), Index("userId")]
)
data class Problem(
    @PrimaryKey
    val id: String,
    val userId: String,
    val categoryId: String,
    val subcategory: String,
    val input: String,
    val solution: String,
    val steps: List<SolutionStep>,
    val relatedConcepts: List<String>,
    val createdAt: Long = System.currentTimeMillis(),
    val isFavorite: Boolean = false,
    val isSynced: Boolean = false
) : Parcelable {
    fun getFormattedDate(): String {
        val formatter = java.text.SimpleDateFormat("MMM dd, yyyy HH:mm", java.util.Locale.getDefault())
        return formatter.format(java.util.Date(createdAt))
    }
}

@Parcelize
data class SolutionStep(
    val stepNumber: Int,
    val description: String,
    val formula: String? = null,
    val result: String? = null
) : Parcelable
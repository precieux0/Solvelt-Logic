package com.precieux.solvelt.logic.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "favorites",
    indices = [Index("userId"), Index("itemId", "itemType")]
)
data class Favorite(
    @PrimaryKey
    val id: String,
    val userId: String,
    val itemId: String,
    val itemType: FavoriteType,
    val title: String,
    val subtitle: String?,
    val categoryId: String?,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable {
    fun getFormattedDate(): String {
        val formatter = java.text.SimpleDateFormat("MMM dd, yyyy", java.util.Locale.getDefault())
        return formatter.format(java.util.Date(createdAt))
    }
}

@Parcelize
enum class FavoriteType : Parcelable {
    PROBLEM, COURSE
}
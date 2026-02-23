package com.precieux.solvelt.logic.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String,
    val email: String,
    val displayName: String?,
    val photoUrl: String?,
    val isGuest: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val lastLoginAt: Long = System.currentTimeMillis(),
    val problemsSolved: Int = 0,
    val coursesCompleted: Int = 0,
    val studyTimeMinutes: Long = 0,
    val preferredLanguage: String = "en",
    val preferredTheme: String = "system"
) : Parcelable {
    fun getInitials(): String {
        return displayName?.split(" ")?.mapNotNull { it.firstOrNull()?.uppercaseChar() }?.joinToString("")?.take(2)
            ?: email.take(2).uppercase()
    }
}
package com.precieux.solvelt.logic.data.dao

import androidx.room.*
import com.precieux.solvelt.logic.data.entities.Favorite
import com.precieux.solvelt.logic.data.entities.FavoriteType
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites WHERE userId = :userId ORDER BY createdAt DESC")
    suspend fun getFavoritesByUser(userId: String): List<Favorite>
    
    @Query("SELECT * FROM favorites WHERE userId = :userId ORDER BY createdAt DESC")
    fun getFavoritesByUserFlow(userId: String): Flow<List<Favorite>>
    
    @Query("SELECT * FROM favorites WHERE userId = :userId AND itemType = :type ORDER BY createdAt DESC")
    suspend fun getFavoritesByType(userId: String, type: FavoriteType): List<Favorite>
    
    @Query("SELECT * FROM favorites WHERE userId = :userId AND itemId = :itemId AND itemType = :type")
    suspend fun getFavorite(userId: String, itemId: String, type: FavoriteType): Favorite?
    
    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE userId = :userId AND itemId = :itemId AND itemType = :type)")
    suspend fun isFavorite(userId: String, itemId: String, type: FavoriteType): Boolean
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)
    
    @Delete
    suspend fun deleteFavorite(favorite: Favorite)
    
    @Query("DELETE FROM favorites WHERE userId = :userId AND itemId = :itemId AND itemType = :type")
    suspend fun deleteFavoriteByItem(userId: String, itemId: String, type: FavoriteType)
    
    @Query("DELETE FROM favorites WHERE userId = :userId")
    suspend fun deleteAllUserFavorites(userId: String)
    
    @Query("SELECT COUNT(*) FROM favorites WHERE userId = :userId")
    suspend fun getFavoriteCount(userId: String): Int
}
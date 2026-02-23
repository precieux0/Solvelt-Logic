package com.precieux.solvelt.logic.data.repositories

import com.precieux.solvelt.logic.data.dao.FavoriteDao
import com.precieux.solvelt.logic.data.entities.Favorite
import com.precieux.solvelt.logic.data.entities.FavoriteType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao
) {
    suspend fun getFavoritesByUser(userId: String): List<Favorite> {
        return favoriteDao.getFavoritesByUser(userId)
    }
    
    fun getFavoritesByUserFlow(userId: String): Flow<List<Favorite>> {
        return favoriteDao.getFavoritesByUserFlow(userId)
    }
    
    suspend fun getFavoritesByType(userId: String, type: FavoriteType): List<Favorite> {
        return favoriteDao.getFavoritesByType(userId, type)
    }
    
    suspend fun getFavorite(userId: String, itemId: String, type: FavoriteType): Favorite? {
        return favoriteDao.getFavorite(userId, itemId, type)
    }
    
    suspend fun isFavorite(userId: String, itemId: String, type: FavoriteType): Boolean {
        return favoriteDao.isFavorite(userId, itemId, type)
    }
    
    suspend fun addFavorite(favorite: Favorite) {
        favoriteDao.insertFavorite(favorite)
    }
    
    suspend fun removeFavorite(favorite: Favorite) {
        favoriteDao.deleteFavorite(favorite)
    }
    
    suspend fun removeFavoriteByItem(userId: String, itemId: String, type: FavoriteType) {
        favoriteDao.deleteFavoriteByItem(userId, itemId, type)
    }
    
    suspend fun toggleFavorite(userId: String, itemId: String, type: FavoriteType, title: String, subtitle: String?, categoryId: String?) {
        val isCurrentlyFavorite = isFavorite(userId, itemId, type)
        if (isCurrentlyFavorite) {
            removeFavoriteByItem(userId, itemId, type)
        } else {
            val favorite = Favorite(
                id = "${userId}_${type.name}_$itemId",
                userId = userId,
                itemId = itemId,
                itemType = type,
                title = title,
                subtitle = subtitle,
                categoryId = categoryId
            )
            addFavorite(favorite)
        }
    }
    
    suspend fun deleteAllUserFavorites(userId: String) {
        favoriteDao.deleteAllUserFavorites(userId)
    }
    
    suspend fun getFavoriteCount(userId: String): Int {
        return favoriteDao.getFavoriteCount(userId)
    }
}
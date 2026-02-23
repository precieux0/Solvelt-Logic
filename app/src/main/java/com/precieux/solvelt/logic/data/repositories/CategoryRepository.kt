package com.precieux.solvelt.logic.data.repositories

import com.precieux.solvelt.logic.data.dao.CategoryDao
import com.precieux.solvelt.logic.data.entities.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) {
    suspend fun getAllCategories(): List<Category> {
        return categoryDao.getAllCategories()
    }
    
    fun getAllCategoriesFlow(): Flow<List<Category>> {
        return categoryDao.getAllCategoriesFlow()
    }
    
    suspend fun getCategoryById(categoryId: String): Category? {
        return categoryDao.getCategoryById(categoryId)
    }
    
    suspend fun saveCategory(category: Category) {
        categoryDao.insertCategory(category)
    }
    
    suspend fun saveCategories(categories: List<Category>) {
        categoryDao.insertCategories(categories)
    }
    
    suspend fun updateCategory(category: Category) {
        categoryDao.updateCategory(category)
    }
    
    suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category)
    }
    
    suspend fun deleteAllCategories() {
        categoryDao.deleteAllCategories()
    }
    
    suspend fun getCategoryCount(): Int {
        return categoryDao.getCategoryCount()
    }
    
    suspend fun initializeDefaultCategories() {
        if (getCategoryCount() == 0) {
            saveCategories(Category.getDefaultCategories())
        }
    }
}
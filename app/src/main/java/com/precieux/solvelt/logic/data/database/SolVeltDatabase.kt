package com.precieux.solvelt.logic.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.precieux.solvelt.logic.data.dao.*
import com.precieux.solvelt.logic.data.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        User::class,
        Category::class,
        Problem::class,
        Course::class,
        UserProgress::class,
        Favorite::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class SolVeltDatabase : RoomDatabase() {
    
    abstract fun userDao(): UserDao
    abstract fun categoryDao(): CategoryDao
    abstract fun problemDao(): ProblemDao
    abstract fun courseDao(): CourseDao
    abstract fun userProgressDao(): UserProgressDao
    abstract fun favoriteDao(): FavoriteDao
    
    companion object {
        @Volatile
        private var INSTANCE: SolVeltDatabase? = null
        
        fun getDatabase(context: Context): SolVeltDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SolVeltDatabase::class.java,
                    "solvelt_database"
                )
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
    
    private class DatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database.categoryDao())
                }
            }
        }
        
        private suspend fun populateDatabase(categoryDao: CategoryDao) {
            // Insert default categories
            categoryDao.insertCategories(Category.getDefaultCategories())
        }
    }
}
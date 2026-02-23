package com.precieux.solvelt.logic.di

import android.content.Context
import com.precieux.solvelt.logic.auth.AuthManager
import com.precieux.solvelt.logic.data.database.SolVeltDatabase
import com.precieux.solvelt.logic.data.repositories.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SolVeltDatabase {
        return SolVeltDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideUserRepository(database: SolVeltDatabase): UserRepository {
        return UserRepository(database.userDao())
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(database: SolVeltDatabase): CategoryRepository {
        return CategoryRepository(database.categoryDao())
    }

    @Provides
    @Singleton
    fun provideProblemRepository(database: SolVeltDatabase): ProblemRepository {
        return ProblemRepository(database.problemDao())
    }

    @Provides
    @Singleton
    fun provideCourseRepository(database: SolVeltDatabase): CourseRepository {
        return CourseRepository(database.courseDao())
    }

    @Provides
    @Singleton
    fun provideUserProgressRepository(database: SolVeltDatabase): UserProgressRepository {
        return UserProgressRepository(database.userProgressDao())
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(database: SolVeltDatabase): FavoriteRepository {
        return FavoriteRepository(database.favoriteDao())
    }

    @Provides
    @Singleton
    fun provideAuthManager(@ApplicationContext context: Context): AuthManager {
        return AuthManager(context)
    }
}
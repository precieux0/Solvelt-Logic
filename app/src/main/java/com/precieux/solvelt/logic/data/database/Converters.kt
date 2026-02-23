package com.precieux.solvelt.logic.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.precieux.solvelt.logic.data.entities.*

class Converters {
    private val gson = Gson()
    
    // List<String> converters
    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return value?.let { gson.toJson(it) }
    }
    
    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.let {
            val type = object : TypeToken<List<String>>() {}.type
            gson.fromJson(it, type)
        }
    }
    
    // List<SolutionStep> converters
    @TypeConverter
    fun fromSolutionStepList(value: List<SolutionStep>?): String? {
        return value?.let { gson.toJson(it) }
    }
    
    @TypeConverter
    fun toSolutionStepList(value: String?): List<SolutionStep>? {
        return value?.let {
            val type = object : TypeToken<List<SolutionStep>>() {}.type
            gson.fromJson(it, type)
        }
    }
    
    // List<Lesson> converters
    @TypeConverter
    fun fromLessonList(value: List<Lesson>?): String? {
        return value?.let { gson.toJson(it) }
    }
    
    @TypeConverter
    fun toLessonList(value: String?): List<Lesson>? {
        return value?.let {
            val type = object : TypeToken<List<Lesson>>() {}.type
            gson.fromJson(it, type)
        }
    }
    
    // Quiz converter
    @TypeConverter
    fun fromQuiz(value: Quiz?): String? {
        return value?.let { gson.toJson(it) }
    }
    
    @TypeConverter
    fun toQuiz(value: String?): Quiz? {
        return value?.let {
            val type = object : TypeToken<Quiz>() {}.type
            gson.fromJson(it, type)
        }
    }
    
    // DifficultyLevel converter
    @TypeConverter
    fun fromDifficultyLevel(value: DifficultyLevel?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toDifficultyLevel(value: String?): DifficultyLevel? {
        return value?.let { DifficultyLevel.valueOf(it) }
    }
    
    // FavoriteType converter
    @TypeConverter
    fun fromFavoriteType(value: FavoriteType?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toFavoriteType(value: String?): FavoriteType? {
        return value?.let { FavoriteType.valueOf(it) }
    }
}
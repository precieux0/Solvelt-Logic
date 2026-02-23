package com.precieux.solvelt.logic.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.precieux.solvelt.logic.data.entities.Category
import com.precieux.solvelt.logic.data.entities.Course
import com.precieux.solvelt.logic.data.entities.User
import com.precieux.solvelt.logic.data.repositories.CategoryRepository
import com.precieux.solvelt.logic.data.repositories.CourseRepository
import com.precieux.solvelt.logic.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val courseRepository: CourseRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    private val _featuredCourses = MutableLiveData<List<Course>>()
    val featuredCourses: LiveData<List<Course>> = _featuredCourses

    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> = _currentUser

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Load categories
                categoryRepository.getAllCategoriesFlow().collectLatest { categories ->
                    _categories.value = categories
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }

        viewModelScope.launch {
            try {
                // Load featured courses
                courseRepository.getAllCoursesFlow().collectLatest { courses ->
                    _featuredCourses.value = courses.take(5)
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }

        viewModelScope.launch {
            // Load current user
            userRepository.getCurrentUserFlow().collectLatest { user ->
                _currentUser.value = user
            }
        }
    }

    fun refreshData() {
        loadData()
    }

    fun getGreeting(): String {
        val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 5..11 -> "Good morning"
            in 12..16 -> "Good afternoon"
            in 17..21 -> "Good evening"
            else -> "Good night"
        }
    }

    fun getUserDisplayName(): String {
        return _currentUser.value?.displayName?.split(" ")?.firstOrNull()
            ?: _currentUser.value?.getInitials()
            ?: "Guest"
    }
}
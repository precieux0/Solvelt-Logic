package com.precieux.solvelt.logic.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.precieux.solvelt.logic.data.entities.*
import com.precieux.solvelt.logic.data.repositories.*
import com.precieux.solvelt.logic.solvers.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ProblemSolverViewModel @Inject constructor(
    private val problemRepository: ProblemRepository,
    private val userRepository: UserRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _solution = MutableLiveData<SolutionResult?>(null)
    val solution: LiveData<SolutionResult?> = _solution

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    private val _isFavorite = MutableLiveData(false)
    val isFavorite: LiveData<Boolean> = _isFavorite

    private var currentProblem: Problem? = null
    private var currentUser: User? = null

    init {
        viewModelScope.launch {
            currentUser = userRepository.getCurrentUser()
        }
    }

    fun solveProblem(input: String, categoryId: String, subcategory: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val solver = getSolverForCategory(categoryId)
                val result = solver.solve(input, subcategory)

                if (result.isSuccess) {
                    val solution = result.getOrThrow()
                    _solution.value = solution

                    // Save problem to history
                    saveProblem(input, solution, categoryId, subcategory)
                } else {
                    _error.value = result.exceptionOrNull()?.message ?: "Failed to solve problem"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun getSolverForCategory(categoryId: String): ProblemSolver {
        return when (categoryId) {
            "mathematics" -> MathSolver()
            "physics" -> PhysicsSolver()
            "chemistry" -> ChemistrySolver()
            "technical_drawing" -> TechnicalDrawingSolver()
            else -> GeneralScienceSolver()
        }
    }

    private suspend fun saveProblem(input: String, solution: SolutionResult, categoryId: String, subcategory: String) {
        val user = currentUser ?: return

        val problem = Problem(
            id = UUID.randomUUID().toString(),
            userId = user.id,
            categoryId = categoryId,
            subcategory = subcategory,
            input = input,
            solution = solution.finalAnswer,
            steps = solution.steps,
            relatedConcepts = solution.relatedConcepts
        )

        problemRepository.saveProblem(problem)
        currentProblem = problem

        // Update user statistics
        userRepository.incrementProblemsSolved(user.id)

        // Check if this problem is favorited
        checkFavoriteStatus(problem.id)
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            val problem = currentProblem ?: return@launch
            val user = currentUser ?: return@launch

            favoriteRepository.toggleFavorite(
                userId = user.id,
                itemId = problem.id,
                type = FavoriteType.PROBLEM,
                title = problem.input.take(50),
                subtitle = problem.solution.take(100),
                categoryId = problem.categoryId
            )

            val newFavoriteStatus = !_isFavorite.value!!
            _isFavorite.value = newFavoriteStatus
            problemRepository.updateFavoriteStatus(problem.id, newFavoriteStatus)
        }
    }

    private suspend fun checkFavoriteStatus(problemId: String) {
        val user = currentUser ?: return
        _isFavorite.value = favoriteRepository.isFavorite(user.id, problemId, FavoriteType.PROBLEM)
    }

    fun clearSolution() {
        _solution.value = null
        currentProblem = null
        _isFavorite.value = false
    }

    fun shareSolution(): String {
        val sol = _solution.value ?: return ""
        return buildString {
            appendLine("Problem: ${currentProblem?.input}")
            appendLine()
            appendLine("Solution:")
            sol.steps.forEach { step ->
                appendLine("${step.stepNumber}. ${step.description}")
                step.formula?.let { appendLine("   Formula: $it") }
                step.result?.let { appendLine("   Result: $it") }
            }
            appendLine()
            appendLine("Final Answer: ${sol.finalAnswer}")
            appendLine()
            appendLine("Powered by SolVelt-Logic")
        }
    }

    fun clearError() {
        _error.value = null
    }
}
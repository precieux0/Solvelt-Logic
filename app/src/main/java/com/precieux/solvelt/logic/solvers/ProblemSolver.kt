package com.precieux.solvelt.logic.solvers

import com.precieux.solvelt.logic.data.entities.SolutionStep

data class SolutionResult(
    val steps: List<SolutionStep>,
    val finalAnswer: String,
    val relatedConcepts: List<String>,
    val confidence: Float = 1.0f
)

interface ProblemSolver {
    suspend fun solve(input: String, subcategory: String): Result<SolutionResult>
}
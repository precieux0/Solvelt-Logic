package com.precieux.solvelt.logic.solvers

import com.precieux.solvelt.logic.data.entities.SolutionStep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.*

class TechnicalDrawingSolver : ProblemSolver {

    override suspend fun solve(input: String, subcategory: String): Result<SolutionResult> {
        return withContext(Dispatchers.Default) {
            try {
                val result = when (subcategory.lowercase()) {
                    "projection" -> solveProjection(input)
                    "dimensioning" -> solveDimensioning(input)
                    "section" -> solveSectionViews(input)
                    "isometric" -> solveIsometric(input)
                    else -> solveGeneralDrawing(input)
                }
                Result.success(result)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    private fun solveProjection(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        
        steps.add(SolutionStep(
            stepNumber = 1,
            description = "Projection problem detected",
            formula = input
        ))
        
        return SolutionResult(
            steps = steps,
            finalAnswer = "Projection solver - Advanced feature coming soon",
            relatedConcepts = listOf("Orthographic Projection", "First Angle", "Third Angle", "Views")
        )
    }

    private fun solveDimensioning(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        
        // Scale calculation
        if (input.contains("scale") || input.contains("ratio")) {
            return solveScaleCalculation(input, steps)
        }
        
        steps.add(SolutionStep(
            stepNumber = 1,
            description = "Dimensioning problem detected",
            formula = input
        ))
        
        return SolutionResult(
            steps = steps,
            finalAnswer = "Dimensioning solver - Please specify scale calculation or tolerance analysis",
            relatedConcepts = listOf("Dimensioning", "Tolerances", "Scales", "Measurements")
        )
    }

    private fun solveScaleCalculation(input: String, steps: MutableList<SolutionStep>): SolutionResult {
        val numbers = extractNumbers(input)
        
        return when {
            numbers.size >= 2 -> {
                val actualSize = numbers[0]
                val drawingSize = numbers[1]
                
                steps.add(SolutionStep(
                    stepNumber = 1,
                    description = "Identify given values",
                    formula = "Actual size = $actualSize mm, Drawing size = $drawingSize mm"
                ))
                
                steps.add(SolutionStep(
                    stepNumber = 2,
                    description = "Calculate the scale ratio",
                    formula = "Scale = Drawing size / Actual size"
                ))
                
                val scale = drawingSize / actualSize
                val scaleRatio = simplifyRatio(drawingSize, actualSize)
                
                steps.add(SolutionStep(
                    stepNumber = 3,
                    description = "Simplified scale representation",
                    formula = "Scale = 1:${(1/scale).toInt()}",
                    result = scale.toString()
                ))
                
                SolutionResult(
                    steps = steps,
                    finalAnswer = "Scale = $scaleRatio or 1:${(1/scale).toInt()}",
                    relatedConcepts = listOf("Scale", "Ratio", "Technical Drawing", "Measurement")
                )
            }
            numbers.size == 1 && input.contains("1:") -> {
                val scale = numbers[0]
                val actual = 100.0 // Example
                val drawing = actual / scale
                
                steps.add(SolutionStep(
                    stepNumber = 1,
                    description = "Scale identified",
                    formula = "Scale = 1:$scale"
                ))
                
                steps.add(SolutionStep(
                    stepNumber = 2,
                    description = "Calculate drawing size from actual size",
                    formula = "Drawing size = Actual size / Scale"
                ))
                
                steps.add(SolutionStep(
                    stepNumber = 3,
                    description = "Example calculation",
                    formula = "If actual = $actual mm, drawing = ${String.format("%.2f", drawing)} mm",
                    result = drawing.toString()
                ))
                
                SolutionResult(
                    steps = steps,
                    finalAnswer = "At scale 1:$scale, ${String.format("%.2f", actual)} mm actual = ${String.format("%.2f", drawing)} mm on drawing",
                    relatedConcepts = listOf("Scale", "Reduction", "Enlargement")
                )
            }
            else -> SolutionResult(
                steps = steps,
                finalAnswer = "Please provide actual size and drawing size, or specify scale ratio",
                relatedConcepts = listOf("Scale Calculation")
            )
        }
    }

    private fun solveSectionViews(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        
        steps.add(SolutionStep(
            stepNumber = 1,
            description = "Section view problem detected",
            formula = input
        ))
        
        return SolutionResult(
            steps = steps,
            finalAnswer = "Section views solver - Advanced feature coming soon",
            relatedConcepts = listOf("Section Views", "Cutting Planes", "Hatching", "Cross-Section")
        )
    }

    private fun solveIsometric(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        
        // Isometric scale calculation
        if (input.contains("scale") || input.contains("length")) {
            val numbers = extractNumbers(input)
            
            if (numbers.isNotEmpty()) {
                val trueLength = numbers[0]
                
                steps.add(SolutionStep(
                    stepNumber = 1,
                    description = "Isometric projection scale calculation",
                    formula = "True length = $trueLength mm"
                ))
                
                steps.add(SolutionStep(
                    stepNumber = 2,
                    description = "Isometric scale factor",
                    formula = "Isometric scale = √(2/3) ≈ 0.816"
                ))
                
                val isometricLength = trueLength * 0.816
                
                steps.add(SolutionStep(
                    stepNumber = 3,
                    description = "Calculate isometric length",
                    formula = "Isometric length = $trueLength × 0.816 = ${String.format("%.2f", isometricLength)} mm",
                    result = isometricLength.toString()
                ))
                
                return SolutionResult(
                    steps = steps,
                    finalAnswer = "Isometric length = ${String.format("%.2f", isometricLength)} mm",
                    relatedConcepts = listOf("Isometric Projection", "Isometric Scale", "3D Drawing")
                )
            }
        }
        
        steps.add(SolutionStep(
            stepNumber = 1,
            description = "Isometric drawing problem detected",
            formula = input
        ))
        
        return SolutionResult(
            steps = steps,
            finalAnswer = "Isometric solver - Please provide length for scale calculation",
            relatedConcepts = listOf("Isometric Drawing", "Axonometric", "3D Representation")
        )
    }

    private fun solveGeneralDrawing(input: String): SolutionResult {
        return SolutionResult(
            steps = listOf(SolutionStep(1, "Technical drawing problem detected", input)),
            finalAnswer = "Please specify the drawing category (projection, dimensioning, section, isometric)",
            relatedConcepts = listOf("Technical Drawing", "Engineering Graphics", "CAD")
        )
    }

    private fun extractNumbers(input: String): List<Double> {
        return "-?\\d+\\.?\\d*".toRegex().findAll(input)
            .mapNotNull { it.value.toDoubleOrNull() }
            .toList()
    }

    private fun simplifyRatio(a: Double, b: Double): String {
        val gcd = gcd(a.toInt(), b.toInt())
        return "${(a / gcd).toInt()}:${(b / gcd).toInt()}"
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }
}
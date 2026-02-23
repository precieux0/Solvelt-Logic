package com.precieux.solvelt.logic.solvers

import com.precieux.solvelt.logic.data.entities.SolutionStep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.*

class MathSolver : ProblemSolver {

    override suspend fun solve(input: String, subcategory: String): Result<SolutionResult> {
        return withContext(Dispatchers.Default) {
            try {
                val result = when (subcategory.lowercase()) {
                    "algebra" -> solveAlgebra(input)
                    "geometry" -> solveGeometry(input)
                    "calculus" -> solveCalculus(input)
                    "trigonometry" -> solveTrigonometry(input)
                    "statistics" -> solveStatistics(input)
                    else -> solveGeneralMath(input)
                }
                Result.success(result)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    private fun solveAlgebra(input: String): SolutionResult {
        // Parse and solve linear equations like "2x + 5 = 15"
        val steps = mutableListOf<SolutionStep>()
        
        // Example: 2x + 5 = 15
        if (input.contains("x") && input.contains("=")) {
            val parts = input.split("=")
            val leftSide = parts[0].trim()
            val rightSide = parts[1].trim().toDoubleOrNull() ?: 0.0
            
            steps.add(SolutionStep(
                stepNumber = 1,
                description = "Start with the equation: $input",
                formula = input
            ))
            
            steps.add(SolutionStep(
                stepNumber = 2,
                description = "Isolate the variable term",
                formula = "$leftSide = $rightSide"
            ))
            
            // Simple linear equation solver
            val coefficient = extractCoefficient(leftSide)
            val constant = extractConstant(leftSide)
            
            steps.add(SolutionStep(
                stepNumber = 3,
                description = "Subtract $constant from both sides",
                formula = "${coefficient}x = ${rightSide - constant}"
            ))
            
            val x = (rightSide - constant) / coefficient
            
            steps.add(SolutionStep(
                stepNumber = 4,
                description = "Divide both sides by $coefficient",
                formula = "x = $x",
                result = x.toString()
            ))
            
            return SolutionResult(
                steps = steps,
                finalAnswer = "x = $x",
                relatedConcepts = listOf("Linear Equations", "Algebraic Manipulation", "Isolating Variables")
            )
        }
        
        // Quadratic equations like "x^2 + 5x + 6 = 0"
        if (input.contains("x^2") || input.contains("x²")) {
            return solveQuadratic(input, steps)
        }
        
        return solveGeneralMath(input)
    }

    private fun solveQuadratic(input: String, steps: MutableList<SolutionStep>): SolutionResult {
        // Parse ax² + bx + c = 0
        val (a, b, c) = parseQuadraticCoefficients(input)
        
        steps.add(SolutionStep(
            stepNumber = 1,
            description = "Identify coefficients: a = $a, b = $b, c = $c",
            formula = "ax² + bx + c = 0"
        ))
        
        steps.add(SolutionStep(
            stepNumber = 2,
            description = "Calculate the discriminant (Δ = b² - 4ac)",
            formula = "Δ = ${b}² - 4(${a})(${c})"
        ))
        
        val discriminant = b * b - 4 * a * c
        
        steps.add(SolutionStep(
            stepNumber = 3,
            description = "Discriminant value",
            formula = "Δ = $discriminant",
            result = discriminant.toString()
        ))
        
        return when {
            discriminant > 0 -> {
                val x1 = (-b + sqrt(discriminant)) / (2 * a)
                val x2 = (-b - sqrt(discriminant)) / (2 * a)
                
                steps.add(SolutionStep(
                    stepNumber = 4,
                    description = "Two real solutions (Δ > 0)",
                    formula = "x = (-b ± √Δ) / 2a"
                ))
                
                SolutionResult(
                    steps = steps,
                    finalAnswer = "x₁ = ${String.format("%.4f", x1)}, x₂ = ${String.format("%.4f", x2)}",
                    relatedConcepts = listOf("Quadratic Equations", "Discriminant", "Quadratic Formula")
                )
            }
            discriminant == 0.0 -> {
                val x = -b / (2 * a)
                
                steps.add(SolutionStep(
                    stepNumber = 4,
                    description = "One real solution (Δ = 0)",
                    formula = "x = -b / 2a"
                ))
                
                SolutionResult(
                    steps = steps,
                    finalAnswer = "x = ${String.format("%.4f", x)}",
                    relatedConcepts = listOf("Quadratic Equations", "Double Root")
                )
            }
            else -> {
                val realPart = -b / (2 * a)
                val imaginaryPart = sqrt(-discriminant) / (2 * a)
                
                steps.add(SolutionStep(
                    stepNumber = 4,
                    description = "Complex solutions (Δ < 0)",
                    formula = "x = (-b ± i√|Δ|) / 2a"
                ))
                
                SolutionResult(
                    steps = steps,
                    finalAnswer = "x = ${String.format("%.4f", realPart)} ± ${String.format("%.4f", imaginaryPart)}i",
                    relatedConcepts = listOf("Quadratic Equations", "Complex Numbers", "Discriminant")
                )
            }
        }
    }

    private fun solveGeometry(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        
        // Area of circle
        if (input.contains("circle") && (input.contains("area") || input.contains("surface"))) {
            val radius = extractNumber(input) ?: 1.0
            
            steps.add(SolutionStep(
                stepNumber = 1,
                description = "Identify the shape and given value",
                formula = "Circle with radius r = $radius"
            ))
            
            steps.add(SolutionStep(
                stepNumber = 2,
                description = "Apply the area formula for a circle",
                formula = "A = πr²"
            ))
            
            val area = PI * radius * radius
            
            steps.add(SolutionStep(
                stepNumber = 3,
                description = "Calculate the area",
                formula = "A = π × ${radius}² = ${String.format("%.4f", area)}",
                result = area.toString()
            ))
            
            return SolutionResult(
                steps = steps,
                finalAnswer = "Area = ${String.format("%.4f", area)} square units",
                relatedConcepts = listOf("Circle", "Area", "Pi", "Radius")
            )
        }
        
        // Pythagorean theorem
        if (input.contains("triangle") || input.contains("hypotenuse") || input.contains("pythagorean")) {
            return solvePythagorean(input, steps)
        }
        
        return SolutionResult(
            steps = listOf(SolutionStep(1, "Geometry problem detected", input)),
            finalAnswer = "Please specify the geometric shape and values",
            relatedConcepts = listOf("Geometry")
        )
    }

    private fun solvePythagorean(input: String, steps: MutableList<SolutionStep>): SolutionResult {
        val numbers = extractNumbers(input)
        
        return when (numbers.size) {
            2 -> {
                val a = numbers[0]
                val b = numbers[1]
                
                steps.add(SolutionStep(
                    stepNumber = 1,
                    description = "Given: two sides of a right triangle",
                    formula = "a = $a, b = $b"
                ))
                
                steps.add(SolutionStep(
                    stepNumber = 2,
                    description = "Apply the Pythagorean theorem",
                    formula = "c² = a² + b²"
                ))
                
                val c = sqrt(a * a + b * b)
                
                steps.add(SolutionStep(
                    stepNumber = 3,
                    description = "Calculate the hypotenuse",
                    formula = "c = √(${a}² + ${b}²) = ${String.format("%.4f", c)}",
                    result = c.toString()
                ))
                
                SolutionResult(
                    steps = steps,
                    finalAnswer = "Hypotenuse c = ${String.format("%.4f", c)}",
                    relatedConcepts = listOf("Pythagorean Theorem", "Right Triangle", "Hypotenuse")
                )
            }
            else -> SolutionResult(
                steps = steps,
                finalAnswer = "Please provide two sides of the right triangle",
                relatedConcepts = listOf("Pythagorean Theorem")
            )
        }
    }

    private fun solveCalculus(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        
        steps.add(SolutionStep(
            stepNumber = 1,
            description = "Calculus problem detected",
            formula = input
        ))
        
        return SolutionResult(
            steps = steps,
            finalAnswer = "Calculus solver - Advanced feature coming soon",
            relatedConcepts = listOf("Calculus", "Derivatives", "Integrals", "Limits")
        )
    }

    private fun solveTrigonometry(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        
        steps.add(SolutionStep(
            stepNumber = 1,
            description = "Trigonometry problem detected",
            formula = input
        ))
        
        return SolutionResult(
            steps = steps,
            finalAnswer = "Trigonometry solver - Advanced feature coming soon",
            relatedConcepts = listOf("Trigonometry", "Sine", "Cosine", "Tangent")
        )
    }

    private fun solveStatistics(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        val numbers = extractNumbers(input)
        
        if (numbers.isNotEmpty()) {
            steps.add(SolutionStep(
                stepNumber = 1,
                description = "Data set identified",
                formula = numbers.joinToString(", ")
            ))
            
            val mean = numbers.average()
            
            steps.add(SolutionStep(
                stepNumber = 2,
                description = "Calculate the mean (average)",
                formula = "x̄ = (Σx) / n = ${numbers.sum()} / ${numbers.size}",
                result = String.format("%.4f", mean)
            ))
            
            val variance = numbers.map { (it - mean).pow(2) }.average()
            val stdDev = sqrt(variance)
            
            steps.add(SolutionStep(
                stepNumber = 3,
                description = "Calculate the standard deviation",
                formula = "σ = √variance = ${String.format("%.4f", stdDev)}",
                result = String.format("%.4f", stdDev)
            ))
            
            return SolutionResult(
                steps = steps,
                finalAnswer = "Mean = ${String.format("%.4f", mean)}, Std Dev = ${String.format("%.4f", stdDev)}",
                relatedConcepts = listOf("Statistics", "Mean", "Standard Deviation", "Variance")
            )
        }
        
        return SolutionResult(
            steps = steps,
            finalAnswer = "Please provide numerical data",
            relatedConcepts = listOf("Statistics")
        )
    }

    private fun solveGeneralMath(input: String): SolutionResult {
        return SolutionResult(
            steps = listOf(SolutionStep(1, "Problem received", input)),
            finalAnswer = "Please specify the mathematical category (algebra, geometry, calculus, etc.)",
            relatedConcepts = listOf("Mathematics")
        )
    }

    // Helper functions
    private fun extractCoefficient(expression: String): Double {
        val regex = "(-?\\d*\\.?\\d*)x".toRegex()
        val match = regex.find(expression)
        return match?.groupValues?.get(1)?.toDoubleOrNull() ?: 1.0
    }

    private fun extractConstant(expression: String): Double {
        val regex = "([+-]?\\d+\\.?\\d*)$".toRegex()
        val match = regex.find(expression.replace(" ", ""))
        return match?.groupValues?.get(1)?.toDoubleOrNull() ?: 0.0
    }

    private fun parseQuadraticCoefficients(input: String): Triple<Double, Double, Double> {
        // Simplified parsing - assumes format like "x^2 + 5x + 6 = 0"
        var a = 1.0
        var b = 0.0
        var c = 0.0
        
        val cleanInput = input.replace(" ", "").replace("²", "^2")
        
        // Extract coefficient of x^2
        val aMatch = "(-?\\d*\\.?\\d*)x\\^2".toRegex().find(cleanInput)
        a = aMatch?.groupValues?.get(1)?.toDoubleOrNull() ?: 1.0
        
        // Extract coefficient of x
        val bMatch = "([+-]?\\d*\\.?\\d*)x[^\\^]".toRegex().find(cleanInput)
        b = bMatch?.groupValues?.get(1)?.toDoubleOrNull() ?: 0.0
        
        // Extract constant term
        val cMatch = "([+-]?\\d+\\.?\\d*)=".toRegex().find(cleanInput)
        c = cMatch?.groupValues?.get(1)?.toDoubleOrNull() ?: 0.0
        
        return Triple(a, b, c)
    }

    private fun extractNumber(input: String): Double? {
        return "\\d+\\.?\\d*".toRegex().find(input)?.value?.toDoubleOrNull()
    }

    private fun extractNumbers(input: String): List<Double> {
        return "-?\\d+\\.?\\d*".toRegex().findAll(input)
            .mapNotNull { it.value.toDoubleOrNull() }
            .toList()
    }
}
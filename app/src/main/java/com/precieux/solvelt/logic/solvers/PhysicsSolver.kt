package com.precieux.solvelt.logic.solvers

import com.precieux.solvelt.logic.data.entities.SolutionStep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhysicsSolver : ProblemSolver {

    override suspend fun solve(input: String, subcategory: String): Result<SolutionResult> {
        return withContext(Dispatchers.Default) {
            try {
                val result = when (subcategory.lowercase()) {
                    "mechanics" -> solveMechanics(input)
                    "electricity" -> solveElectricity(input)
                    "thermodynamics" -> solveThermodynamics(input)
                    "optics" -> solveOptics(input)
                    "waves" -> solveWaves(input)
                    else -> solveGeneralPhysics(input)
                }
                Result.success(result)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    private fun solveMechanics(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        
        // Newton's Second Law: F = ma
        if (input.contains("force") || input.contains("newton") || input.contains("F=") || input.contains("F =")) {
            return solveNewtonsSecondLaw(input, steps)
        }
        
        // Kinematic equations
        if (input.contains("velocity") || input.contains("acceleration") || input.contains("distance") || input.contains("time")) {
            return solveKinematics(input, steps)
        }
        
        return SolutionResult(
            steps = listOf(SolutionStep(1, "Mechanics problem detected", input)),
            finalAnswer = "Please specify: force, velocity, acceleration, or distance problem",
            relatedConcepts = listOf("Mechanics", "Newton's Laws", "Kinematics")
        )
    }

    private fun solveNewtonsSecondLaw(input: String, steps: MutableList<SolutionStep>): SolutionResult {
        val numbers = extractNumbers(input)
        
        return when (numbers.size) {
            2 -> {
                // If we have 2 values, calculate the third
                val (m, a) = numbers[0] to numbers[1]
                
                steps.add(SolutionStep(
                    stepNumber = 1,
                    description = "Identify given values",
                    formula = "Mass m = $m kg, Acceleration a = $a m/s²"
                ))
                
                steps.add(SolutionStep(
                    stepNumber = 2,
                    description = "Apply Newton's Second Law",
                    formula = "F = m × a"
                ))
                
                val force = m * a
                
                steps.add(SolutionStep(
                    stepNumber = 3,
                    description = "Calculate the force",
                    formula = "F = $m × $a = ${String.format("%.2f", force)} N",
                    result = force.toString()
                ))
                
                SolutionResult(
                    steps = steps,
                    finalAnswer = "Force F = ${String.format("%.2f", force)} N",
                    relatedConcepts = listOf("Newton's Second Law", "Force", "Mass", "Acceleration")
                )
            }
            else -> SolutionResult(
                steps = steps,
                finalAnswer = "Please provide mass and acceleration values",
                relatedConcepts = listOf("Newton's Second Law", "F = ma")
            )
        }
    }

    private fun solveKinematics(input: String, steps: MutableList<SolutionStep>): SolutionResult {
        val numbers = extractNumbers(input)
        
        steps.add(SolutionStep(
            stepNumber = 1,
            description = "Kinematic problem identified",
            formula = input
        ))
        
        // v = u + at (final velocity)
        if (numbers.size >= 3) {
            val u = numbers[0] // initial velocity
            val a = numbers[1] // acceleration
            val t = numbers[2] // time
            
            steps.add(SolutionStep(
                stepNumber = 2,
                description = "Use kinematic equation",
                formula = "v = u + at"
            ))
            
            val v = u + a * t
            
            steps.add(SolutionStep(
                stepNumber = 3,
                description = "Calculate final velocity",
                formula = "v = $u + ($a × $t) = ${String.format("%.2f", v)} m/s",
                result = v.toString()
            ))
            
            return SolutionResult(
                steps = steps,
                finalAnswer = "Final velocity v = ${String.format("%.2f", v)} m/s",
                relatedConcepts = listOf("Kinematics", "Velocity", "Acceleration", "Motion")
            )
        }
        
        return SolutionResult(
            steps = steps,
            finalAnswer = "Please provide initial velocity, acceleration, and time",
            relatedConcepts = listOf("Kinematics", "Equations of Motion")
        )
    }

    private fun solveElectricity(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        
        // Ohm's Law: V = IR
        if (input.contains("ohm") || input.contains("voltage") || input.contains("current") || input.contains("resistance")) {
            return solveOhmsLaw(input, steps)
        }
        
        // Power: P = VI
        if (input.contains("power") || input.contains("watt")) {
            return solvePower(input, steps)
        }
        
        return SolutionResult(
            steps = listOf(SolutionStep(1, "Electricity problem detected", input)),
            finalAnswer = "Please specify: Ohm's Law or Power calculation",
            relatedConcepts = listOf("Electricity", "Circuits", "Ohm's Law")
        )
    }

    private fun solveOhmsLaw(input: String, steps: MutableList<SolutionStep>): SolutionResult {
        val numbers = extractNumbers(input)
        
        return when (numbers.size) {
            2 -> {
                val (v, r) = numbers[0] to numbers[1]
                
                steps.add(SolutionStep(
                    stepNumber = 1,
                    description = "Identify given values",
                    formula = "Voltage V = $v V, Resistance R = $r Ω"
                ))
                
                steps.add(SolutionStep(
                    stepNumber = 2,
                    description = "Apply Ohm's Law",
                    formula = "I = V / R"
                ))
                
                val current = v / r
                
                steps.add(SolutionStep(
                    stepNumber = 3,
                    description = "Calculate the current",
                    formula = "I = $v / $r = ${String.format("%.4f", current)} A",
                    result = current.toString()
                ))
                
                SolutionResult(
                    steps = steps,
                    finalAnswer = "Current I = ${String.format("%.4f", current)} A",
                    relatedConcepts = listOf("Ohm's Law", "Voltage", "Current", "Resistance")
                )
            }
            else -> SolutionResult(
                steps = steps,
                finalAnswer = "Please provide voltage and resistance values",
                relatedConcepts = listOf("Ohm's Law", "V = IR")
            )
        }
    }

    private fun solvePower(input: String, steps: MutableList<SolutionStep>): SolutionResult {
        val numbers = extractNumbers(input)
        
        return when (numbers.size) {
            2 -> {
                val (v, i) = numbers[0] to numbers[1]
                
                steps.add(SolutionStep(
                    stepNumber = 1,
                    description = "Identify given values",
                    formula = "Voltage V = $v V, Current I = $i A"
                ))
                
                steps.add(SolutionStep(
                    stepNumber = 2,
                    description = "Apply Power formula",
                    formula = "P = V × I"
                ))
                
                val power = v * i
                
                steps.add(SolutionStep(
                    stepNumber = 3,
                    description = "Calculate the power",
                    formula = "P = $v × $i = ${String.format("%.2f", power)} W",
                    result = power.toString()
                ))
                
                SolutionResult(
                    steps = steps,
                    finalAnswer = "Power P = ${String.format("%.2f", power)} W",
                    relatedConcepts = listOf("Electrical Power", "Watt", "Voltage", "Current")
                )
            }
            else -> SolutionResult(
                steps = steps,
                finalAnswer = "Please provide voltage and current values",
                relatedConcepts = listOf("Electrical Power", "P = VI")
            )
        }
    }

    private fun solveThermodynamics(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        
        steps.add(SolutionStep(
            stepNumber = 1,
            description = "Thermodynamics problem detected",
            formula = input
        ))
        
        return SolutionResult(
            steps = steps,
            finalAnswer = "Thermodynamics solver - Advanced feature coming soon",
            relatedConcepts = listOf("Thermodynamics", "Heat", "Temperature", "Energy")
        )
    }

    private fun solveOptics(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        
        steps.add(SolutionStep(
            stepNumber = 1,
            description = "Optics problem detected",
            formula = input
        ))
        
        return SolutionResult(
            steps = steps,
            finalAnswer = "Optics solver - Advanced feature coming soon",
            relatedConcepts = listOf("Optics", "Light", "Reflection", "Refraction")
        )
    }

    private fun solveWaves(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        
        steps.add(SolutionStep(
            stepNumber = 1,
            description = "Waves problem detected",
            formula = input
        ))
        
        return SolutionResult(
            steps = steps,
            finalAnswer = "Waves solver - Advanced feature coming soon",
            relatedConcepts = listOf("Waves", "Frequency", "Wavelength", "Amplitude")
        )
    }

    private fun solveGeneralPhysics(input: String): SolutionResult {
        return SolutionResult(
            steps = listOf(SolutionStep(1, "Physics problem detected", input)),
            finalAnswer = "Please specify the physics category (mechanics, electricity, thermodynamics, etc.)",
            relatedConcepts = listOf("Physics")
        )
    }

    private fun extractNumbers(input: String): List<Double> {
        return "-?\\d+\\.?\\d*".toRegex().findAll(input)
            .mapNotNull { it.value.toDoubleOrNull() }
            .toList()
    }
}
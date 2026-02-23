package com.precieux.solvelt.logic.solvers

import com.precieux.solvelt.logic.data.entities.SolutionStep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChemistrySolver : ProblemSolver {

    override suspend fun solve(input: String, subcategory: String): Result<SolutionResult> {
        return withContext(Dispatchers.Default) {
            try {
                val result = when (subcategory.lowercase()) {
                    "organic" -> solveOrganicChemistry(input)
                    "inorganic" -> solveInorganicChemistry(input)
                    "equations" -> solveChemicalEquations(input)
                    "stoichiometry" -> solveStoichiometry(input)
                    "periodic" -> solvePeriodicTable(input)
                    else -> solveGeneralChemistry(input)
                }
                Result.success(result)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    private fun solveOrganicChemistry(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        
        steps.add(SolutionStep(
            stepNumber = 1,
            description = "Organic chemistry problem detected",
            formula = input
        ))
        
        return SolutionResult(
            steps = steps,
            finalAnswer = "Organic chemistry solver - Advanced feature coming soon",
            relatedConcepts = listOf("Organic Chemistry", "Hydrocarbons", "Functional Groups", "Reactions")
        )
    }

    private fun solveInorganicChemistry(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        
        steps.add(SolutionStep(
            stepNumber = 1,
            description = "Inorganic chemistry problem detected",
            formula = input
        ))
        
        return SolutionResult(
            steps = steps,
            finalAnswer = "Inorganic chemistry solver - Advanced feature coming soon",
            relatedConcepts = listOf("Inorganic Chemistry", "Ionic Compounds", "Acids", "Bases")
        )
    }

    private fun solveChemicalEquations(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        
        steps.add(SolutionStep(
            stepNumber = 1,
            description = "Chemical equation problem detected",
            formula = input
        ))
        
        // Simple balancing example for H2 + O2 -> H2O
        if (input.contains("H2") && input.contains("O2") && input.contains("H2O")) {
            steps.add(SolutionStep(
                stepNumber = 2,
                description = "Identify the unbalanced equation",
                formula = "H₂ + O₂ → H₂O"
            ))
            
            steps.add(SolutionStep(
                stepNumber = 3,
                description = "Count atoms on each side",
                formula = "Left: 2 H, 2 O | Right: 2 H, 1 O"
            ))
            
            steps.add(SolutionStep(
                stepNumber = 4,
                description = "Balance oxygen atoms by adding coefficient 2 to H₂O",
                formula = "H₂ + O₂ → 2H₂O"
            ))
            
            steps.add(SolutionStep(
                stepNumber = 5,
                description = "Balance hydrogen atoms by adding coefficient 2 to H₂",
                formula = "2H₂ + O₂ → 2H₂O"
            ))
            
            steps.add(SolutionStep(
                stepNumber = 6,
                description = "Verify the balanced equation",
                formula = "Left: 4 H, 2 O | Right: 4 H, 2 O ✓"
            ))
            
            return SolutionResult(
                steps = steps,
                finalAnswer = "2H₂ + O₂ → 2H₂O",
                relatedConcepts = listOf("Chemical Equations", "Balancing", "Stoichiometry", "Conservation of Mass")
            )
        }
        
        return SolutionResult(
            steps = steps,
            finalAnswer = "Chemical equation solver - Please provide a specific equation to balance",
            relatedConcepts = listOf("Chemical Equations", "Balancing", "Stoichiometry")
        )
    }

    private fun solveStoichiometry(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        
        steps.add(SolutionStep(
            stepNumber = 1,
            description = "Stoichiometry problem detected",
            formula = input
        ))
        
        return SolutionResult(
            steps = steps,
            finalAnswer = "Stoichiometry solver - Advanced feature coming soon",
            relatedConcepts = listOf("Stoichiometry", "Moles", "Molar Mass", "Limiting Reagent")
        )
    }

    private fun solvePeriodicTable(input: String): SolutionResult {
        val steps = mutableListOf<SolutionStep>()
        
        steps.add(SolutionStep(
            stepNumber = 1,
            description = "Periodic table query detected",
            formula = input
        ))
        
        // Common element information
        val elementInfo = getElementInfo(input)
        
        if (elementInfo != null) {
            steps.add(SolutionStep(
                stepNumber = 2,
                description = "Element information found",
                formula = elementInfo.formula
            ))
            
            return SolutionResult(
                steps = steps,
                finalAnswer = elementInfo.description,
                relatedConcepts = listOf("Periodic Table", "Elements", "Atomic Structure")
            )
        }
        
        return SolutionResult(
            steps = steps,
            finalAnswer = "Periodic table reference - Please specify an element symbol or name",
            relatedConcepts = listOf("Periodic Table", "Elements", "Atomic Number", "Atomic Mass")
        )
    }

    private fun solveGeneralChemistry(input: String): SolutionResult {
        return SolutionResult(
            steps = listOf(SolutionStep(1, "Chemistry problem detected", input)),
            finalAnswer = "Please specify the chemistry category (organic, inorganic, equations, stoichiometry, periodic)",
            relatedConcepts = listOf("Chemistry")
        )
    }

    private fun getElementInfo(input: String): ElementInfo? {
        val elementMap = mapOf(
            "H" to ElementInfo("H", "Hydrogen", "Atomic number: 1, Atomic mass: 1.008 u, Nonmetal"),
            "He" to ElementInfo("He", "Helium", "Atomic number: 2, Atomic mass: 4.003 u, Noble Gas"),
            "Li" to ElementInfo("Li", "Lithium", "Atomic number: 3, Atomic mass: 6.941 u, Alkali Metal"),
            "C" to ElementInfo("C", "Carbon", "Atomic number: 6, Atomic mass: 12.011 u, Nonmetal"),
            "N" to ElementInfo("N", "Nitrogen", "Atomic number: 7, Atomic mass: 14.007 u, Nonmetal"),
            "O" to ElementInfo("O", "Oxygen", "Atomic number: 8, Atomic mass: 15.999 u, Nonmetal"),
            "Na" to ElementInfo("Na", "Sodium", "Atomic number: 11, Atomic mass: 22.990 u, Alkali Metal"),
            "Cl" to ElementInfo("Cl", "Chlorine", "Atomic number: 17, Atomic mass: 35.453 u, Halogen"),
            "Fe" to ElementInfo("Fe", "Iron", "Atomic number: 26, Atomic mass: 55.845 u, Transition Metal"),
            "Au" to ElementInfo("Au", "Gold", "Atomic number: 79, Atomic mass: 196.967 u, Transition Metal")
        )
        
        val upperInput = input.uppercase()
        return elementMap.entries.find { 
            upperInput.contains(it.key) || upperInput.contains(it.value.name.uppercase()) 
        }?.value
    }

    data class ElementInfo(
        val symbol: String,
        val name: String,
        val description: String
    ) {
        val formula: String
            get() = "$symbol - $name"
    }
}
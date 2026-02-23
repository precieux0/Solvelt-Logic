package com.precieux.solvelt.logic.data.entities

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.precieux.solvelt.logic.R
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "categories")
data class Category(
    @PrimaryKey
    val id: String,
    val name: String,
    val nameFr: String,
    val description: String,
    val descriptionFr: String,
    @DrawableRes val iconRes: Int,
    @ColorRes val colorRes: Int,
    @ColorRes val containerColorRes: Int,
    val order: Int,
    val isActive: Boolean = true
) : Parcelable {
    fun getLocalizedName(language: String): String {
        return if (language == "fr") nameFr else name
    }
    
    fun getLocalizedDescription(language: String): String {
        return if (language == "fr") descriptionFr else description
    }
    
    companion object {
        fun getDefaultCategories(): List<Category> = listOf(
            Category(
                id = "mathematics",
                name = "Mathematics",
                nameFr = "Mathématiques",
                description = "Algebra, Geometry, Calculus, and more",
                descriptionFr = "Algèbre, Géométrie, Calcul, et plus",
                iconRes = R.drawable.ic_math,
                colorRes = R.color.category_math,
                containerColorRes = R.color.category_math_container,
                order = 0
            ),
            Category(
                id = "physics",
                name = "Physics",
                nameFr = "Physique",
                description = "Mechanics, Electricity, Thermodynamics",
                descriptionFr = "Mécanique, Électricité, Thermodynamique",
                iconRes = R.drawable.ic_physics,
                colorRes = R.color.category_physics,
                containerColorRes = R.color.category_physics_container,
                order = 1
            ),
            Category(
                id = "chemistry",
                name = "Chemistry",
                nameFr = "Chimie",
                description = "Organic, Inorganic, Equations",
                descriptionFr = "Organique, Inorganique, Équations",
                iconRes = R.drawable.ic_chemistry,
                colorRes = R.color.category_chemistry,
                containerColorRes = R.color.category_chemistry_container,
                order = 2
            ),
            Category(
                id = "technical_drawing",
                name = "Technical Drawing",
                nameFr = "Dessin Technique",
                description = "Engineering drawings and blueprints",
                descriptionFr = "Dessins d'ingénierie et plans",
                iconRes = R.drawable.ic_drawing,
                colorRes = R.color.category_drawing,
                containerColorRes = R.color.category_drawing_container,
                order = 3
            ),
            Category(
                id = "science",
                name = "Other Sciences",
                nameFr = "Autres Sciences",
                description = "Biology, Earth Science, and more",
                descriptionFr = "Biologie, Sciences de la Terre, et plus",
                iconRes = R.drawable.ic_science,
                colorRes = R.color.category_science,
                containerColorRes = R.color.category_science_container,
                order = 4
            )
        )
    }
}
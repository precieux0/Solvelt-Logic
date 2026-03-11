# 📚 Guide de Complétion des Fragments - SolVelt Logic

## 📝 Fragments à Implémenter

### 1. HomeFragment
**Lieu:** `app/src/main/java/com/precieux/solvelt/logic/ui/fragments/HomeFragment.kt`
**Layout:** `app/src/main/res/layout/fragment_home_new.xml` ✅ (Créé)

```kotlin
package com.precieux.solvelt.logic.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import androidx.fragment.app.Fragment
import com.precieux.solvelt.logic.R

class HomeFragment : Fragment() {
    companion object {
        private const val TAG = "HomeFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return try {
            inflater.inflate(R.layout.fragment_home_new, container, false)
        } catch (e: Exception) {
            Log.e(TAG, "Erreur création layout", e)
            View(requireContext())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            setupUI(view)
            Log.d(TAG, "HomeFragment initialisé")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation", e)
        }
    }

    private fun setupUI(view: View) {
        try {
            val startButton = view.findViewById<com.google.android.material.button.MaterialButton>(R.id.start_button)
            startButton?.setOnClickListener {
                Log.d(TAG, "Bouton 'Commencer' cliqué")
                // TODO: Implémenter la navigation vers les catégories
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur setup UI", e)
        }
    }
}
```

---

### 2. CoursesFragment
**Lieu:** `app/src/main/java/com/precieux/solvelt/logic/ui/fragments/CoursesFragment.kt`

```kotlin
package com.precieux.solvelt.logic.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CoursesFragment : Fragment() {
    companion object {
        private const val TAG = "CoursesFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return RecyclerView(requireContext()).apply {
            layoutManager = LinearLayoutManager(context)
            Log.d(TAG, "CoursesFragment créé")
        }
    }
}
```

---

### 3. SolverFragment
**Lieu:** `app/src/main/java/com/precieux/solvelt/logic/ui/fragments/SolverFragment.kt`

```kotlin
package com.precieux.solvelt.logic.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.util.Log

class SolverFragment : Fragment() {
    companion object {
        private const val TAG = "SolverFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            
            addView(TextView(context).apply {
                text = "Solveur - À implémenter"
                textSize = 18f
                setPadding(16, 16, 16, 16)
            })
            
            Log.d(TAG, "SolverFragment créé")
        }
    }
}
```

---

### 4. ProfileFragment
**Lieu:** `app/src/main/java/com/precieux/solvelt/logic/ui/fragments/ProfileFragment.kt`

```kotlin
package com.precieux.solvelt.logic.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.util.Log

class ProfileFragment : Fragment() {
    companion object {
        private const val TAG = "ProfileFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = View(requireContext())
        view.setBackgroundColor(android.graphics.Color.WHITE)
        Log.d(TAG, "ProfileFragment créé")
        return view
    }
}
```

---

### 5. SettingsFragment
**Lieu:** `app/src/main/java/com/precieux/solvelt/logic/ui/fragments/SettingsFragment.kt`

```kotlin
package com.precieux.solvelt.logic.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.util.Log

class SettingsFragment : Fragment() {
    companion object {
        private const val TAG = "SettingsFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = View(requireContext())
        view.setBackgroundColor(android.graphics.Color.WHITE)
        Log.d(TAG, "SettingsFragment créé")
        return view
    }
}
```

---

## 🔗 Navigation Graph (nav_graph.xml)

Assurez-vous que `app/src/main/res/navigation/nav_graph.xml` contient:

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_graph"
    app:startDestination="@id/homeFragment">

    <fragment
        android:id="@+id/homeFragment"
        android:name="com.precieux.solvelt.logic.ui.fragments.HomeFragment"
        android:label="Accueil" />

    <fragment
        android:id="@+id/coursesFragment"
        android:name="com.precieux.solvelt.logic.ui.fragments.CoursesFragment"
        android:label="Cours" />

    <fragment
        android:id="@+id/solverFragment"
        android:name="com.precieux.solvelt.logic.ui.fragments.SolverFragment"
        android:label="Résoudre" />

    <fragment
        android:id="@+id/profileFragment"
        android:name="com.precieux.solvelt.logic.ui.fragments.ProfileFragment"
        android:label="Profil" />

    <fragment
        android:id="@+id/settingsFragment"
        android:name="com.precieux.solvelt.logic.ui.fragments.SettingsFragment"
        android:label="Paramètres" />

</navigation>
```

---

## 📋 Menu Bottom Navigation (menu_bottom_nav.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/homeFragment"
        android:icon="@drawable/ic_home"
        android:title="Accueil" />

    <item
        android:id="@+id/coursesFragment"
        android:icon="@drawable/ic_courses"
        android:title="Cours" />

    <item
        android:id="@+id/solverFragment"
        android:icon="@drawable/ic_solve"
        android:title="Résoudre" />

    <item
        android:id="@+id/profileFragment"
        android:icon="@drawable/ic_profile"
        android:title="Profil" />

    <item
        android:id="@+id/settingsFragment"
        android:icon="@drawable/ic_settings"
        android:title="Paramètres" />
</menu>
```

---

## ✅ Checklist d'Implémentation

- [ ] Vérifier que tous les fragments existent
- [ ] Vérifier que tous les layouts XML existent
- [ ] Vérifier que nav_graph.xml a tous les fragments
- [ ] Vérifier que menu_bottom_nav.xml a tous les items
- [ ] Compiler sans erreurs: `./gradlew build`
- [ ] Lancer l'app et tester la navigation

---

## 🚀 Tester la Navigation

```kotlin
// Dans MainActivity.kt (déjà implémenté)
val navController = navHostFragment.navController
NavigationUI.setupWithNavController(bottomNav, navController)

// Les clics sur bottom_nav changeront automatiquement le fragment
```

---

**Statut:** 📝 À compléter selon votre implémentation

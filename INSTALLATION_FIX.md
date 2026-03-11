# 📱 Guide d'Installation et de Compilation - SolVelt Logic (CORRIGÉ)

## 🔧 Prérequis

- Android Studio **version 2024.1 ou plus récente**
- JDK 17 (intégré dans Android Studio)
- Android SDK API 34
- Gradle 8.x

---

## 🚀 Installation Rapide

### 1. Cloner le Projet
```bash
git clone https://github.com/precieux-okitakoy/Solvelt-Logic.git
cd Solvelt-Logic
```

### 2. Ouvrir dans Android Studio
```
File → Open → Solvelt-Logic
```

### 3. Configuration Gradle
Android Studio va télécharger automatiquement:
- Gradle wrapper
- Dépendances
- SDK necessary

**Attendre que l'indexation soit terminée** (coin inférieur droit → "Indexing...")

---

## ✅ Corrections Appliquées

### A. Import Incorrect Corrigé
**Avant (ERREUR):**
```kotlin
import androidx.appcompat.appCompatActivity  ❌
```

**Après (CORRECT):**
```kotlin
import androidx.appcompat.app.AppCompatActivity  ✅
```

### B. Fichiers Mis à Jour
- ✅ `SplashActivity.kt` - Typo corrigée + gestion d'erreurs
- ✅ `MainActivity.kt` - Navigation architecture + fallback UI
- ✅ `SolVeltApplication.kt` - Logging + try-catch
- ✅ 4 nouveaux layouts XML modernes

---

## 📦 Compilation et Exécution

### Via Android Studio

**Option 1: Débogage (Recommandé)**
```
1. Run → Edit Configurations
2. Vérifier que "app" est sélectionné
3. Run → Run 'app' (Shift+F10)
4. Sélectionner un device virtuel ou connecté
```

**Option 2: Build Release (Production)**
```
1. Build → Build Bundle(s) / APK(s) → Build APK(s)
2. APK généré: app/release/app-release.apk
```

### Via Terminal

**Debug Build:**
```bash
./gradlew assembleDebug
# APK: app/build/outputs/apk/debug/app-debug.apk
```

**Release Build:**
```bash
./gradlew assembleRelease
# APK: app/build/outputs/apk/release/app-release.apk
```

---

## 🐛 Dépannage

### ❌ Erreur: "Cannot resolve symbol 'AppCompatActivity'"
**Cause:** Imports incorrects
**Solution:**
```kotlin
// ❌ MAUVAIS
import androidx.appcompat.appCompatActivity

// ✅ CORRECT
import androidx.appcompat.app.AppCompatActivity
```

### ❌ Erreur: "Build failed"
**Cause:** Cache Gradle corrompu
**Solution:**
```bash
./gradlew clean
./gradlew build
```

### ❌ Erreur: "Compilation 404 - Dependency not found"
**Cause:** Problème de connexion ou dépôt
**Solution:**
```gradle
// Dans build.gradle.kts
repositories {
    google()
    mavenCentral()
    maven { url = uri("https://jcenter.bintray.com") }
}
```

### ❌ App Plante au Démarrage
**Cause:** Fragment manquant ou layout non trouvé
**Solution:**
1. Ouvrir **Logcat** (View → Tool Windows → Logcat)
2. Filtrer: `com.precieux.solvelt.logic`
3. Chercher les messages `E/` (erreurs)
4. Lire le stack trace et corriger

### ❌ App Reste Bloquée sur Splash
**Cause:** Délai pas respecté ou erreur dans MainActivity
**Solution:**
- Vérifier le timeout SplashActivity (3 secondes)
- Vérifier que MainActivity existe et compile
- Regarder les logs Logcat

---

## 🎯 Vérifier que Tout Fonctionne

### Checklist Post-Compilation

- [ ] **App démarre** sans crash
- [ ] **Splash screen** s'affiche 3 secondes
- [ ] **MainActivity** se charge automatiquement
- [ ] **UI responsive** et pas d'ANR (Application Not Responding)
- [ ] **Logs** montrent `D/SplashActivity: SplashActivity créée avec succès`
- [ ] **Logs** montrent `D/MainActivity: MainActivity créée avec succès`
- [ ] **Aucune erreur** dans Logcat filtrée sur votre package

### Test Complet
```bash
# Dans Logcat, chercher ces messages (ordre normal):
D/SolVeltApplication: Initialisation de SolVeltApplication
D/SolVeltApplication: Mode nuit système activé
D/SolVeltApplication: SolVeltApplication initialisée avec succès
D/SplashActivity: SplashActivity créée avec succès
D/SplashActivity: Transition vers MainActivity
D/MainActivity: MainActivity créée avec succès
D/MainActivity: Navigation initialisée avec succès
```

---

## 📱 Installation sur Appareil

### Via USB (Recommandé)

1. **Connecter téléphone Android**
   - Activer le mode développeur
   - Autoriser USB debugging

2. **Dans Android Studio:**
   - Run → Select Device
   - Choisir votre téléphone
   - Appuyer sur Run

3. **Vérifier dans Logcat:**
   - Vérifier que c'est bien votre appareil
   - Regarder les logs d'exécution

### Via Émulateur

1. **Créer AVD** (Android Virtual Device)
   - Tools → Device Manager
   - Create Device
   - Choisir: Pixel 6, Android 14, x86_64

2. **Lancer l'émulateur:**
   - Device Manager → Play (▶)
   - Attendre le démarrage complet

3. **Compiler et installer:**
   - Run → Run 'app'
   - Sélectionner l'émulateur
   - Attendre l'installation

---

## 🎨 Personnalisation (Optionnel)

### Changer le Temps de Splash
`SplashActivity.kt:` ligne 15
```kotlin
private const val SPLASH_DELAY = 3000L  // En millisecondes
// 3000 = 3 secondes
// 2000 = 2 secondes
```

### Changer les Couleurs
`app/res/values/colors.xml:` 
```xml
<color name="primary">#6750A4</color>  <!-- Violet par défaut -->
```

### Changer le Thème
`app/res/values/styles.xml` ou `themes.xml`

---

## 📊 Architecture du Projet

```
app/
├── src/main/
│   ├── AndroidManifest.xml
│   ├── java/com/precieux/solvelt/logic/
│   │   ├── SolVeltApplication.kt ✅
│   │   ├── ui/activities/
│   │   │   ├── SplashActivity.kt ✅ (CORRIGÉ)
│   │   │   └── MainActivity.kt ✅ (AMÉLIORÉ)
│   │   ├── solvers/
│   │   ├── data/
│   │   └── di/
│   └── res/
│       ├── layout/
│       │   ├── activity_splash_new.xml ✅ (NOUVEAU)
│       │   ├── activity_main_new.xml ✅ (NOUVEAU)
│       │   ├── activity_auth_new.xml ✅ (NOUVEAU)
│       │   └── fragment_home_new.xml ✅ (NOUVEAU)
│       ├── values/
│       │   └── colors.xml
│       └── drawable/
└── build.gradle.kts

```

---

## 💡 Conseils d'Optimisation

1. **Désactiver ProGuard en Debug** → Plus rapide
2. **Utiliser Hot Reload** → Modifications sans redémarrage
3. **Vérifier Logcat en Temps Réel** → Identifier les bugs
4. **Tester sur Multiple Appareils** → Compatibilité

---

## 🆘 Besoin d'Aide?

Chercher dans Logcat:
```
E/AndroidRuntime  - Erreurs critiques
W/                - Avertissements
D/[votre_tag]     - Messages de débogage
```

Sauvegarder les logs:
```bash
adb logcat > logs.txt
```

---

**Dernière mise à jour:** 10 Mars 2026  
**Statut:** ✅ Compilation et Installation Optimisées

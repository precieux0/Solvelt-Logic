# SolVelt-Logic

**Votre Compagnon Complet de Résolution de Problèmes Scientifiques**

SolVelt-Logic est une application Android moderne conçue pour aider les étudiants, les enseignants et les apprenants à résoudre des problèmes scientifiques dans plusieurs disciplines.

## Fonctionnalités

### Résolution de Problèmes
- **Mathématiques**: Algèbre, Géométrie, Calcul, Trigonométrie, Statistiques
- **Physique**: Mécanique, Électricité, Thermodynamique, Optique, Ondes
- **Chimie**: Organique, Inorganique, Équations chimiques, Stœchiométrie
- **Dessin Technique**: Projections, Cotation, Vues en coupe, Isométrique
- Solutions étape par étape avec explications détaillées
- Sauvegarde des problèmes favoris

### Micro-Cours
- Leçons interactives courtes
- Quiz avec retour immédiat
- Suivi de progression
- Téléchargement pour apprentissage hors ligne

### Expérience Utilisateur
- **Material You Design**: Interface moderne avec thématisation dynamique
- **Thème Clair/Sombre**: Sélection automatique ou manuelle
- **Multilingue**: Français et Anglais avec détection automatique
- **Mode Hors Ligne**: Accès au contenu sans connexion internet
- **Authentification Google**: Connexion sécurisée avec mode invité

## Spécifications Techniques

- **Plateforme**: Android 13+ (API 33+)
- **Langage**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **Base de données**: Room (SQLite)
- **Authentification**: Google Sign-In (sans Firebase)
- **UI Framework**: Material Design 3 (Material You)
- **Injection de dépendances**: Hilt
- **Réseau**: Retrofit + OkHttp
- **Opérations asynchrones**: Kotlin Coroutines + Flow

## Informations du Projet

- **Package**: `com.precieux.solvelt.logic`
- **Version**: 1.0.0
- **Version Code**: 1
- **Client ID OAuth**: `936352759734-2ai6ht4kvn73svbo3l257cjtkre4f3li.apps.googleusercontent.com`
- **SHA-1**: `94:E4:5A:97:0D:86:06:33:C2:EF:3D:F7:BF:BC:3C:26:30:23:22:10`
- **Développeur**: Précieux Okitakoy
- **Email**: okitakoyprecieux@gmail.com

## Structure du Projet

```
com.precieux.solvelt.logic/
├── data/
│   ├── database/       # Base de données Room et convertisseurs
│   ├── dao/            # Objets d'accès aux données
│   ├── entities/       # Modèles de données
│   └── repositories/   # Répositories de données
├── di/                 # Modules d'injection de dépendances
├── solvers/            # Moteurs de résolution de problèmes
├── ui/
│   ├── activities/     # Activités
│   ├── fragments/      # Fragments
│   ├── adapters/       # Adaptateurs RecyclerView
│   └── viewmodels/     # ViewModels
├── utils/              # Classes utilitaires
└── auth/               # Gestionnaire d'authentification
```

## Compilation

### Prérequis
- Java JDK 17
- Android SDK (API 34)

### Méthode rapide (Script Python)
```bash
cd SolVelt-Logic
python3 setup_and_build.py
```

### Méthode manuelle
```bash
cd SolVelt-Logic

# Configurer le SDK Android
export ANDROID_SDK_ROOT=/chemin/vers/android-sdk

# Créer local.properties
echo "sdk.dir=$ANDROID_SDK_ROOT" > local.properties

# Rendre gradlew exécutable
chmod +x gradlew

# Compiler l'APK
./gradlew assembleDebug

# L'APK sera généré dans:
# app/build/outputs/apk/debug/app-debug.apk
```

### Via Android Studio
1. Ouvrez le projet dans Android Studio
2. Attendez la synchronisation Gradle
3. Cliquez sur "Build" → "Build Bundle(s) / APK(s)" → "Build APK(s)"

Pour plus de détails, consultez le fichier [BUILD_GUIDE.md](BUILD_GUIDE.md).

## Configuration OAuth Google Cloud Console

1. Allez sur https://console.cloud.google.com/
2. Créez un nouveau projet
3. Activez l'API "Google Sign-In"
4. Allez dans "APIs et services" → "Identifiants"
5. Cliquez sur "Créer des identifiants" → "ID client OAuth 2.0"
6. Sélectionnez "Application Android"
7. Entrez les informations:
   - Nom du package: `com.precieux.solvelt.logic`
   - Empreinte SHA-1: `94:E4:5A:97:0D:86:06:33:C2:EF:3D:F7:BF:BC:3C:26:30:23:22:10`
8. Enregistrez et notez votre Client ID

## Installation

### Via ADB
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Via transfert de fichier
1. Copiez l'APK sur votre téléphone
2. Ouvrez le fichier APK
3. Autorisez l'installation depuis des sources inconnues

## Écrans de l'Application

1. **Splash Screen**: Lancement animé de l'application
2. **Onboarding**: Introduction aux fonctionnalités
3. **Authentification**: Connexion Google et mode invité
4. **Accueil**: Tableau de bord avec catégories et contenu en vedette
5. **Résolveur de Problèmes**: Saisie et résolution de problèmes
6. **Cours**: Parcourir et apprendre avec les micro-cours
7. **Favoris**: Problèmes et cours sauvegardés
8. **Profil**: Statistiques utilisateur et succès
9. **Paramètres**: Préférences de l'application
10. **À propos**: Informations sur l'application et le développeur

## Bibliothèques Utilisées

- **AndroidX Core**: Composants Android de base
- **Material Design 3**: Composants UI modernes
- **Room**: Base de données locale
- **Hilt**: Injection de dépendances
- **Navigation Component**: Navigation dans l'application
- **Google Sign-In**: Authentification OAuth
- **Retrofit**: Client HTTP
- **Gson**: Parsing JSON
- **Coil**: Chargement d'images
- **DataStore**: Stockage des préférences
- **Coroutines**: Programmation asynchrone
- **Lottie**: Animations

## Contribution

Les contributions sont les bienvenues ! N'hésitez pas à soumettre une Pull Request.

## Licence

Ce projet est sous licence MIT - voir le fichier [LICENSE](LICENSE) pour plus de détails.

## Remerciements

- L'équipe Material Design pour le magnifique système de design
- L'équipe Google pour les services d'authentification
- Tous les contributeurs de bibliothèques open-source

## Support

Pour toute question ou assistance:
- Email: okitakoyprecieux@gmail.com
- Développeur: Précieux Okitakoy

---

**Fait avec ❤️ par Précieux Okitakoy**
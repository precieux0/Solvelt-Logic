# Démarrage Rapide - SolVelt-Logic

## Méthode la plus simple pour compiler l'APK

### Option 1: Script Python Automatisé (Recommandé)

```bash
cd SolVelt-Logic
python3 setup_and_build.py
```

Ce script va:
1. Vérifier que Java est installé
2. Configurer Android SDK automatiquement
3. Compiler l'APK
4. Optionnellement installer sur votre téléphone

### Option 2: Compilation Manuelle

#### Étape 1: Installer Java 17
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install openjdk-17-jdk

# macOS
brew install openjdk@17

# Windows
# Téléchargez depuis: https://adoptium.net/
```

#### Étape 2: Installer Android Studio (Recommandé)
1. Téléchargez: https://developer.android.com/studio
2. Installez et ouvrez Android Studio
3. Allez dans SDK Manager (icône SDK en haut à droite)
4. Installez:
   - Android SDK Platform 34
   - Android SDK Build-Tools 34
   - Android SDK Command Line Tools

#### Étape 3: Configurer le projet
```bash
cd SolVelt-Logic

# Créer local.properties
echo "sdk.dir=/chemin/vers/votre/android/sdk" > local.properties

# Exemples de chemins:
# Linux: /home/username/Android/Sdk
# macOS: /Users/username/Library/Android/sdk
# Windows: C:\\Users\\username\\AppData\\Local\\Android\\Sdk
```

#### Étape 4: Compiler
```bash
# Rendre gradlew exécutable
chmod +x gradlew

# Compiler l'APK
./gradlew assembleDebug
```

L'APK sera généré dans: `app/build/outputs/apk/debug/app-debug.apk`

### Option 3: Via Android Studio

1. Ouvrez le dossier SolVelt-Logic dans Android Studio
2. Attendez la synchronisation Gradle (peut prendre quelques minutes)
3. Cliquez sur: **Build** → **Build Bundle(s) / APK(s)** → **Build APK(s)**
4. L'APK sera dans `app/build/outputs/apk/debug/`

## Installation sur votre téléphone

### Méthode 1: ADB (si vous avez les outils Android)
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Méthode 2: Transfert de fichier
1. Copiez l'APK sur votre téléphone (email, USB, cloud)
2. Sur votre téléphone, ouvrez le fichier APK
3. Autorisez l'installation depuis cette source si demandé
4. Installez l'application

### Méthode 3: Android Studio
1. Connectez votre téléphone en USB
2. Activez le débogage USB dans les options développeur
3. Cliquez sur le bouton "Run" (triangle vert) dans Android Studio
4. Sélectionnez votre appareil

## Configuration OAuth (Pour la connexion Google)

Avant d'utiliser l'authentification Google:

1. Allez sur https://console.cloud.google.com/
2. Créez un nouveau projet
3. Allez dans "APIs et services" → "Identifiants"
4. Cliquez sur "Créer des identifiants" → "ID client OAuth 2.0"
5. Sélectionnez "Application Android"
6. Remplissez:
   - Nom du package: `com.precieux.solvelt.logic`
   - Empreinte SHA-1: `94:E4:5A:97:0D:86:06:33:C2:EF:3D:F7:BF:BC:3C:26:30:23:22:10`
7. Enregistrez

## Résolution des problèmes

### "SDK location not found"
Créez le fichier `local.properties` avec le bon chemin vers votre SDK Android.

### "Permission denied" sur gradlew
```bash
chmod +x gradlew
```

### "Could not find com.android.tools.build:gradle"
Vérifiez votre connexion internet et réessayez:
```bash
./gradlew clean
./gradlew assembleDebug --refresh-dependencies
```

### Erreur de mémoire
Modifiez `gradle.properties`:
```properties
org.gradle.jvmargs=-Xmx4096m
```

## Besoin d'aide?

Contactez le développeur:
- Email: okitakoyprecieux@gmail.com
- Nom: Précieux Okitakoy
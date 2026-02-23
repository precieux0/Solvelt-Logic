# Guide de Compilation SolVelt-Logic

Ce guide explique comment compiler l'APK SolVelt-Logic sur votre machine.

## Prérequis

### 1. Java JDK 17
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install openjdk-17-jdk

# macOS
brew install openjdk@17

# Windows
# Téléchargez depuis: https://adoptium.net/
```

Vérifiez l'installation:
```bash
java -version
```

### 2. Android SDK

#### Option A: Android Studio (Recommandé)
1. Téléchargez Android Studio: https://developer.android.com/studio
2. Installez et lancez Android Studio
3. Allez dans SDK Manager et installez:
   - Android SDK Platform 34
   - Android SDK Build-Tools 34
   - Android SDK Command Line Tools

#### Option B: Command Line Tools uniquement
```bash
# Créez le dossier Android SDK
mkdir -p ~/Android/Sdk
cd ~/Android/Sdk

# Téléchargez les command line tools
wget https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip
unzip commandlinetools-linux-11076708_latest.zip
mv cmdline-tools latest
mkdir cmdline-tools
mv latest cmdline-tools/

# Configurez les variables d'environnement
export ANDROID_SDK_ROOT=~/Android/Sdk
export PATH=$PATH:$ANDROID_SDK_ROOT/cmdline-tools/latest/bin
export PATH=$PATH:$ANDROID_SDK_ROOT/platform-tools

# Acceptez les licences
yes | sdkmanager --licenses

# Installez les composants nécessaires
sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"
```

### 3. Variables d'environnement

Ajoutez à votre `~/.bashrc` ou `~/.zshrc`:
```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk  # Ajustez selon votre installation
export ANDROID_SDK_ROOT=~/Android/Sdk
export PATH=$PATH:$ANDROID_SDK_ROOT/cmdline-tools/latest/bin
export PATH=$PATH:$ANDROID_SDK_ROOT/platform-tools
```

Puis rechargez:
```bash
source ~/.bashrc  # ou source ~/.zshrc
```

## Compilation

### Méthode 1: Utiliser le script de build
```bash
cd SolVelt-Logic
chmod +x build_apk.sh
./build_apk.sh
```

### Méthode 2: Commandes Gradle manuelles
```bash
cd SolVelt-Logic

# Rendre gradlew exécutable
chmod +x gradlew

# Compiler l'APK debug
./gradlew assembleDebug

# L'APK sera généré dans:
# app/build/outputs/apk/debug/app-debug.apk
```

### Méthode 3: Via Android Studio
1. Ouvrez le projet dans Android Studio
2. Attendez la synchronisation Gradle
3. Cliquez sur "Build" → "Build Bundle(s) / APK(s)" → "Build APK(s)"
4. L'APK sera dans `app/build/outputs/apk/debug/`

## Résolution des problèmes courants

### Erreur: "SDK location not found"
Créez un fichier `local.properties`:
```bash
echo "sdk.dir=$ANDROID_SDK_ROOT" > local.properties
```

### Erreur: "Permission denied" sur gradlew
```bash
chmod +x gradlew
```

### Erreur: "Could not find com.android.tools.build:gradle"
Vérifiez votre connexion internet et réessayez:
```bash
./gradlew clean
./gradlew assembleDebug --refresh-dependencies
```

### Erreur de mémoire
Si vous avez une erreur de mémoire, augmentez la mémoire Gradle:
Dans `gradle.properties`, modifiez:
```properties
org.gradle.jvmargs=-Xmx4096m -Dfile.encoding=UTF-8
```

## Installation sur votre téléphone

### Méthode 1: ADB (Android Debug Bridge)
```bash
# Connectez votre téléphone en USB avec le mode développeur activé
# Activez: Paramètres > Système > Options pour les développeurs > Débogage USB

# Installez l'APK
adb install app/build/outputs/apk/debug/app-debug.apk

# Ou si vous avez déjà une version installée:
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

### Méthode 2: Transfert de fichier
1. Copiez l'APK sur votre téléphone
2. Ouvrez le fichier APK sur votre téléphone
3. Autorisez l'installation depuis des sources inconnues si demandé

### Méthode 3: Android Studio
1. Connectez votre téléphone en USB
2. Cliquez sur le bouton "Run" (triangle vert) dans Android Studio
3. Sélectionnez votre appareil

## Configuration OAuth Google Cloud Console

Avant d'utiliser l'authentification Google, vous devez configurer votre projet dans Google Cloud Console:

1. Allez sur https://console.cloud.google.com/
2. Créez un nouveau projet ou sélectionnez-en un existant
3. Activez l'API "Google Sign-In"
4. Allez dans "Identifiants" → "Créer des identifiants" → "ID client OAuth 2.0"
5. Sélectionnez "Application Android"
6. Entrez votre nom de package: `com.precieux.solvelt.logic`
7. Entrez votre empreinte SHA-1: `94:E4:5A:97:0D:86:06:33:C2:EF:3D:F7:BF:BC:3C:26:30:23:22:10`
8. Téléchargez le fichier `google-services.json` (optionnel pour cette version sans Firebase)

## Vérification de l'APK

Pour vérifier que votre APK est correct:
```bash
# Voir les informations de l'APK
aapt dump badging app/build/outputs/apk/debug/app-debug.apk

# Voir le contenu de l'APK
unzip -l app/build/outputs/apk/debug/app-debug.apk

# Signer l'APK (pour release)
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore mykeystore.keystore app-release-unsigned.apk alias_name
```

## Support

En cas de problème, contactez:
- Email: okitakoyprecieux@gmail.com
- Développeur: Précieux Okitakoy
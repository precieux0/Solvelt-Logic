#!/usr/bin/env python3
"""
Script de configuration et de compilation pour SolVelt-Logic
Ce script aide à configurer l'environnement et compiler l'APK.
"""

import os
import sys
import subprocess
import platform
from pathlib import Path

def run_command(cmd, cwd=None):
    """Exécute une commande et retourne le résultat"""
    print(f"\n>>> Exécution: {cmd}")
    result = subprocess.run(cmd, shell=True, cwd=cwd, capture_output=True, text=True)
    if result.returncode != 0:
        print(f"Erreur: {result.stderr}")
        return False, result.stderr
    print(result.stdout)
    return True, result.stdout

def check_java():
    """Vérifie si Java est installé"""
    success, output = run_command("java -version")
    if success:
        print("✓ Java est installé")
        return True
    else:
        print("✗ Java n'est pas installé. Veuillez installer Java 17.")
        return False

def check_android_sdk():
    """Vérifie si Android SDK est configuré"""
    sdk_root = os.environ.get('ANDROID_SDK_ROOT') or os.environ.get('ANDROID_HOME')
    if sdk_root and Path(sdk_root).exists():
        print(f"✓ Android SDK trouvé: {sdk_root}")
        return True
    else:
        print("✗ Android SDK non trouvé. Veuillez configurer ANDROID_SDK_ROOT.")
        return False

def setup_android_sdk():
    """Configure Android SDK automatiquement"""
    home = Path.home()
    sdk_path = home / "Android" / "Sdk"
    
    print("\n=== Configuration d'Android SDK ===")
    
    # Créer le dossier
    sdk_path.mkdir(parents=True, exist_ok=True)
    
    # Télécharger command line tools
    cmdline_url = "https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip"
    zip_file = sdk_path / "cmdline-tools.zip"
    
    if not zip_file.exists():
        print(f"Téléchargement des command line tools...")
        run_command(f"wget -O {zip_file} {cmdline_url}")
    
    # Extraire
    cmdline_tools = sdk_path / "cmdline-tools"
    if not (cmdline_tools / "latest").exists():
        print("Extraction des command line tools...")
        run_command(f"unzip -o {zip_file} -d {sdk_path}")
        (sdk_path / "cmdline-tools" / "latest").mkdir(parents=True, exist_ok=True)
        run_command(f"mv {sdk_path / 'cmdline-tools' / '*'} {cmdline_tools / 'latest'} 2>/dev/null || true")
    
    # Configurer les variables d'environnement
    print("\nAjoutez ces lignes à votre ~/.bashrc ou ~/.zshrc:")
    print(f"""
export ANDROID_SDK_ROOT={sdk_path}
export PATH=$PATH:$ANDROID_SDK_ROOT/cmdline-tools/latest/bin
export PATH=$PATH:$ANDROID_SDK_ROOT/platform-tools
""")
    
    # Installer les composants nécessaires
    print("\nInstallation des composants Android SDK...")
    os.environ['ANDROID_SDK_ROOT'] = str(sdk_path)
    os.environ['PATH'] = os.environ.get('PATH', '') + f":{sdk_path}/cmdline-tools/latest/bin"
    
    run_command("yes | sdkmanager --licenses")
    run_command("sdkmanager 'platform-tools' 'platforms;android-34' 'build-tools;34.0.0'")
    
    return str(sdk_path)

def create_local_properties(sdk_path):
    """Crée le fichier local.properties"""
    with open("local.properties", "w") as f:
        f.write(f"sdk.dir={sdk_path}\n")
    print(f"✓ Fichier local.properties créé avec sdk.dir={sdk_path}")

def build_apk():
    """Compile l'APK"""
    print("\n=== Compilation de l'APK ===")
    
    # Rendre gradlew exécutable
    run_command("chmod +x gradlew")
    
    # Nettoyer
    print("Nettoyage du projet...")
    run_command("./gradlew clean")
    
    # Compiler
    print("Compilation de l'APK debug...")
    success, output = run_command("./gradlew assembleDebug")
    
    if success:
        print("\n✓ Compilation réussie!")
        
        # Trouver l'APK
        apk_path = Path("app/build/outputs/apk/debug/app-debug.apk")
        if apk_path.exists():
            size = apk_path.stat().st_size / (1024 * 1024)  # Convertir en MB
            print(f"\n📱 APK généré:")
            print(f"   Chemin: {apk_path.absolute()}")
            print(f"   Taille: {size:.2f} MB")
            
            # Copier dans le dossier racine
            run_command(f"cp {apk_path} SolVelt-Logic-debug.apk")
            print(f"   Copié vers: SolVelt-Logic-debug.apk")
            
            return True
    else:
        print("\n✗ Échec de la compilation")
        return False

def install_apk():
    """Installe l'APK sur un appareil connecté"""
    print("\n=== Installation de l'APK ===")
    
    # Vérifier si un appareil est connecté
    success, output = run_command("adb devices")
    if "device" in output and "List" not in output.split("\n")[1]:
        print("Appareil trouvé. Installation...")
        run_command("adb install -r SolVelt-Logic-debug.apk")
    else:
        print("Aucun appareil connecté. Veuillez:")
        print("1. Connecter votre téléphone en USB")
        print("2. Activer le débogage USB dans les options développeur")
        print("3. Autoriser le débogage sur votre téléphone")

def main():
    print("=" * 60)
    print("  SolVelt-Logic - Script de Build")
    print("=" * 60)
    
    # Vérifier Java
    if not check_java():
        print("\nVeuillez installer Java 17:")
        print("  Ubuntu/Debian: sudo apt install openjdk-17-jdk")
        print("  macOS: brew install openjdk@17")
        print("  Windows: https://adoptium.net/")
        sys.exit(1)
    
    # Vérifier Android SDK
    sdk_path = os.environ.get('ANDROID_SDK_ROOT') or os.environ.get('ANDROID_HOME')
    
    if not sdk_path or not Path(sdk_path).exists():
        print("\nAndroid SDK non trouvé.")
        response = input("Voulez-vous configurer Android SDK automatiquement? (o/n): ")
        if response.lower() == 'o':
            sdk_path = setup_android_sdk()
        else:
            print("Veuillez configurer ANDROID_SDK_ROOT manuellement.")
            sys.exit(1)
    
    # Créer local.properties
    create_local_properties(sdk_path)
    
    # Compiler
    if build_apk():
        print("\n" + "=" * 60)
        print("  Build terminé avec succès!")
        print("=" * 60)
        
        response = input("\nVoulez-vous installer l'APK sur un appareil connecté? (o/n): ")
        if response.lower() == 'o':
            install_apk()
    else:
        print("\n" + "=" * 60)
        print("  Échec du build")
        print("=" * 60)
        sys.exit(1)

if __name__ == "__main__":
    main()
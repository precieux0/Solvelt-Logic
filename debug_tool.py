#!/usr/bin/env python3
"""
SolVelt Logic - Outil de Débogage et Compilation
Utilité: Faciliter la compilation, l'installation et le débogage de l'application
"""

import os
import subprocess
import sys
import platform
from pathlib import Path
from typing import Optional

class SolVeltDebugger:
    def __init__(self):
        self.project_root = Path(__file__).parent
        self.gradle_wrapper = "gradlew.bat" if platform.system() == "Windows" else "./gradlew"
        
    def run_command(self, cmd: list, description: str = "") -> bool:
        """Exécuter une commande shell"""
        if description:
            print(f"\n{'='*60}")
            print(f"▶ {description}")
            print(f"{'='*60}")
        
        try:
            result = subprocess.run(cmd, cwd=self.project_root)
            return result.returncode == 0
        except Exception as e:
            print(f"❌ Erreur: {e}")
            return False

    def clean_build(self) -> bool:
        """Nettoyer le cache Gradle"""
        return self.run_command(
            [self.gradle_wrapper, "clean"],
            "🧹 Nettoyage du projet..."
        )

    def build_debug(self) -> bool:
        """Compiler en mode débogage"""
        return self.run_command(
            [self.gradle_wrapper, "assembleDebug"],
            "🔨 Compilation Debug..."
        )

    def build_release(self) -> bool:
        """Compiler en mode production"""
        return self.run_command(
            [self.gradle_wrapper, "assembleRelease"],
            "📦 Compilation Release..."
        )

    def install_debug(self, device_id: Optional[str] = None) -> bool:
        """Installer l'APK de débogage sur un appareil"""
        adb_cmd = "adb" if not device_id else f"adb -s {device_id}"
        
        apk_path = self.project_root / "app/build/outputs/apk/debug/app-debug.apk"
        
        if not apk_path.exists():
            print(f"❌ APK non trouvé: {apk_path}")
            return False
        
        cmd = f'{adb_cmd} install -r "{apk_path}"'.split()
        
        return self.run_command(
            cmd,
            "📱 Installation de l'APK Debug..."
        )

    def list_devices(self) -> bool:
        """Lister les appareils connectés"""
        return self.run_command(
            ["adb", "devices"],
            "📋 Appareils connectés..."
        )

    def logcat_filter(self, tag: str = "com.precieux.solvelt.logic") -> bool:
        """Afficher les logs filtrés"""
        return self.run_command(
            ["adb", "logcat", f"{tag}:*", "*:E"],
            f"📊 Logs Logcat (filtré sur {tag})..."
        )

    def check_app(self) -> bool:
        """Vérifier si l'app fonctionne correctement"""
        print("\n✅ Vérifications à effectuer:")
        print("  1. App démarre sans crash")
        print("  2. Splash screen s'affiche 3 secondes")
        print("  3. MainActivity se charge automatiquement")
        print("  4. UI responsive et pas d'ANR")
        print("  5. Logs montrent les initializations")
        return True

    def show_menu(self):
        """Afficher le menu principal"""
        print("\n" + "="*60)
        print("🔧 SolVelt Logic - Outil de Débogage et Compilation")
        print("="*60)
        print("\nOptions disponibles:")
        print("  1. 🧹 Nettoyer le projet (clean)")
        print("  2. 🔨 Compiler en Debug")
        print("  3. 📦 Compiler en Release")
        print("  4. 📱 Installer APK Debug")
        print("  5. 📋 Lister les appareils")
        print("  6. 📊 Afficher les logs (Logcat)")
        print("  7. ✅ Vérifier que tout fonctionne")
        print("  8. 🚀 Compilation + Installation complète")
        print("  9. 🔄 Rebuild complet (Clean + Build + Install)")
        print("  0. ❌ Quitter")
        print("="*60)

    def run(self):
        """Boucle principale"""
        while True:
            self.show_menu()
            choice = input("\nChoisir une option (0-9): ").strip()
            
            if choice == "1":
                self.clean_build()
            elif choice == "2":
                self.build_debug()
            elif choice == "3":
                self.build_release()
            elif choice == "4":
                device_id = input("ID device (vide pour premier device): ").strip()
                self.install_debug(device_id if device_id else None)
            elif choice == "5":
                self.list_devices()
            elif choice == "6":
                self.logcat_filter()
            elif choice == "7":
                self.check_app()
            elif choice == "8":
                if self.build_debug() and self.install_debug():
                    print("\n✅ Installation complète réussie!")
                else:
                    print("\n❌ Erreur pendant l'installation")
            elif choice == "9":
                if (self.clean_build() and 
                    self.build_debug() and 
                    self.install_debug()):
                    print("\n✅ Rebuild complet réussi!")
                else:
                    print("\n❌ Erreur pendant le rebuild")
            elif choice == "0":
                print("\n👋 Au revoir!")
                break
            else:
                print("\n⚠️  Option invalide")
            
            input("\nAppuyer sur Entrée pour continuer...")

def main():
    """Point d'entrée du script"""
    debugger = SolVeltDebugger()
    
    if len(sys.argv) > 1:
        # Mode ligne de commande
        arg = sys.argv[1].lower()
        
        commands = {
            "clean": debugger.clean_build,
            "build": debugger.build_debug,
            "release": debugger.build_release,
            "install": debugger.install_debug,
            "devices": debugger.list_devices,
            "logs": debugger.logcat_filter,
            "check": debugger.check_app,
        }
        
        if arg in commands:
            commands[arg]()
        else:
            print(f"Commande inconnue: {arg}")
            print(f"Commandes disponibles: {', '.join(commands.keys())}")
    else:
        # Mode interactif
        debugger.run()

if __name__ == "__main__":
    main()

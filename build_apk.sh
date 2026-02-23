#!/bin/bash

# Script to build SolVelt-Logic APK

echo "========================================="
echo "Building SolVelt-Logic Debug APK"
echo "========================================="

# Check if Android SDK is available
if [ -z "$ANDROID_SDK_ROOT" ] && [ -z "$ANDROID_HOME" ]; then
    echo "Warning: ANDROID_SDK_ROOT or ANDROID_HOME not set"
    echo "Please set one of these environment variables"
fi

# Clean previous builds
echo "Cleaning previous builds..."
./gradlew clean

# Build debug APK
echo "Building debug APK..."
./gradlew assembleDebug

# Check if build was successful
if [ $? -eq 0 ]; then
    echo ""
    echo "========================================="
    echo "Build successful!"
    echo "========================================="
    
    # Find the APK
    APK_PATH=$(find app/build/outputs/apk/debug -name "*.apk" | head -1)
    
    if [ -n "$APK_PATH" ]; then
        echo ""
        echo "APK location: $APK_PATH"
        echo ""
        echo "APK file details:"
        ls -lh "$APK_PATH"
        echo ""
        echo "APK size:"
        du -h "$APK_PATH"
        
        # Copy to a more accessible location
        cp "$APK_PATH" SolVelt-Logic-debug.apk
        echo ""
        echo "APK copied to: SolVelt-Logic-debug.apk"
    else
        echo "APK not found in expected location"
    fi
else
    echo ""
    echo "========================================="
    echo "Build failed!"
    echo "========================================="
    exit 1
fi
#!/bin/bash
# Shell script to compile and run the Java application on Unix-like systems
# Address Management System - EnterNewAddress Use Case
echo "========================================"
echo "    Address Management System"
echo "========================================"
echo ""
# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "Error: Java is not installed or not in PATH."
    echo "Please install Java and try again."
    exit 1
fi
# Check Java version
JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d '"' -f2 | cut -d '.' -f1)
if [ "$JAVA_VERSION" -lt 11 ]; then
    echo "Warning: Java 11 or higher is recommended."
    echo "Current version: $(java -version 2>&1 | head -n 1)"
    read -p "Continue anyway? (y/n): " -n 1 -r
    echo
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        exit 1
    fi
fi
echo "Compiling Java files..."
echo ""
# Remove old class files if they exist
if [ -f *.class ]; then
    echo "Removing old class files..."
    rm -f *.class
fi
# Compile Java files
echo "Compiling source code..."
javac -encoding UTF-8 addressmanager.java addressmanagementapp.java
if [ $? -ne 0 ]; then
    echo ""
    echo "Compilation failed!"
    echo "Please check for errors in the source code."
    exit 1
fi
echo ""
echo "Compilation successful!"
echo ""
echo "Running Address Management Application..."
echo "========================================"
echo ""
java -Dfile.encoding=UTF-8 AddressManagementApp
echo ""
echo "Application terminated."
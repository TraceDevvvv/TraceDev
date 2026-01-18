#!/bin/bash

# Script to compile and run the ModifyCulturalHeritage Java program
# This script compiles all Java files and runs the main application

# Set colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Print header
printf "${YELLOW}=== Modify Cultural Heritage Java Program ===${NC}\n\n"

# Check if Java is installed
if ! command -v javac &> /dev/null; then
    printf "${RED}Error: Java compiler (javac) not found!${NC}\n"
    printf "Please install Java Development Kit (JDK) version 8 or higher.\n"
    exit 1
fi

if ! command -v java &> /dev/null; then
    printf "${RED}Error: Java runtime (java) not found!${NC}\n"
    printf "Please install Java Runtime Environment (JRE) version 8 or higher.\n"
    exit 1
fi

# Get Java version
JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2)
printf "Java version: ${JAVA_VERSION}\n\n"

# Set the current directory
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"

printf "Compiling Java files...\n"

# Compile all Java files
javac -d . *.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
    printf "${GREEN}Compilation successful!${NC}\n\n"
    
    printf "Running the ModifyCulturalHeritage application...\n"
    printf "${YELLOW}========================================${NC}\n\n"
    
    # Run the main application
    java workspace.ModifyCulturalHeritage_1766884653.ModifyCulturalHeritageApp
    
    EXIT_CODE=$?
    printf "\n${YELLOW}========================================${NC}\n"
    
    if [ $EXIT_CODE -eq 0 ]; then
        printf "${GREEN}Application completed successfully.${NC}\n"
    else
        printf "${RED}Application exited with code: $EXIT_CODE${NC}\n"
    fi
    
    printf "\nCleaning up compiled class files...\n"
    
    # Clean up compiled class files
    rm -f *.class
    
    if [ $? -eq 0 ]; then
        printf "${GREEN}Cleanup complete.${NC}\n"
    else
        printf "${YELLOW}Warning: Some files could not be cleaned up.${NC}\n"
    fi
    
else
    printf "${RED}Compilation failed!${NC}\n"
    printf "Please check for errors in the Java source files.\n"
    exit 1
fi

printf "\n${YELLOW}Script execution complete.${NC}\n"

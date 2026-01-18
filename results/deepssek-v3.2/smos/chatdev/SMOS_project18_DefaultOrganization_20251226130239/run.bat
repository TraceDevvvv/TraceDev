REM Batch file to compile and run the Java application
@echo off
echo ========================================
echo    Address Management System
echo ========================================
echo.
echo Checking Java installation...
where java >nul 2>nul
if %ERRORLEVEL% neq 0 (
    echo Error: Java is not installed or not in PATH.
    echo Please install Java and try again.
    pause
    exit /b 1
)
echo Compiling Java files...
echo.
REM Remove old class files if they exist
if exist *.class (
    echo Removing old class files...
    del *.class
)
echo Compiling source code...
javac -encoding UTF-8 addressmanager.java addressmanagementapp.java
if %ERRORLEVEL% neq 0 (
    echo.
    echo Compilation failed!
    echo Please check for errors in the source code.
    pause
    exit /b 1
)
echo.
echo Compilation successful!
echo.
echo Running Address Management Application...
echo ========================================
echo.
java -Dfile.encoding=UTF-8 AddressManagementApp
echo.
echo Application terminated.
pause
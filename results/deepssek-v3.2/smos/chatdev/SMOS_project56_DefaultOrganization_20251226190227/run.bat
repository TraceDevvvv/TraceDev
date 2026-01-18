@echo off
REM Batch file to compile and run the Java program
echo Compiling Java classes...
javac TeacherDataService.java Main.java
if errorlevel 1 (
    echo Compilation failed!
    pause
    exit /b 1
)
echo Running ViewClassListTeacher application...
echo.
java Main
pause
@echo off
setlocal
set "SCRIPT_DIR=%~dp0"
REM Ensure JavaFX native DLLs are on PATH
set "PATH=%SCRIPT_DIR%bin;%PATH%"
REM Launch with JavaFX modules on the module-path
java --module-path "%SCRIPT_DIR%lib" --add-modules javafx.controls,javafx.fxml -jar "%SCRIPT_DIR%Video-Game-Shop-Simulator.jar"
endlocal


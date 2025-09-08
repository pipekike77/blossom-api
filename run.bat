@echo off
echo  Iniciando Blossom API...

:: Verifica si mvnw está presente
IF NOT EXIST "mvnw.cmd" (
    echo  Maven Wrapper (mvnw.cmd) no encontrado. Asegúrate de estar en la raíz del proyecto.
    pause
    exit /b 1
)

:: Ejecuta con Maven Wrapper
call mvnw.cmd spring-boot:run
pause

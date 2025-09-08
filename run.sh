#!/bin/bash

echo " Iniciando Blossom API..."

# Verifica si Java está instalado
if ! command -v java &> /dev/null
then
    echo " Java no está instalado. Por favor, instala Java 17 o superior."
    exit 1
fi

# Verifica si Maven Wrapper existe
if [ ! -f "./mvnw" ]; then
    echo " Maven Wrapper (mvnw) no encontrado. Asegúrate de estar en la raíz del proyecto."
    exit 1
fi

# Ejecuta la app con Maven Wrapper
chmod +x mvnw
./mvnw spring-boot:run

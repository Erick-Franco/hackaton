@echo off
echo ===========================================
echo    COMPILANDO MICROSERVICIO CATALOGO
echo ===========================================

cd /d "D:\CatalogoRepositorio-master\ms-catalogo"

echo.
echo 1. Limpiando proyecto...
call mvn clean

echo.
echo 2. Compilando...
call mvn compile

echo.
echo 3. Resultado de la compilacion:
if %ERRORLEVEL% == 0 (
    echo ✅ COMPILACION EXITOSA
    echo.
    echo Para ejecutar la aplicacion:
    echo mvn spring-boot:run
    echo.
    echo Endpoints disponibles una vez ejecutado:
    echo - Health Check: http://localhost:8080/actuator/health
    echo - Swagger UI: http://localhost:8080/doc/swagger-ui.html
    echo - API Productos: http://localhost:8080/api/productos
    echo - API Categorias: http://localhost:8080/api/categorias
) else (
    echo ❌ ERROR EN LA COMPILACION
    echo Revisa los errores mostrados arriba
)

echo.
pause

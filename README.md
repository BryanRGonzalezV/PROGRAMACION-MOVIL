# Práctica 7 — Anatomía Android y Composables Básicos

## Ejercicio 1: Preguntas de Análisis

### 1. ¿Qué hace la función `setContent {}` en MainActivity.kt?
Define el diseño de la interfaz de usuario mediante Jetpack Compose. Actúa como el puente entre la actividad de Android (`ComponentActivity`) y los componentes visuales `@Composable`, reemplazando el antiguo método `setContentView(R.layout.xml)`.

### 2. ¿Qué significan `minSdk`, `targetSdk` y `compileSdk` en `build.gradle.kts`?
* **`minSdk`:** Es la versión mínima del sistema operativo Android que requiere la aplicación para poder ser instalada y ejecutada (en esta práctica, API 24 / Android 7.0).
* **`compileSdk`:** Es la versión del SDK de Android utilizada para compilar la aplicación. Define qué APIs y funciones del sistema operativo están disponibles para el desarrollador durante la construcción de la app.
* **`targetSdk`:** Es la versión del SDK para la cual la app fue optimizada y probada. Indica al sistema operativo Android que aplique los comportamientos de compatibilidad correspondientes a esa versión.

### 3. ¿Para qué sirve el archivo `libs.versions.toml`?
Es el catálogo de versiones centralizado de Gradle (*Version Catalog*). Permite gestionar en un solo lugar las versiones de las librerías, dependencias y plugins del proyecto, evitando inconsistencias entre los archivos de construcción de los módulos.

### 4. ¿Qué anotación convierte una función en un componente de UI en Compose?
La anotación `@Composable`.

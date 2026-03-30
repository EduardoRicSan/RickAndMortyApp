# Rick and Morty Challenge - Android App

## 🧩 Overview

Aplicación Android desarrollada con principios de arquitectura moderna, enfocada en **escalabilidad, mantenibilidad y rendimiento**. El objetivo principal es consumir y mostrar información de personajes de forma eficiente, ofreciendo una experiencia fluida y confiable.

---

## 🏗️ Arquitectura

El proyecto sigue **Clean Architecture + MVVM**, asegurando una clara separación de responsabilidades:

- **Presentation:** UI con Jetpack Compose + ViewModels  
- **Domain:** UseCases y modelos de negocio  
- **Data:** Repositorios, fuentes de datos y mappers  

### Principios aplicados

- SOLID  
- Single Source of Truth  
- Unidirectional Data Flow  
- Separation of Concerns  

---

## ⚙️ Decisiones técnicas

### UI & Experiencia de Usuario
- Uso de **Jetpack Compose** para una UI declarativa y mantenible.
- Creación de un **Design System** propio (`RMCard`, `RMChip`, `RMImage`, etc.) para asegurar consistencia y reutilización.
- Manejo explícito de estados de UI:
  - Loading (global)
  - Error (pantalla completa con retry)
  - Empty state
  - Pagination loading (append)

### Manejo de datos
- Integración de **Paging 3** para manejar grandes volúmenes de datos de forma eficiente.
- Uso de **StateFlow** para flujos reactivos y predecibles.
- Implementación del patrón `NetworkResult` (Success / Error / Loading).

### Networking
- Consumo de APIs desacoplado mediante repositorios.
- Manejo centralizado de errores con `safeApiCall`.

### Inyección de dependencias
- Uso de **Dagger Hilt** para mejorar escalabilidad y testabilidad.

### Imágenes
- Uso de **Coil 3** optimizado para Compose.

### Navegación
- Implementación con **Navigation Compose**, desacoplada de la UI.

### Splash Screen
- Uso de la API moderna (`androidx.core.splashscreen`).

---

## 🧠 Trade-offs

- Se priorizó claridad arquitectónica sobre velocidad de implementación.
- No se implementó caching offline completo para mantener el alcance enfocado.
- Se eligió MVVM con una base preparada para evolucionar a MVI si es necesario.

---

## ⏳ Qué quedó fuera por falta de tiempo

- Ampliar la cobertura de **tests unitarios** (aunque se implementaron algunos, no cubren todos los casos necesarios).
- Implementación de **UI tests** (Compose UI Testing / Espresso).
- Animaciones avanzadas en transiciones.
- Estrategia de caching más robusta (Room + RemoteMediator).
- Soporte offline completo.
- Accesibilidad (TalkBack, contrastes, etc.).
- Optimización visual avanzada (skeleton loaders / shimmer).

---

## 🚀 Mejoras futuras

- Migrar completamente a **MVI** para un manejo de estado más predecible.
- Incrementar cobertura de testing (unit + UI + integration).
- Implementar estrategia **offline-first** con Room + RemoteMediator.
- Integrar **CI/CD pipelines** (lint, tests, builds automatizados).
- Optimizar recomposiciones en Compose (profiling).
- Mejorar animaciones y microinteracciones.
- Evolucionar el Design System a un sistema documentado y versionado.

---

## 🧪 Estrategia de Testing

La arquitectura fue diseñada para facilitar el testing desde su base.

### Implementado
- Tests unitarios en:
  - UseCases
  - ViewModels
- Uso de:
  - **JUnit**
  - **Mockito / Mocks**

### Beneficios
- Bajo acoplamiento → fácil mockeo  
- Uso de **StateFlow** → validación clara de estados  
- Separación de capas → pruebas aisladas  

### Próximos pasos
- Incrementar cobertura en repositorios y casos borde  
- Implementar **UI Testing**:
  - Jetpack Compose Testing  
  - Espresso  
- Integrar testing en CI/CD  
- Adoptar TDD de forma más estricta  

---

## 🤖 Uso de Inteligencia Artificial

Se utilizó inteligencia artificial como herramienta de apoyo para resolver problemas específicos, principalmente en:

- Configuración y debugging de Navigation Compose  
- Integración de Dagger Hilt  
- Resolución de issues en UI y manejo de estados  

El uso de AI fue enfocado en acelerar la resolución de problemas, manteniendo control total sobre las decisiones técnicas y arquitectura.

---

## 📱 Stack tecnológico

- Kotlin  
- Jetpack Compose  
- Paging 3  
- Dagger Hilt  
- Coil 3  
- Coroutines & Flow  
- Clean Architecture  
- MVVM (preparado para MVI)  

---

## ▶️ Cómo ejecutar el proyecto

### Requisitos

- Android Studio (última versión estable recomendada)
- JDK 17
- SDK de Android configurado
- Conexión a internet (para consumo de API)

### Pasos

1. Clonar el repositorio:
   ```bash
   git clone git@github.com:EduardoRicSan/RickAndMortyApp.git
   Abrir el proyecto en Android Studio.
2. Sincronizar el proyecto con Gradle.
3. Ejecutar la app en:
    - Emulador
    - Dispositivo físico

Notas
No se requiere configuración adicional de API keys.
El proyecto utiliza Version Catalogs para la gestión de dependencias.
Compatible con dispositivos desde Android 8+ (API 26 en adelante).

## 👨‍💻 Author

Jesús Eduardo Rico Sandoval

## 📱 Demo

![Rick and Morty GIF](https://media4.giphy.com/media/v1.Y2lkPTc5MGI3NjExY2FsOWNnamdjcGd3aHlzY2VrNGV6NDdicHNhcDFwMzI4eXZwM2FwOSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/COUmRMUNI74HQ1fXTl/giphy.gif)

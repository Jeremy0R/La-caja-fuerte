# 📦 La Caja Fuerte - Math Learning Game

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Android Studio](https://img.shields.io/badge/Android_Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white)

> Una aplicación educativa gamificada con estética retro (pixel-art) diseñada para reforzar conocimientos en matemáticas básicas para niños de 6 a 12 años.

<div align="center">
  <img src="app/src/main/res/drawable/cfsplash.gif" alt="La Caja Fuerte Splash" width="250"/>
</div>

---

## 🎮 Características Principales

* **Estética Pixel-Art:** Interfaz de usuario (UI) completamente personalizada mediante XML Drawables, emulando botones y entornos de consolas clásicas.
* **Motor Matemático Dinámico:** Generación procedimental de acertijos con niveles de dificultad escalables.
* **Progresión Visual:** Barra de progreso interactiva y sistema de recompensas ("Coronas") al completar niveles.
* **Navegación Fluida:** Transiciones entre múltiples *Activities* (Splash, Menú, Niveles, Juego, Victoria) manteniendo un flujo de experiencia de usuario (UX) óptimo.

---

## ⚙️ Arquitectura y Calidad de Código

Este proyecto fue desarrollado aplicando rigurosamente los principios de **Programación Orientada a Objetos (POO)** para garantizar un código modular, limpio y escalable:

* **Herencia y Clases Abstractas:** Implementación de una clase base `OperacionMatematica` de la cual derivan las clases específicas (`Suma`, `Resta`, `Multiplicacion`, `Division`).
* **Sobrecarga (Overloading):** Uso de sobrecarga de constructores para gestionar la dificultad y sobrecarga de métodos (`verificarRespuesta()`) para el manejo seguro de entradas de datos (Int vs String).
* **Manejo de Errores:** Implementación de bloques `try-catch` para evitar cierres inesperados por entradas de teclado anómalas.
* **Persistencia de Datos:** Sistema de guardado local para mantener el progreso del usuario de forma persistente.

---

## 📸 Pantallas de la Aplicación

<div align="center">
  <img width="738" height="1600" alt="image" src="https://github.com/user-attachments/assets/4179e4c9-b877-4c5b-a0b1-8ef0e7fc14f9" />
 <img width="738" height="1600" alt="image" src="https://github.com/user-attachments/assets/b970f9d4-7b62-4f94-9907-b1380cfdb07a" />
 <img width="1080" height="2340" alt="image" src="https://github.com/user-attachments/assets/90e84b8e-0cb6-4ea8-a7a1-7ffe55aa260c" />
</div>

---

## 🚀 Instalación y Pruebas

Para clonar y correr esta aplicación en tu entorno local:

1. Clona este repositorio:
   ```bash
   git clone [https://github.com/Jeremy0R/La-caja-fuerte.git](https://github.com/Jeremy0R/La-caja-fuerte.git)

Abre el proyecto en Android Studio.

Sincroniza los archivos de Gradle (Sync Project with Gradle Files).

Compila y ejecuta en un emulador o dispositivo físico con Android (Mínimo recomendado API 24).

👨‍💻 Autor
Jeremy Osorio Ramos * Estudiante de Ingeniería en Computación.

[Perfil de GitHub](https://github.com/Jeremy0R)

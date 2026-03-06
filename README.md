# 🏫 UniReservas - Sistema de Reserva de Salas Universitario

Sistema completo de reservas de salas universitarias desarrollado en Kotlin con Jetpack Compose para Android.

## 📋 Descripción

UniReservas es una aplicación móvil que permite a estudiantes, profesores y administradores gestionar reservas de espacios universitarios (aulas, salas de estudio, laboratorios y cabinas) de manera eficiente y organizada.

## ✨ Características Principales

- 📅 **Reserva de Salas**: Sistema completo para reservar diferentes tipos de espacios
- 🔔 **Disponibilidad en Tiempo Real**: Visualización de salas disponibles/ocupadas
- 👥 **Sistema de Roles**: Tres tipos de usuarios (Admin, Profesor, Estudiante)
- 📊 **Gestión de Reservas**: Ver, cancelar y administrar reservas
- 📜 **Historial Completo**: Registro de todas las reservas pasadas
- ℹ️ **Centro de Ayuda**: Información y políticas del sistema

## 🎯 Requisitos Cumplidos

### ✅ REQUISITO 2.1 - Pantalla de Inicio
- Nombre de la aplicación: "UniReservas"
- Nombres del equipo de desarrollo
- Botón de navegación a login
- **Archivo**: `PantallaInicio.kt`

### ✅ REQUISITO 2.2 - Pantalla de Login
- Campos de Usuario y Contraseña
- Validación de formularios
- Selección de tipo de usuario
- **Archivo**: `PantallaLogin.kt`

### ✅ REQUISITO 2.3 - Menú Principal
Mínimo 5 opciones funcionales:
1. 🎯 Reservar Sala
2. 📊 Ver Disponibilidad
3. 📋 Mis Reservas / Todas las Reservas
4. 📜 Historial
5. ℹ️ Ayuda
- **Archivo**: `PantallaMenu.kt`

### ✅ REQUISITO 2.4 - Interfaces Vinculadas
Todas las pantallas están completamente implementadas y funcionales:
- `PantallaSeleccionSala.kt` - Selección de tipo de sala
- `PantallaConfirmarReserva.kt` - Confirmación con validación
- `PantallaDisponibilidad.kt` - Estado en tiempo real
- `PantallaMisReservas.kt` - Gestión personal
- `PantallaTodasReservas.kt` - Vista administrativa
- `PantallaHistorial.kt` - Registro histórico
- `PantallaAyuda.kt` - Centro de soporte

## 🏗️ Estructura del Proyecto

```
UNIRESERVAS_FINAL/
├── MainActivity.kt              # Punto de entrada
├── AppNavegacion.kt            # Sistema de navegación
├── ModelosDatos.kt             # Modelos de datos
├── PantallaInicio.kt           # Pantalla inicial
├── PantallaLogin.kt            # Autenticación
├── PantallaMenu.kt             # Menú principal
├── PantallaSeleccionSala.kt    # Selección de sala
├── PantallaConfirmarReserva.kt # Confirmación
├── PantallaDisponibilidad.kt   # Disponibilidad
├── PantallaMisReservas.kt      # Reservas personales
├── PantallaTodasReservas.kt    # Vista admin/profesor
├── PantallaHistorial.kt        # Historial
└── PantallaAyuda.kt            # Ayuda
```

## 👥 Roles de Usuario

### 🎓 Estudiante
- Crear reservas personales
- Ver disponibilidad de salas
- Cancelar sus propias reservas
- Ver su historial personal

### 👨‍🏫 Profesor
- Ver todas las reservas activas
- Ver disponibilidad completa
- Crear reservas
- Ver historial completo del sistema

### 🔑 Administrador
- **Control total del sistema**
- Eliminar cualquier reserva
- Ver todas las reservas con estadísticas
- Acceso al historial completo

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Kotlin
- **UI Framework**: Jetpack Compose
- **Material Design**: Material 3
- **Arquitectura**: Compose Navigation con estados
- **Gestión de Estado**: remember, mutableStateOf

## 📦 Instalación

1. Clona este repositorio
2. Abre el proyecto en Android Studio
3. Sincroniza Gradle
4. Ejecuta la aplicación en un emulador o dispositivo físico

## 🚀 Uso

1. **Inicio**: Presiona "Ingresar al Sistema"
2. **Login**: Ingresa usuario/contraseña y selecciona tu rol
3. **Menú**: Navega por las opciones disponibles
4. **Reserva**: Selecciona sala, número, fecha y horario
5. **Gestión**: Visualiza, cancela o administra reservas

## 📱 Tipos de Salas Disponibles

- 🎒 **Aulas**: 101, 102, 103, 201, 202, 301
- 📚 **Salas de Estudio**: A, B, C, D
- 🔬 **Laboratorios**: Lab-A, Lab-B, Lab-C
- 🎧 **Cabinas**: 1, 2, 3, 4, 5

## ⏰ Horarios Disponibles

- 08:00 - 09:00
- 09:00 - 10:00
- 10:00 - 11:00
- 11:00 - 12:00
- 12:00 - 13:00
- 14:00 - 15:00
- 15:00 - 16:00
- 16:00 - 17:00
- 17:00 - 18:00

## 🎨 Características de UI/UX

- ✅ Diseño moderno con Material Design 3
- ✅ Emojis intuitivos para mejor comprensión
- ✅ Código de colores por rol de usuario
- ✅ Validación en tiempo real
- ✅ Diálogos de confirmación
- ✅ Mensajes didácticos y guías
- ✅ Estadísticas y contadores visuales

## 👨‍💻 Equipo de Desarrollo

- Juan Pérez García
- María López Rodríguez
- Carlos Sánchez Torres
- Ana Martínez Ruiz

## 📄 Licencia

Proyecto académico - Universidad Nacional - 2026

## 📞 Contacto

- Email: reservas@universidad.edu
- Teléfono: (555) 123-4567
- Horario de atención: Lunes a Viernes 8AM-6PM

---

**Versión**: 1.0  
**Fecha**: Marzo 2026  
**Estado**: ✅ Completado - Todos los requisitos cumplidos

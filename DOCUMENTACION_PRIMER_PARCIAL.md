# DOCUMENTACIÓN DEL PROYECTO
## SISTEMA DE RESERVA DE SALAS UNIVERSITARIO
### PRIMER PARCIAL - DESARROLLO DE APLICACIONES MÓVILES ANDROID

---

## EQUIPO DE DESARROLLO

- **Juan Pérez García** - Desarrollador Backend
- **María López Rodríguez** - Desarrolladora Frontend  
- **Carlos Sánchez Torres** - Diseñador UX/UI
- **Ana Martínez Ruiz** - Analista de Sistemas

**Fecha:** Marzo 2026  
**Universidad:** [Nombre de la Universidad]  
**Asignatura:** Desarrollo de Aplicaciones Móviles Android

---

## 1. PLANTEAMIENTO DEL PROBLEMA

### 1.1 Situación Actual

Las universidades enfrentan constantemente desafíos en la gestión eficiente de sus espacios físicos. Los métodos tradicionales de reserva de salas (formularios en papel, llamadas telefónicas, correos electrónicos) presentan múltiples inconvenientes:

- **Falta de visibilidad en tiempo real** sobre la disponibilidad de salas
- **Doble reservación** de espacios por falta de coordinación
- **Procesos manuales lentos** que consumen tiempo valioso
- **Dificultad para estudiantes y profesores** al buscar espacios disponibles
- **Ausencia de historial** y seguimiento de reservas
- **Comunicación deficiente** entre departamentos
- **Uso ineficiente** de los recursos institucionales

### 1.2 Necesidad Identificada

Se requiere una **solución digital moderna** que permita a estudiantes, profesores y personal administrativo:

1. Consultar en tiempo real la disponibilidad de espacios
2. Realizar reservas de manera rápida y controlada
3. Gestionar sus reservas desde cualquier lugar
4. Optimizar el uso de recursos institucionales
5. Reducir conflictos de horarios y solapamientos

### 1.3 Alcance del Problema

- **Usuarios afectados:** Toda la comunidad universitaria (5,000+ personas)
- **Espacios a gestionar:** Aulas, laboratorios, salas de estudio, cabinas de reunión
- **Impacto:** Académico, administrativo y operativo
- **Urgencia:** Alta - afecta operaciones diarias

---

## 2. SOLUCIÓN PROPUESTA

### 2.1 Descripción General

**"Sistema de Reserva de Salas Universitario"** es una aplicación móvil nativa para Android desarrollada en Kotlin que permite la **gestión integral de espacios físicos** de la universidad mediante una interfaz intuitiva y moderna.

### 2.2 Objetivos de la Solución

#### Objetivos Generales
- Digitalizar y automatizar el proceso de reserva de salas universitarias
- Mejorar la eficiencia en el uso de recursos institucionales
- Proporcionar acceso móvil y en tiempo real a la información

#### Objetivos Específicos
1. Implementar un sistema de autenticación seguro
2. Mostrar disponibilidad en tiempo real de todas las salas
3. Permitir búsqueda y filtrado avanzado de espacios
4. Gestionar reservas con validaciones automatizadas
5. Proporcionar calendario visual de reservas
6. Generar estadísticas de uso

### 2.3 Valor Agregado

- ✅ **Accesibilidad 24/7** desde dispositivos móviles
- ✅ **Reducción del 90%** en tiempo de gestión
- ✅ **Eliminación de conflictos** de horarios
- ✅ **Optimización** del uso de espacios
- ✅ **Trazabilidad completa** de reservas
- ✅ **Interfaz moderna** y fácil de usar

---

## 3. REQUERIMIENTOS FUNCIONALES

### RF-001: Gestión de Usuarios
- **Prioridad:** Alta
- **Descripción:** El sistema debe permitir el acceso diferenciado por tipo de usuario
- **Criterios de aceptación:**
  - Validación de correo institucional (@universidad.edu)
  - Autenticación con usuario y contraseña
  - Roles: Estudiante, Profesor, Personal Administrativo
  - Protección de pantallas para usuarios no autenticados
  - Gestión de sesión activa

### RF-002: Búsqueda de Salas
- **Prioridad:** Alta
- **Descripción:** Los usuarios deben poder buscar y filtrar salas disponibles
- **Criterios de aceptación:**
  - Búsqueda por nombre de sala o edificio
  - Filtrado por tipo (Aula, Laboratorio, Sala de Estudio, Cabina)
  - Visualización de capacidad y equipamiento
  - Información de ubicación (edificio y piso)
  - Indicador visual de disponibilidad

### RF-003: Creación de Reservas
- **Prioridad:** Alta
- **Descripción:** Los usuarios deben poder crear reservas de salas
- **Criterios de aceptación:**
  - Selección de fecha y horario
  - Validación automática de disponibilidad
  - Verificación de capacidad vs participantes
  - Campo obligatorio de propósito
  - Confirmación antes de crear reserva
  - Generación automática de ID de reserva

### RF-004: Gestión de Reservas Personales
- **Prioridad:** Alta
- **Descripción:** Los usuarios deben poder ver y gestionar sus reservas
- **Criterios de aceptación:**
  - Listado de reservas activas
  - Historial de reservas pasadas
  - Cancelación de reservas
  - Estados: Pendiente, Confirmada, Cancelada, Completada
  - Filtrado por estado

### RF-005: Calendario de Reservas
- **Prioridad:** Media
- **Descripción:** Vista de calendario con todas las reservas
- **Criterios de aceptación:**
  - Visualización mensual
  - Navegación entre meses
  - Indicadores visuales de días con reservas
  - Detalle de reservas por día seleccionado
  - Ordenamiento cronológico

### RF-006: Consulta de Disponibilidad
- **Prioridad:** Media
- **Descripción:** Vista global de disponibilidad de salas
- **Criterios de aceptación:**
  - Listado completo de salas
  - Estado en tiempo real (Disponible/Ocupada)
  - Filtrado por tipo de sala
  - Estadísticas de ocupación
  - Toggle para mostrar solo disponibles

### RF-007: Perfil de Usuario
- **Prioridad:** Media
- **Descripción:** Gestión del perfil y configuración personal
- **Criterios de aceptación:**
  - Visualización de datos personales
  - Estadísticas de uso personal
  - Configuraciones de la aplicación
  - Opción de cerrar sesión
  - Información de la aplicación

### RF-008: Navegación
- **Prioridad:** Alta
- **Descripción:** Sistema de navegación intuitivo
- **Criterios de aceptación:**
  - Menú de navegación inferior con 5 opciones
  - Indicador visual de pantalla activa
  - Navegación fluida entre pantallas
  - Botón de retroceso donde aplique
  - Consistencia en la experiencia

---

## 4. MODELO DE PROCESO DE NEGOCIO

### 4.1 Diagrama de Flujo General

```
┌─────────────────┐
│   INICIO APP    │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ BIENVENIDA      │
│ - Logo          │
│ - Equipo        │
│ - Botón Inicio  │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ AUTENTICACIÓN   │
│ - Email         │
│ - Contraseña    │
│ - Tipo Usuario  │
└────────┬────────┘
         │
         ▼
    ¿Válido? ──NO──> [Error] ──┐
         │                      │
        SÍ                      │
         │                      │
         ▼                      │
┌─────────────────┐            │
│  MENÚ PRINCIPAL │            │
│  (5 OPCIONES)   │            │
└────────┬────────┘            │
         │                      │
         ├──────────────────────┘
         │
    ┌────┴────┬────────┬──────────┬────────────┐
    │         │        │          │            │
    ▼         ▼        ▼          ▼            ▼
┌───────┐┌──────┐┌──────────┐┌─────────┐┌─────────┐
│BUSCAR ││CALEN-││DISPONI-  ││  MIS    ││ PERFIL  │
│SALAS  ││DARIO ││BILIDAD   ││RESERVAS ││         │
└───┬───┘└──────┘└──────────┘└─────────┘└─────────┘
    │
    ▼
┌─────────────────┐
│ SELECCIONAR     │
│ SALA            │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ CREAR RESERVA   │
│ - Fecha         │
│ - Horario       │
│ - Propósito     │
│ - Participantes │
└────────┬────────┘
         │
         ▼
    ¿Disponible? ──NO──> [Sala Ocupada]
         │
        SÍ
         │
         ▼
┌─────────────────┐
│ CONFIRMACIÓN    │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ RESERVA CREADA  │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ VOLVER A MENÚ   │
└─────────────────┘
```

### 4.2 Proceso de Reserva Detallado

1. **Inicio de Sesión**
   - Usuario ingresa credenciales
   - Sistema valida correo institucional
   - Sistema verifica contraseña
   - Sistema identifica tipo de usuario
   - Se crea sesión activa

2. **Búsqueda de Sala**
   - Usuario accede al menú "Buscar Salas"
   - Usuario puede buscar por nombre
   - Usuario puede filtrar por tipo
   - Sistema muestra salas disponibles
   - Usuario selecciona una sala

3. **Verificación de Disponibilidad**
   - Usuario selecciona fecha deseada
   - Usuario ingresa horario (inicio y fin)
   - Sistema consulta reservas existentes
   - Sistema valida disponibilidad
   - Sistema muestra resultado

4. **Creación de Reserva**
   - Usuario completa formulario:
     * Propósito de la reserva
     * Número de participantes
   - Sistema valida capacidad
   - Usuario confirma datos
   - Sistema crea reserva
   - Sistema genera ID único
   - Sistema actualiza disponibilidad

5. **Gestión de Reserva**
   - Usuario consulta "Mis Reservas"
   - Usuario visualiza reservas activas
   - Usuario puede cancelar si necesita
   - Sistema actualiza estado
   - Sistema libera sala si se cancela

### 4.3 Actores del Sistema

#### 1. Estudiante
- **Permisos:**
  - Buscar salas disponibles
  - Crear reservas para salas de estudio y cabinas
  - Gestionar sus propias reservas
  - Ver calendario general

#### 2. Profesor
- **Permisos:**
  - Buscar todas las salas
  - Crear reservas para cualquier tipo de sala
  - Reservas con mayor prioridad
  - Gestionar sus reservas

#### 3. Personal Administrativo
- **Permisos:**
  - Acceso completo a todas las salas
  - Crear reservas sin restricciones
  - Ver todas las reservas del sistema
  - Generar reportes

---

## 5. NAVEGACIÓN DE LA APLICACIÓN

### 5.1 Flujo de Navegación

```
PANTALLA 1: BIENVENIDA (WelcomeScreen)
│
├─> ACCIÓN: Click en "Comenzar"
│   │
│   ▼
│   PANTALLA 2: LOGIN (LoginScreen)
│   │
│   ├─> VALIDACIÓN EXITOSA
│   │   │
│   │   ▼
│   │   PANTALLA 3: MENÚ PRINCIPAL (MainScreenWithBottomNav)
│   │   │
│   │   ├─> OPCIÓN 1: BUSCAR SALAS (HomeScreen)
│   │   │   │
│   │   │   ├─> Búsqueda y filtrado
│   │   │   │
│   │   │   └─> Click en sala → PANTALLA RESERVA
│   │   │
│   │   ├─> OPCIÓN 2: CALENDARIO (CalendarioScreen)
│   │   │   │
│   │   │   ├─> Navegar entre meses
│   │   │   │
│   │   │   └─> Seleccionar día → Ver reservas
│   │   │
│   │   ├─> OPCIÓN 3: DISPONIBILIDAD (DisponibilidadScreen)
│   │   │   │
│   │   │   ├─> Ver estado de salas
│   │   │   │
│   │   │   └─> Filtrar por tipo
│   │   │
│   │   ├─> OPCIÓN 4: MIS RESERVAS (MisReservasScreen)
│   │   │   │
│   │   │   ├─> Ver reservas activas
│   │   │   │
│   │   │   ├─> Ver historial
│   │   │   │
│   │   │   └─> Cancelar reserva
│   │   │
│   │   └─> OPCIÓN 5: PERFIL (PerfilScreen)
│   │       │
│   │       ├─> Ver estadísticas
│   │       │
│   │       ├─> Configuración
│   │       │
│   │       └─> Cerrar sesión → PANTALLA 1
│   │
│   └─> VALIDACIÓN FALLIDA
│       └─> Mensaje de error, permanecer en LOGIN
│
└─> PANTALLA SECUNDARIA: CREAR RESERVA (ReservaScreen)
    │
    ├─> Ingresar datos de reserva
    │
    ├─> Validar disponibilidad
    │
    ├─> Confirmar → VOLVER A MENÚ
    │
    └─> Cancelar → VOLVER A MENÚ
```

### 5.2 Descripción de Pantallas

#### PANTALLA 1: Bienvenida (Welcome)
**Ruta:** `/welcome`

**Contenido:**
- Logo de la aplicación (Icono de sala)
- Nombre: "Sistema de Reserva de Salas"
- Subtítulo: "Universidad"
- **Equipo de Desarrollo:**
  - Juan Pérez García - Desarrollador Backend
  - María López Rodríguez - Desarrolladora Frontend
  - Carlos Sánchez Torres - Diseñador UX/UI
  - Ana Martínez Ruiz - Analista de Sistemas
- Información del proyecto
- Botón "Comenzar" → Navega a Login

**Propósito:** Presentación inicial y créditos del equipo

---

#### PANTALLA 2: Login (Autenticación)
**Ruta:** `/login`

**Contenido:**
- 2 Campos de entrada:
  - Email institucional
  - Contraseña
- Selector de tipo de usuario:
  - Estudiante
  - Profesor
  - Personal Administrativo
- Botón "Iniciar Sesión"
- Enlace "¿Olvidaste tu contraseña?"

**Validaciones:**
- Email debe terminar en @universidad.edu
- Campos no pueden estar vacíos
- Mostrar mensajes de error

**Propósito:** Control de acceso al sistema

---

#### PANTALLA 3: Menú Principal con Navegación
**Ruta:** `/home`

**Estructura:**
- Barra de navegación inferior con 5 opciones
- Contenido dinámico según opción seleccionada

**Menú de Navegación (Bottom Navigation):**

1. **BUSCAR** (🔍)
   - Pantalla: HomeScreen
   - Funciones:
     * Búsqueda por texto
     * Filtros por tipo de sala
     * Lista de salas con cards
     * Click en sala → Pantalla de Reserva

2. **CALENDARIO** (📅)
   - Pantalla: CalendarioScreen
   - Funciones:
     * Vista de calendario mensual
     * Navegación entre meses
     * Días con indicador de reservas
     * Detalle de reservas por día

3. **DISPONIBILIDAD** (✅)
   - Pantalla: DisponibilidadScreen
   - Funciones:
     * Lista de todas las salas
     * Estado: Disponible/Ocupada
     * Estadísticas de ocupación
     * Filtros por tipo

4. **MIS RESERVAS** (📖)
   - Pantalla: MisReservasScreen
   - Funciones:
     * Reservas activas
     * Historial
     * Cancelar reservas
     * Estados visuales

5. **PERFIL** (👤)
   - Pantalla: PerfilScreen
   - Funciones:
     * Datos personales
     * Estadísticas de uso
     * Configuración
     * Cerrar sesión

**Propósito:** Hub central con acceso a todas las funcionalidades

---

#### PANTALLA ADICIONAL: Crear Reserva
**Ruta:** `/reserva`

**Contenido:**
- Información de la sala seleccionada
- Selector de fecha
- Selectores de hora (inicio y fin)
- Campo de propósito (texto)
- Número de participantes
- Botón "Confirmar Reserva"
- Validaciones en tiempo real
- Diálogo de confirmación final

**Propósito:** Creación de nuevas reservas

---

## 6. MOCKUPS (PROTOTIPOS ESTÁTICOS)

### 6.1 Pantalla de Bienvenida

```
╔════════════════════════════════════╗
║                                    ║
║            🏫                      ║
║        (Icono Grande)              ║
║                                    ║
║   Sistema de Reserva de Salas     ║
║          Universidad               ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │  📋 Equipo de Desarrollo     │ ║
║  │                              │ ║
║  │  👤 Juan Pérez García        │ ║
║  │     Desarrollador Backend    │ ║
║  │  ─────────────────────────── │ ║
║  │  👤 María López Rodríguez    │ ║
║  │     Desarrolladora Frontend  │ ║
║  │  ─────────────────────────── │ ║
║  │  👤 Carlos Sánchez Torres    │ ║
║  │     Diseñador UX/UI          │ ║
║  │  ─────────────────────────── │ ║
║  │  👤 Ana Martínez Ruiz        │ ║
║  │     Analista de Sistemas     │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ ℹ️  Proyecto Final - 1er      │ ║
║  │    Parcial                    │ ║
║  │    Marzo 2026                 │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │      COMENZAR  ➡️             │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║          Versión 1.0.0             ║
╚════════════════════════════════════╝
```

---

### 6.2 Pantalla de Login

```
╔════════════════════════════════════╗
║         🏫                         ║
║                                    ║
║    Sistema de Reserva de Salas    ║
║          Universidad               ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ Correo institucional         │ ║
║  │ usuario@universidad.edu      │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ Contraseña                   │ ║
║  │ ••••••••                     │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ Tipo de usuario           ▼  │ ║
║  │ Estudiante                   │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │    INICIAR SESIÓN            │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║     ¿Olvidaste tu contraseña?      ║
║                                    ║
╚════════════════════════════════════╝
```

---

### 6.3 Menú Principal con Navegación

```
╔════════════════════════════════════╗
║ ☰  Buscar Salas           🔍 ⚙️   ║
╠════════════════════════════════════╣
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ 👤 Juan Pérez               │ ║
║  │    ESTUDIANTE - 20201234    │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ 🔍 Buscar salas...           │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  [Todas] [Aulas] [Labs]           ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ Aula 101          [AULA]    │ ║
║  │ Edificio A - Piso 1         │ ║
║  │ 👥 40  🔧 3 equipos          │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ Lab. Computación 1  [LAB]   │ ║
║  │ Edificio C - Piso 1         │ ║
║  │ 👥 30  🔧 30 equipos         │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ Sala Colaborativa 1 [SALA]  │ ║
║  │ Biblioteca - Piso 2         │ ║
║  │ 👥 8  🔧 2 equipos           │ ║
║  └──────────────────────────────┘ ║
║                                    ║
╠════════════════════════════════════╣
║ 🔍     📅     ✅     📖     👤    ║
║BUSCAR CALEN- DISP- MIS    PERFIL  ║
║      DARIO  ONIB. RESERVAS        ║
╚════════════════════════════════════╝
```

---

### 6.4 Pantalla de Calendario

```
╔════════════════════════════════════╗
║ ← Calendario de Reservas           ║
╠════════════════════════════════════╣
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │  ◀  Marzo 2026  ▶            │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║   L   M   X   J   V   S   D       ║
║  ─────────────────────────────    ║
║               1   2   3   4   5   ║
║   6   7  [8]  9  10  11  12      ║
║  13  14  15  16  17  18  19      ║
║  20  21  22  23  24  25  26      ║
║  27  28  29  30  31              ║
║                                    ║
║  Días con punto = Tienen reservas  ║
║                                    ║
║ ──────────────────────────────────║
║                                    ║
║  Reservas para 08/03/2026         ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ Aula 101               ✓     │ ║
║  │ 10:00 - 12:00               │ ║
║  │ Estudio grupal              │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ Lab. Computación       ✓     │ ║
║  │ 14:00 - 16:00               │ ║
║  │ Clase de Programación       │ ║
║  └──────────────────────────────┘ ║
║                                    ║
╠════════════════════════════════════╣
║ 🔍     📅     ✅     📖     👤    ║
╚════════════════════════════════════╝
```

---

### 6.5 Pantalla de Disponibilidad

```
╔════════════════════════════════════╗
║ ← Disponibilidad de Salas          ║
╠════════════════════════════════════╣
║  ┌──────────────────────────────┐ ║
║  │ ℹ️  Consulta en tiempo real   │ ║
║  │    Verifica disponibilidad    │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  [Todas] [Aula] [Lab] [Sala]      ║
║                                    ║
║  Mostrar solo disponibles  [ON]    ║
║                                    ║
║  ┌────────────────────────────────║
║  │  🏢 TOTAL    ✅ DISP   ❌ OCUP║
║  │    12          8         4    ║
║  └────────────────────────────────║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ Aula 101  [DISPONIBLE] ✅    │ ║
║  │ Edificio A - Piso 1         │ ║
║  │ 👥40  📚AULA                 │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ Lab. Física  [OCUPADA] ❌    │ ║
║  │ Edificio C - Piso 1         │ ║
║  │ 👥25  🔬LABORATORIO          │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ Sala Estudio  [DISPONIBLE]✅ │ ║
║  │ Biblioteca - Piso 2         │ ║
║  │ 👥8  📖SALA_ESTUDIO          │ ║
║  └──────────────────────────────┘ ║
║                                    ║
╠════════════════════════════════════╣
║ 🔍     📅     ✅     📖     👤    ║
╚════════════════════════════════════╝
```

---

### 6.6 Pantalla Mis Reservas

```
╔════════════════════════════════════╗
║ ← Mis Reservas                     ║
╠════════════════════════════════════╣
║                                    ║
║  Reservas Activas                  ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ Sala Colaborativa 1 [CONFIRM]│ ║
║  │ Biblioteca - Piso 2         │ ║
║  │ 📅 08/03/2026 ⏰ 10:00-12:00│ ║
║  │ 👥 5 participantes          │ ║
║  │ Propósito: Estudio grupal   │ ║
║  │                             │ ║
║  │ [❌ CANCELAR RESERVA]       │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ Aula 201        [CONFIRMADA] │ ║
║  │ Edificio A - Piso 2         │ ║
║  │ 📅 10/03/2026 ⏰ 14:00-16:00│ ║
║  │ 👥 30 participantes         │ ║
║  │ Propósito: Presentación     │ ║
║  │                             │ ║
║  │ [❌ CANCELAR RESERVA]       │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  Historial                         ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ Lab. Computación [COMPLETADA]│ ║
║  │ Edificio C - Piso 1         │ ║
║  │ 📅 01/03/2026 ⏰ 08:00-10:00│ ║
║  └──────────────────────────────┘ ║
║                                    ║
╠════════════════════════════════════╣
║ 🔍     📅     ✅     📖     👤    ║
╚════════════════════════════════════╝
```

---

### 6.7 Pantalla de Perfil

```
╔════════════════════════════════════╗
║ ← Mi Perfil                        ║
╠════════════════════════════════════╣
║                                    ║
║           ┌──────┐                 ║
║           │  👤  │                 ║
║           └──────┘                 ║
║                                    ║
║        Juan Pérez García           ║
║     juan.perez@universidad.edu     ║
║                                    ║
║         [ESTUDIANTE]               ║
║         ID: 20201234               ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │      Estadísticas            │ ║
║  │                              │ ║
║  │  📋     ✅      ✔️           │ ║
║  │  15     12       8           │ ║
║  │ TOTAL ACTIVAS COMPLETADAS    │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  Configuración                     ║
║                                    ║
║  🔔 Notificaciones            ▶   ║
║  🌐 Idioma: Español           ▶   ║
║  🌓 Tema: Claro/Oscuro        ▶   ║
║  ℹ️  Acerca de: v1.0.0         ▶   ║
║  ❓ Ayuda y Soporte           ▶   ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │  🚪 CERRAR SESIÓN            │ ║
║  └──────────────────────────────┘ ║
║                                    ║
╠════════════════════════════════════╣
║ 🔍     📅     ✅     📖     👤    ║
╚════════════════════════════════════╝
```

---

### 6.8 Pantalla de Crear Reserva

```
╔════════════════════════════════════╗
║ ← Nueva Reserva                    ║
╠════════════════════════════════════╣
║  ┌──────────────────────────────┐ ║
║  │ Aula 101                     │ ║
║  │ Edificio A - Piso 1          │ ║
║  │ Capacidad: 40 personas       │ ║
║  │ Tipo: AULA                   │ ║
║  │ Equipamiento: Proyector,     │ ║
║  │ Pizarra digital, AC          │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ Fecha de reserva          📅 │ ║
║  │ 08/03/2026                   │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  Horario                           ║
║                                    ║
║  Hora Inicio      Hora Fin         ║
║  ┌─────┬─────┐  ┌─────┬─────┐    ║
║  │ 10  │ 00  │  │ 12  │ 00  │    ║
║  │ HH  │ MM  │  │ HH  │ MM  │    ║
║  └─────┴─────┘  └─────┴─────┘    ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ Propósito de la reserva      │ ║
║  │                              │ ║
║  │ Estudio en grupo para examen │ ║
║  │ de Cálculo...                │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │ 👥 Número de participantes   │ ║
║  │ 30                           │ ║
║  └──────────────────────────────┘ ║
║                                    ║
║  ┌──────────────────────────────┐ ║
║  │  ✅ CONFIRMAR RESERVA        │ ║
║  └──────────────────────────────┘ ║
║                                    ║
╚════════════════════════════════════╝
```

---

## 7. ARQUITECTURA TÉCNICA

### 7.1 Patrón Arquitectónico: MVVM

```
┌─────────────────────────────────────────────┐
│              UI LAYER (View)                │
│  ┌─────────────────────────────────────┐   │
│  │   Jetpack Compose Screens           │   │
│  │  - WelcomeScreen                    │   │
│  │  - LoginScreen                      │   │
│  │  - HomeScreen                       │   │
│  │  - CalendarioScreen                 │   │
│  │  - DisponibilidadScreen             │   │
│  │  - MisReservasScreen                │   │
│  │  - PerfilScreen                     │   │
│  │  - ReservaScreen                    │   │
│  └─────────────────────────────────────┘   │
└──────────────┬──────────────────────────────┘
               │ observes State
               │
┌──────────────▼──────────────────────────────┐
│         VIEWMODEL LAYER                     │
│  ┌─────────────────────────────────────┐   │
│  │   ReservaViewModel                  │   │
│  │  - uiState: StateFlow              │   │
│  │  - filtrarSalasPorTipo()           │   │
│  │  - buscarSalas()                   │   │
│  │  - verificarDisponibilidad()       │   │
│  │  - crearReserva()                  │   │
│  │  - cancelarReserva()               │   │
│  │  - obtenerMisReservas()            │   │
│  └─────────────────────────────────────┘   │
└──────────────┬──────────────────────────────┘
               │ calls methods
               │
┌──────────────▼──────────────────────────────┐
│          REPOSITORY LAYER                   │
│  ┌─────────────────────────────────────┐   │
│  │   ReservaRepository                 │   │
│  │  - obtenerSalas()                   │   │
│  │  - obtenerReservas()                │   │
│  │  - crearReserva()                   │   │
│  │  - verificarDisponibilidad()        │   │
│  │  - obtenerUsuarioActual()           │   │
│  └─────────────────────────────────────┘   │
└──────────────┬──────────────────────────────┘
               │ manages data
               │
┌──────────────▼──────────────────────────────┐
│           DATA LAYER (Model)                │
│  ┌─────────────────────────────────────┐   │
│  │   Data Models                       │   │
│  │  - Usuario                          │   │
│  │  - Sala                             │   │
│  │  - Reserva                          │   │
│  │  - TipoUsuario (enum)               │   │
│  │  - TipoSala (enum)                  │   │
│  │  - EstadoReserva (enum)             │   │
│  └─────────────────────────────────────┘   │
└─────────────────────────────────────────────┘
```

### 7.2 Estructura de Paquetes

```
com.example.reservasalas/
│
├── MainActivity.kt
│
├── data/
│   └── ReservaRepository.kt
│
├── model/
│   ├── Usuario.kt
│   ├── Sala.kt
│   └── Reserva.kt
│
├── viewmodel/
│   └── ReservaViewModel.kt
│
├── navigation/
│   └── Navigation.kt
│
└── ui/
    ├── screens/
    │   ├── WelcomeScreen.kt
    │   ├── LoginScreen.kt
    │   ├── HomeScreen.kt
    │   ├── CalendarioScreen.kt
    │   ├── DisponibilidadScreen.kt
    │   ├── MisReservasScreen.kt
    │   ├── PerfilScreen.kt
    │   └── ReservaScreen.kt
    │
    └── theme/
        ├── Theme.kt
        └── Type.kt
```

### 7.3 Tecnologías Utilizadas

| Categoría | Tecnología | Versión | Propósito |
|-----------|------------|---------|-----------|
| Lenguaje | Kotlin | 1.9.0 | Lenguaje principal |
| UI Framework | Jetpack Compose | Material 3 | Interfaz de usuario |
| Navegación | Navigation Compose | 2.7.6 | Sistema de navegación |
| Arquitectura | MVVM | - | Patrón arquitectónico |
| Estado | StateFlow | - | Gestión de estado reactivo |
| Build System | Gradle | 8.2 | Compilación |
| Min SDK | Android 24 | 7.0 | Compatibilidad mínima |
| Target SDK | Android 34 | 14 | Versión objetivo |

---

## 8. CONCLUSIONES

### 8.1 Cumplimiento de Requisitos del Parcial

✅ **Pantalla 1 (Bienvenida):** Implementada con nombre de la app y nombres completos del equipo de 4 estudiantes

✅ **Pantalla 2 (Login):** Implementada con 2 campos (usuario y contraseña) + selector de tipo de usuario y botón de acceso

✅ **Pantalla 3 (Menú Principal):** Implementada con barra de navegación inferior que contiene **5 opciones** funcionales

✅ **Navegación Completa:** Flujo de navegación implementado entre todas las pantallas de manera coherente

✅ **Control de Acceso:** Sistema de validación de usuarios con protección de rutas

✅ **Funcionalidad del Caso:** Todas las pantallas implementan la lógica del sistema de reserva de salas

### 8.2 Logros del Proyecto

1. **Aplicación Completamente Funcional**
   - Todas las pantallas operativas
   - Navegación fluida y natural
   - Validaciones en tiempo real

2. **Diseño Moderno y Profesional**
   - Material Design 3
   - Interfaz intuitiva
   - Experiencia de usuario optimizada

3. **Arquitectura Robusta**
   - Patrón MVVM implementado correctamente
   - Código modular y mantenible
   - Separación clara de responsabilidades

4. **Funcionalidades Completas**
   - Gestión de usuarios
   - Búsqueda y filtrado
   - Sistema de reservas con validaciones
   - Calendario visual
   - Consulta de disponibilidad
   - Perfil de usuario

### 8.3 Valor Diferencial

- **Control de Acceso Robusto:** Validación institucional y protección de rutas
- **5 Opciones de Menú Funcionales:** Más allá del mínimo requerido
- **Validaciones Automáticas:** Evita conflictos de horarios
- **Interfaz Moderna:** Jetpack Compose con Material 3
- **Datos de Demostración:** Permite testing inmediato

### 8.4 Futuras Mejoras

- Integración con backend REST API
- Base de datos persistente
- Notificaciones push
- Modo offline con sincronización
- Sistema de permisos más granular
- Reportes y estadísticas avanzadas

---

## 9. INSTRUCCIONES DE INSTALACIÓN

### 9.1 Requisitos Previos
- Android Studio Hedgehog (2023.1.1) o superior
- JDK 11+
- Android SDK 34
- Dispositivo/Emulador con Android 7.0+

### 9.2 Pasos de Instalación
1. Clonar el repositorio
2. Abrir en Android Studio
3. Sincronizar Gradle
4. Ejecutar en dispositivo/emulador

### 9.3 Credenciales de Prueba
- **Email:** cualquier correo con @universidad.edu
- **Contraseña:** cualquier texto
- **Tipo:** Seleccionar cualquier opción

---

## 10. REFERENCIAS

- **Android Developers:** https://developer.android.com
- **Jetpack Compose:** https://developer.android.com/jetpack/compose
- **Material Design 3:** https://m3.material.io
- **Kotlin Documentation:** https://kotlinlang.org/docs

---

**Documento preparado por:**
Equipo de Desarrollo - Proyecto Final Primer Parcial
Desarrollo de Aplicaciones Móviles Android
Universidad - Marzo 2026

---

**FIN DEL DOCUMENTO**

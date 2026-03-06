═══════════════════════════════════════════════════════════════════════════
  GUÍA RÁPIDA PARA CORREGIR LOS 3 ERRORES
═══════════════════════════════════════════════════════════════════════════

📂 UBICACIÓN DE LOS ARCHIVOS CORREGIDOS:
   - ReservaScreen_CORREGIDO.txt
   - LoginScreen_CORREGIDO.txt  
   - ui_CORREGIDO.txt

═══════════════════════════════════════════════════════════════════════════
  ERROR 1: ReservaScreen.kt - REDECLARACIÓN
═══════════════════════════════════════════════════════════════════════════

❌ PROBLEMA:
e: file:///C:/Users/Kozum/AndroidStudioProjects/Proyecto1/composeApp/src/androidMain/kotlin/com/example/proyecto1/ui/screens/ReservaScreen.kt:7:12 Redeclaration:
enum class EstadoReserva : Enum<EstadoReserva>

✅ SOLUCIÓN:
Archivo: composeApp/src/androidMain/kotlin/com/example/proyecto1/ui/screens/ReservaScreen.kt

BUSCA estas líneas (aproximadamente líneas 7-20) y ELIMÍNALAS:

    enum class EstadoReserva {
        PENDIENTE,
        CONFIRMADA,
        CANCELADA
    }

    data class Reserva(
        val id: String,
        val usuario: Usuario,
        val sala: Sala,
        val fecha: LocalDate,
        val horaInicio: LocalTime,
        val horaFin: LocalTime,
        val proposito: String,
        val estado: EstadoReserva
    )

O mejor aún: Copia todo el contenido de ReservaScreen_CORREGIDO.txt

═══════════════════════════════════════════════════════════════════════════
  ERROR 2: LoginScreen.kt - EXPERIMENTAL API
═══════════════════════════════════════════════════════════════════════════

❌ PROBLEMA:
e: file:///C:/Users/Kozum/AndroidStudioProjects/Proyecto1/composeApp/src/androidMain/kotlin/com/example/proyecto1/ui/screens/LoginScreen.kt:78:9 This material API is experimental and is likely to change or to be removed in the future.

✅ SOLUCIÓN:
Archivo: composeApp/src/androidMain/kotlin/com/example/proyecto1/ui/screens/LoginScreen.kt

AGREGA esta línea AL INICIO del archivo (antes de "package"):

    @file:OptIn(ExperimentalMaterial3Api::class)

El archivo debe empezar así:

    @file:OptIn(ExperimentalMaterial3Api::class)

    package com.example.proyecto1.ui.screens

    import androidx.compose.foundation.layout.*
    [... resto de imports ...]

O mejor aún: Copia todo el contenido de LoginScreen_CORREGIDO.txt

═══════════════════════════════════════════════════════════════════════════
  ERROR 3: ui.kt - DATA CLASS VACÍO
═══════════════════════════════════════════════════════════════════════════

❌ PROBLEMA:
e: file:///C:/Users/Kozum/AndroidStudioProjects/Proyecto1/composeApp/src/androidMain/kotlin/com/example/proyecto1/ui/ui.kt:3:14 Data class must have at least one primary constructor parameter.

✅ SOLUCIÓN (elige una):

OPCIÓN A: Reemplazar contenido
Abre: composeApp/src/androidMain/kotlin/com/example/proyecto1/ui/ui.kt
Usa el contenido de: ui_CORREGIDO.txt

OPCIÓN B: Eliminar archivo (RECOMENDADO)
1. En Android Studio, busca: ui/ui.kt
2. Click derecho → Delete
3. Confirmar

═══════════════════════════════════════════════════════════════════════════
  ORDEN RECOMENDADO DE CORRECCIÓN
═══════════════════════════════════════════════════════════════════════════

1️⃣ Corregir ReservaScreen.kt (ERROR MÁS CRÍTICO)
   → Copiar de ReservaScreen_CORREGIDO.txt

2️⃣ Corregir LoginScreen.kt (ADVERTENCIAS - OPCIONAL)
   → Copiar de LoginScreen_CORREGIDO.txt

3️⃣ Eliminar o corregir ui.kt
   → Mejor: ELIMINAR el archivo completo

4️⃣ Build → Rebuild Project

5️⃣ Si hay más errores, cópialos y avisa

═══════════════════════════════════════════════════════════════════════════
  DESPUÉS DE CORREGIR
═══════════════════════════════════════════════════════════════════════════

1. File → Sync Project with Gradle Files
2. Build → Clean Project
3. Build → Rebuild Project
4. Si compila sin errores: Run → Run 'composeApp'

═══════════════════════════════════════════════════════════════════════════

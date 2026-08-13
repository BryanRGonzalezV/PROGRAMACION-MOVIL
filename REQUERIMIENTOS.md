# TaskManager

## 1. Descripción
Una app que permite al usuario gestionar sus actividades y tareas diarias de manera sencilla e intuitiva. El usuario puede registrar nuevos pendientes, llevar el seguimiento de su avance en tiempo real mediante indicadores visuales y revisar los detalles o estado de cada tarea individual.

## 2. Problema que resuelve
Resuelve la falta de organización cotidiana al permitir centralizar los pendientes en un solo lugar accesible desde el dispositivo móvil. Facilita el control de actividades finalizadas versus pendientes, ayudando al usuario a priorizar sus deberes y evitar el olvido de tareas importantes.

## 3. Pantallas
|  | Nombre de pantalla | Descripción breve |
|---|-------------------|-------------------------------|
| 1 | PantallaInicio | Pantalla de bienvenida con resumen y contador de tareas registradas |
| 2 | PantallaLista | Lista scrollable de tareas con opción de agregar, marcar como completadas y eliminar |
| 3 | PantallaDetalle | Detalle específico de la tarea seleccionada mostrando su ID, título y estado |

## 4. Tecnologías usadas
- Kotlin 2.x
- Jetpack Compose + Material 3
- Navigation Compose
- Estado con remember / rememberSaveable

## 5. Diagrama de navegación
- **Inicio -> Lista:** Al pulsar el botón *"Ver Mis Tareas"* en `InicioScreen`, se navega a la pantalla `ListaScreen`.
- **Lista -> Detalle:** Al hacer clic sobre cualquier tarjeta de tarea en `ListaScreen`, la app pasa el `id` de dicha tarea como argumento y navega a `DetalleScreen`.
- **Detalle -> Lista:** Al pulsar el botón *"Regresar"* en `DetalleScreen`, se retrocede en la pila de navegación de vuelta a `ListaScreen`.

## 6. Capturas de pantalla
<img width="591" height="1280" alt="image" src="https://github.com/user-attachments/assets/a3724945-8d66-447c-ab82-f49e4d923a38" />

<img width="591" height="1280" alt="image" src="https://github.com/user-attachments/assets/ed8e63ff-9d57-4af4-abee-36d7a52c1899" />

<img width="591" height="1280" alt="image" src="https://github.com/user-attachments/assets/07cd412e-66b8-4fa0-a728-90f8d9f13120" />


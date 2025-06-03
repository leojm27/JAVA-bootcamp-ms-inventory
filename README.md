# 🎓 Proyecto Bootcamp - MS-INVENTORY

Este proyecto forma parte del **Bootcamp "Microservicios con Java"** dictado en la plataforma **Código Facilito**.  
El objetivo principal es aplicar los conocimientos adquiridos para desarrollar un sistema basado en microservicios, cumpliendo con buenas prácticas de arquitectura, separación de responsabilidades y escalabilidad.

---

# 📦 MS-INVENTORY - Microservicio de Gestión de Inventario

## 📋 Descripción

**MS-INVENTORY** es un microservicio responsable de gestionar el inventario de productos.  
Controla las operaciones relacionadas con el stock, como agregar, restar o consultar cantidades disponibles, y se integra con otros servicios como `ms-product` y `catalog-bff`.

Este servicio permite mantener actualizado el estado del inventario en función de las operaciones de negocio.

---

## 🛠️ Tecnologías utilizadas

- Java 21
- Spring Boot
- Maven
- PostgreSQL
- JPA / Hibernate
- Spring Security
- Springdoc OpenAPI 3.0
- Lombok
- Actuator

---

## 📦 Endpoints disponibles

### Inventario de Productos

- `GET /api/inventario-producto` → Listar todos los registros de inventario
- `GET /api/inventario-producto/{id}` → Obtener inventario por ID
- `POST /api/inventario-producto` → Crear un nuevo registro de inventario
- `PUT /api/inventario-producto/{id}` → Actualizar inventario por ID
- `DELETE /api/inventario-producto/{id}` → Eliminar inventario por ID (lógico)

### Por ID de Producto

- `GET /api/inventario-producto/por-producto/{productId}` → Obtener inventario usando el ID del producto
- `PUT /api/inventario-producto/por-producto/{productId}` → Actualizar inventario según ID del producto
- `DELETE /api/inventario-producto/por-producto/{productId}` → Eliminar inventario según ID del producto (lógico)

---

## 🔧 Variables de entorno requeridas

Asegurate de definir estas variables en tu entorno para la configuración de base de datos y seguridad:

```env
DB_USERNAME=postgres
DB_PASSWORD=password
DB_URL=jdbc:postgresql://localhost:5432/ms_inventory

SECURITY_USERNAME=auth_username
SECURITY_PASSWORD=auth_password
```

## 🐳 Cómo levantar el proyecto con Docker

### 1. Clonar el repositorio
- git clone https://github.com/leojm27/JAVA-bootcamp-ms-inventory.git
- cd JAVA-bootcamp-ms-product

### 2. Construir el JAR y la imagen Docker
- desde el IDE y a maven/gradle -> clean, -> package
- docker build -t ms-inventory:v1 .

### 3. Ejecutar con Docker Compose
- docker compose up -d

## 🌐 Acceso
Una vez levantado el contenedor, accedé al servicio MS-INVENTORY desde:
- http://localhost:8081

Si tenés habilitada la documentación Swagger:
- http://localhost:8081/swagger-ui.html

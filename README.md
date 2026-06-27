# 📦 B2B Inventory Reconciliation API

Una API RESTful de nivel corporativo desarrollada en **Java y Spring Boot**, diseñada para automatizar y auditar la conciliación de inventario en almacenes B2B (Business to Business). 

Este sistema compara el stock registrado en el sistema con los conteos físicos reales, calculando discrepancias, ajustando inventarios y generando alertas automáticas de faltantes o sobrantes, todo bajo una capa estricta de seguridad.

---

## 🚀 Características Principales (Core Features)

* **Auditoría Automática de Inventario:** Lógica de negocio encapsulada para detectar y ajustar discrepancias de stock de manera transaccional.
* **Seguridad Stateless (JWT):** Implementación de Spring Security 6+ con JSON Web Tokens para restringir el acceso a los endpoints operativos. Protección contra acceso no autorizado (401/403).
* **Patrón DTO (Data Transfer Object):** Desacoplamiento estricto de las entidades de la base de datos de la capa de presentación, previniendo vulnerabilidades de exposición de datos.
* **Pruebas Automatizadas (TDD/BDD):** Alta cobertura de la lógica de negocio utilizando pruebas unitarias con JUnit 5 y simulación de dependencias con Mockito.
* **Infraestructura Contenedorizada:** Base de datos relacional desplegada mediante Docker Compose para garantizar consistencia en múltiples entornos de desarrollo.

---

## 🛠️ Stack Tecnológico

| Capa | Tecnología |
| :--- | :--- |
| **Lenguaje** | Java 21 |
| **Framework Base** | Spring Boot 3.x |
| **Persistencia** | Spring Data JPA / Hibernate |
| **Base de Datos** | PostgreSQL 15+ |
| **Seguridad** | Spring Security + io.jsonwebtoken (JWT) |
| **Testing** | JUnit 5 + Mockito |
| **Infraestructura** | Docker & Docker Compose |
| **Build Tool** | Maven |

---

## ⚙️ Requisitos Previos

Para ejecutar este proyecto en tu entorno local, asegúrate de tener instalado:
* [Java 21 (JDK)](https://jdk.java.net/21/) o superior.
* [Docker Desktop](https://www.docker.com/products/docker-desktop/) (o Docker Engine + Compose).
* [Maven](https://maven.apache.org/) (Opcional, incluye el wrapper `mvnw`).

---

## 🏗️ Configuración y Despliegue Local

### 1. Levantar la Infraestructura (Base de Datos)
Ubicado en la raíz del proyecto, ejecuta el siguiente comando para levantar el contenedor de PostgreSQL en segundo plano:
```bash
docker-compose up -d

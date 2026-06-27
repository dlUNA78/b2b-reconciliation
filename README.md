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

2. Ejecutar la Aplicación Spring Boot
Puedes ejecutar la aplicación directamente desde tu IDE (IntelliJ IDEA / Eclipse) o utilizando Maven en la terminal:

Bash
mvn spring-boot:run
Nota: La aplicación se iniciará en http://localhost:8080 y las tablas se crearán automáticamente gracias a la configuración ddl-auto=update de Hibernate.

🔌 Documentación de Endpoints
1. Autenticación (Público)
Obtiene el Token JWT necesario para operar el sistema.

Endpoint: POST /auth/login

Parámetros URL: username (ej. admin), password (ej. admin)

Respuesta Exitosa (200 OK): Retorna el JWT en texto plano.

2. Consultar Catálogo de Productos (Protegido)
Obtiene la lista de productos y su stock actual en el sistema.

Endpoint: GET /api/products

Headers requeridos: Authorization: Bearer <TU_TOKEN>

Respuesta Exitosa (200 OK): Array JSON de DTOs de productos.

3. Conciliación de Inventario (Protegido)
Audita un producto específico informando el conteo físico real.

Endpoint: POST /api/products/{id}/reconcile?physicalStock={cantidad}

Headers requeridos: Authorization: Bearer <TU_TOKEN>

Respuesta Exitosa (200 OK): Mensaje de estado de la auditoría:

"🚨 FALTANTE DETECTADO..."

"⚠️ SOBRANTE DETECTADO..."

"✅ INVENTARIO CUADRADO"

🧪 Pruebas Unitarias y Calidad de Código
El proyecto cuenta con una suite de pruebas automatizadas que validan la lógica de conciliación sin necesidad de levantar el contexto completo de Spring ni la base de datos real.

Para ejecutar la batería de pruebas:

Bash
mvn test
👨‍💻 Autor
[Daniel Luna] Desarrollador Backend Java especializado en arquitecturas escalables, seguridad de APIs e integración de sistemas.

🔗 LinkedIn: [www.linkedin.com/in/daniel-luna-a2b848271]

💼 Portafolio: [https://github.com/dlUNA78]

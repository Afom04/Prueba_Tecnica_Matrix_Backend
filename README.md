# Prueba técnica — Backend (Matrix)

Descripción
-----------
API REST construida con Spring Boot para la prueba técnica. Incluye autenticación, gestión de usuarios, recursos y solicitudes con estados y migraciones de base de datos.

Estado del repositorio
----------------------
- Lenguaje: Java 17
- Framework: Spring Boot
- Build: Maven
- Contenedores: Docker / docker-compose

Requisitos locales
------------------
- JDK 17
- Maven 3.6+
- Docker & Docker Compose (si usas contenedores)

Estructura relevante
--------------------
- `technical-test/` — código fuente del servicio Spring Boot
- `docker/` — scripts relacionados con Docker (ej. `init.sql`)
- `docker-compose.yml` — definición de servicios para entorno local

Configuración
-------------
La configuración principal está en `technical-test/src/main/resources/application.yaml`.
Ajusta variables (puerto, datasource, JWT, etc.) según tu entorno.

Base de datos
------------
Hay migraciones y un script inicial en `docker/init.sql` usado por la composición Docker.

Ejecutar
-------
Con Docker (recomendado para réplica local rápida):

1. Levanta contenedores:

	docker-compose up --build

2. La API estará disponible en `http://localhost:8080` (según configuración).

Local con Maven (sin Docker):

1. Desde la carpeta `technical-test`:

	mvn clean package
	mvn spring-boot:run

2. O ejecutar el JAR:

	java -jar target/technical-test-0.0.1-SNAPSHOT.jar

Endpoints principales
---------------------
La aplicación expone controladores para autenticación, usuarios, recursos y solicitudes.

- Autenticación: `/api/auth` — login/refresh (JWT)
- Usuarios: `/api/users` — CRUD usuarios (según roles)
- Recursos: `/api/resources` — CRUD de recursos
- Solicitudes: `/api/requests` — crear / actualizar estado de solicitudes

Para detalles de cada endpoint y esquemas DTO, consulta los controladores en `technical-test/src/main/java/com/matrix/technicaltest/controller`.

Documentación API (Swagger)
--------------------------
Si Swagger está habilitado, la UI está disponible típicamente en `/swagger-ui.html` o en `/swagger-ui/index.html`.

Tests
-----
Se incluyen tests base en `technical-test/src/test`. Ejecuta:

	mvn test

Buenas prácticas y notas
-----------------------
- Revisa `technical-test/src/main/resources/application.yaml` antes de ejecutar en producción.
- El módulo de seguridad utiliza JWT; revisa `technical-test/src/main/java/com/matrix/technicaltest/security`.

Contribuir
----------
1. Crea una rama por feature: `git checkout -b feature/mi-cambio`
2. Haz commits claros y atómicos
3. Abre un PR solicitando revisión

Contacto
-------
Para preguntas o clarificaciones, responde en el repositorio o contacta al autor de la prueba.

---

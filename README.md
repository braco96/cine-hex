# 🎬 Cine Hex — Backend (Arquitectura Hexagonal con Spring Boot)

**Autor:** Luis Bravo Collado (_braco96_)  
**Stack:** Java 17 · Spring Boot 3 · Maven · JUnit 5 · (opcional) Testcontainers · Spring Data JPA · MapStruct  
**Arquitectura:** Hexagonal (Domain / Application / Infrastructure)  
**Objetivo:** Gestión de un cine: **películas**, **salas**, **sesiones**, **butacas** y **compra de entradas**.

---

## 📌 Descripción

**Cine Hex** es una API backend desarrollada con **Spring Boot 3** siguiendo **Arquitectura Hexagonal (Ports & Adapters)**.  
Permite **programar sesiones**, **consultar disponibilidad de butacas**, **comprar/cancelar entradas** y consultar **historial de compras** por usuario.

Se ha puesto foco en:
- **Dominio rico** (reglas de negocio claras, sin dependencias de framework).
- **Servicios de aplicación** que orquestan casos de uso (`ProgramarSesion`, `ComprarEntradas`, `ConsultarDisponibilidad`).
- **Puertos** (interfaces) para persistencia y **adaptadores** JPA/REST.
- **Testing** con **JUnit 5** (unitarios del dominio + servicios).

---

## 🧱 Estructura del Proyecto (Hexagonal)

```
cine-hex/
├─ domain/
│  ├─ model/                 # Entidades de dominio
│  │  ├─ Pelicula.java
│  │  ├─ Sala.java
│  │  ├─ Sesion.java         # fecha/hora, sala, pelicula, plano de butacas
│  │  ├─ Butaca.java         # fila, columna, estado
│  │  ├─ Entrada.java
│  │  └─ Usuario.java
│  └─ port/out/              # Puertos de salida (persistencia)
│     ├─ PeliculaRepository.java
│     ├─ SalaRepository.java
│     ├─ SesionRepository.java
│     ├─ EntradaRepository.java
│     └─ UsuarioRepository.java
├─ application/
│  ├─ usecase/
│  │  ├─ ProgramarSesionService.java
│  │  ├─ ComprarEntradasService.java
│  │  ├─ ConsultarDisponibilidadService.java
│  │  └─ CancelarEntradaService.java
│  └─ dto/                   # DTOs si aplica
├─ infrastructure/
│  ├─ config/                # Beans, mappers, seguridad (si aplica)
│  ├─ adapter/
│  │  ├─ persistence/        # Adapters JPA/Hibernate
│  │  └─ rest/               # Controladores REST
│  ├─ entity/                # Entidades JPA (solo infraestructura)
│  └─ mapper/                # MapStruct (entity <-> domain)
└─ src/test/java/...         # Pruebas (dominio + aplicación)
```

> Nota: En **dominio** no se permiten dependencias a Spring/JPA. Las reglas de negocio viven ahí.

---

## 🚀 Casos de Uso

- **Programar sesión**: alta de sesión indicando `pelicula`, `sala`, `fecha/hora` y **aforo/plano de butacas**.
- **Consultar disponibilidad**: lista de butacas libres/ocupadas por sesión.
- **Comprar entradas**: reserva de `n` butacas concretas; verifica **no duplicidad**, **aforo** y **estado**.
- **Cancelar entrada**: libera butacas y actualiza estado.
- **Historial usuario**: entradas por usuario.

---

## 🔌 Endpoints REST (referencia)

> Los nombres pueden variar según tu implementación; aquí una sugerencia alineada a los servicios:

### 🎥 Películas
- `GET /api/v1/movies` – listar películas
- `POST /api/v1/movies` – crear película

### 🏟️ Salas
- `GET /api/v1/rooms`
- `POST /api/v1/rooms`

### 🕒 Sesiones
- `GET /api/v1/sessions?movieId=&date=` – buscar sesiones
- `POST /api/v1/sessions` – programar sesión

### 💺 Butacas / Disponibilidad
- `GET /api/v1/sessions/{id}/seats` – disponibilidad (ocupada/libre)

### 🎫 Entradas
- `POST /api/v1/tickets/purchase` – comprar entradas  
  _Body ejemplo:_
  ```json
  {
    "sessionId": 42,
    "userId": 7,
    "seats": ["F5-C7", "F5-C8"]
  }
  ```
- `POST /api/v1/tickets/cancel` – cancelar entrada(s)  
- `GET /api/v1/users/{id}/tickets` – historial por usuario

---

## 🧪 Testing

### Pirámide del Testing aplicada
- **Unit tests (Dominio y Servicios)** → JUnit 5 (rápidos, sin framework).
- **Integration tests (Adapters)** → (Opcional) Testcontainers para DB real.
- **E2E/API** → (Opcional) Postman/Newman o RestAssured.

### Pruebas clave incluidas (recomendadas)
- **Compra de entradas**:
  - ✅ Reserva de butacas disponibles.
  - ❌ Error si la butaca ya está ocupada.
  - ❌ Error si el aforo está completo.
  - ✅ Cálculo total de entradas/coste (si aplica).
- **Programar sesión**:
  - ✅ No permitir solape de sesiones en la misma sala/hora.
  - ✅ Validar que la película y sala existan.

_Ejecutar tests:_
```bash
mvn -q -DskipTests=false test
```

---

## ⚙️ Puesta en Marcha

### Requisitos
- **Java 17**, **Maven 3.9+**
- Base de datos (PostgreSQL/MySQL) o H2 en memoria (dev).

### Configuración típica (`application.properties`)
```properties
server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/cinehex
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Ejecutar
```bash
mvn clean package
mvn spring-boot:run
```

---

## 🧰 Calidad y Seguridad (opcional pero recomendado)
- **SonarQube**: análisis estático (bugs, code smells, cobertura).
  ```bash
  mvn sonar:sonar \
   -Dsonar.projectKey=cine-hex \
   -Dsonar.host.url=http://localhost:9000 \
   -Dsonar.login=TOKEN
  ```
- **Fortify / Semgrep**: reglas de seguridad (SAST).

---

## 🧩 Decisiones de Diseño

- **Arquitectura Hexagonal** para aislar el **dominio** de frameworks externos.
- **Puertos** (`port/out`) definen **qué** necesita el dominio; los **adapters** implementan **cómo**.
- **Servicios de aplicación** orquestan casos de uso y transacciones.
- **MapStruct** para mapear **entity ↔ model** (limpieza y menor boilerplate).
- **Reglas de negocio** unit-testables (sin necesidad de Spring en tests del dominio).

---

## 🗂️ Roadmap / TODO

- [ ] Endpoint para **asignación masiva** de butacas en programación de sesión.
- [ ] **DTOs** de entrada/salida y validación con `jakarta.validation`.
- [ ] **Autenticación/Autorización** (JWT) para roles `ADMIN` (programar) y `USER` (comprar).
- [ ] **Precios dinámicos** por día/hora/sala (pricing).
- [ ] **Métricas** (Micrometer/Prometheus) y **Logs** estructurados.

---

## 👤 Autor

**Luis Bravo Collado — _braco96_**  
📧 bravocolladoluis@gmail.com  
🌐 https://github.com/braco96

> Proactivo y con iniciativa. Código basado en **principios SOLID** y **arquitectura hexagonal**, con foco en mantenibilidad, tests y entrega continua.

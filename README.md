# ForoHub API

API REST para un foro de discusión, desarrollada con Spring Boot 3.

## Tecnologias

- Java 17
- Spring Boot 3.2.12
- Spring Security con JWT
- Spring Data JPA
- MySQL
- Lombok
- Flyway (migraciones)

## Requisitos

- Java 17+
- MySQL 8.0+
- Maven 3.8+

## Configuracion

1. Crear la base de datos en MySQL:
```sql
CREATE DATABASE forohub;
```

2. Configurar credenciales en `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/forohub
spring.datasource.username=root
spring.datasource.password=tu_password
```

3. Ejecutar la aplicacion:
```bash
./mvnw spring-boot:run
```

La aplicacion estara disponible en `http://localhost:8080`

## Endpoints

### Autenticacion

| Metodo | Endpoint | Descripcion | Autenticacion |
|--------|----------|-------------|---------------|
| POST | `/registro` | Registrar nuevo usuario | No |
| POST | `/login` | Iniciar sesion | No |

### Topicos

| Metodo | Endpoint | Descripcion | Autenticacion |
|--------|----------|-------------|---------------|
| GET | `/topicos` | Listar todos los topicos | Si |
| GET | `/topico/{id}` | Obtener topico por ID | Si |
| POST | `/topico` | Crear nuevo topico | Si |
| PUT | `/topico/{id}` | Actualizar topico | Si |
| DELETE | `/topicos/{id}` | Eliminar topico | Si |

## Uso de la API

### 1. Registrar usuario

```http
POST /registro
Content-Type: application/json

{
    "nombre": "Daniel",
    "login": "daniel123",
    "contrasenia": "mipassword"
}
```

### 2. Iniciar sesion

```http
POST /login
Content-Type: application/json

{
    "login": "daniel123",
    "contrasenia": "mipassword"
}
```

Respuesta:
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### 3. Usar endpoints protegidos

Incluir el token en el header `Authorization`:

```http
GET /topicos
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### 4. Crear topico

```http
POST /topico
Authorization: Bearer {token}
Content-Type: application/json

{
    "titulo": "Mi primer topico",
    "mensaje": "Contenido del topico",
    "autorId": 1,
    "cursoId": 1
}
```

### 5. Actualizar topico

```http
PUT /topico/1
Authorization: Bearer {token}
Content-Type: application/json

{
    "titulo": "Titulo actualizado",
    "mensaje": "Mensaje actualizado",
    "estado": "cerrado"
}
```

## Estructura del Proyecto

```
src/main/java/com/forohub/forohub/
├── controller/
│   ├── AutenticacionController.java
│   ├── RegistroController.java
│   └── TopicoController.java
├── domain/
│   ├── curso/
│   ├── perfil/
│   ├── respuesta/
│   ├── topico/
│   └── usuario/
├── infra/
│   └── security/
│       ├── SecurityConfigurations.java
│       ├── SecurityFilter.java
│       └── TokenService.java
└── service/
    ├── AutenticationService.java
    └── TopicoService.java
```

## Seguridad

- Autenticacion mediante JWT (JSON Web Token)
- Tokens con expiracion de 2 horas
- Contrasenas encriptadas con BCrypt
- Endpoints `/login` y `/registro` son publicos
- Todos los demas endpoints requieren token valido

## Entidades

- **Usuario**: Usuarios del sistema con perfiles
- **Topico**: Temas de discusion del foro
- **Respuesta**: Respuestas a los topicos
- **Curso**: Cursos asociados a los topicos
- **Perfil**: Roles de usuario

## Autor

Challenge Foro Hub - Alura Latam

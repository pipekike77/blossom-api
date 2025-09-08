#  Blossom eCommerce API

Blossom API es una API RESTful para una plataforma de e-commerce desarrollada con Spring Boot. Soporta funcionalidades básicas como autenticación, gestión de productos, órdenes, pagos y usuarios.

##  Descripción

Este repositorio contiene la capa backend de un sistema de comercio electrónico. Entre sus características se incluyen:

- Gestión de productos  

- Autenticación y registro de usuarios con JWT  

- Gestión de órdenes y pagos  

- Historial y estadísticas de usuarios  

- Seguridad con Spring Security  

- Documentación automática con OpenAPI / Swagger UI  

- Base de datos embebida H2 para desarrollo y pruebas  

##  Tecnologías
```bash
| **Tecnología** | **Versión** |
| --- | --- |
| Java | 17  |
| --- | --- |
| Spring Boot | 2.x (compatible) |
| --- | --- |
| Base de datos | H2 (embebida) |
| --- | --- |
| Seguridad | Spring Security + JWT |
| --- | --- |
| Documentación API | springdoc-openapi (Swagger UI) |
| --- | --- |
| Build | Maven |
| --- | --- |
```
## Cómo ejecutar el proyecto

### **Clonar el repositorio**
```bash
git clone <https://github.com/pipekike77/blossom-api.git>

cd blossom-api
```
### **Opción 1: Ejecutar con Maven directamente**

./mvnw spring-boot:run

### **Opción 2: Usar scripts para facilitar la ejecución**

#### **Linux / macOS**
```bash
chmod +x run.sh

./run.sh
```
#### **Windows**

Haz doble clic en run.bat o ejecútalo desde CMD:
```bash
run.bat
```
 ### **Recuerda tener instalado Java 17 o superior.**

##  Estructura del proyecto

La aplicación correrá en:

<http://localhost:8080>

 Estructura del Proyecto 
```bash
src
├── main
│   ├── java/com.ecommerce.blossom.api
│   │   ├── config              # Configuraciones generales
│   │   ├── controllers         # Controladores REST
│   │   ├── dtos                # Data Transfer Objects
│   │   ├── entities            # Entidades JPA
│   │   ├── enums               # Enums para atributos fijos
│   │   ├── events              # Evento orden creada
│   │   ├── exceptions          # Manejadores y excepciones
│   │   ├── filters             # Filtros consultas personalizadas Producto
│   │   ├── listeners           # Evento asíncrono pago de orden
│   │   ├── mappers             # Mapeo de entidades a DTO
│   │   ├── repositories        # Interfaces JPA
│   │   ├── security            # Configuración de seguridad y JWT
│   │   └── services            # Lógica de negocio
│   └── resources
│       ├── application.properties
```

- /src/main/java/... – Código fuente (controladores, servicios, repositorios)  

- /src/main/resources/application.properties – Configuración del proyecto   

- /run.sh y /run.bat – Scripts para ejecutar la aplicación fácilmente  

- /Blossom eCommerce API.postman_collection.json – Colección de Postman para probar la API  

## Documentación API (Swagger UI)

Al iniciar la aplicación, puedes acceder a la documentación interactiva en:

<http://localhost:8080/swagger-ui.html>

Ahí encontrarás todos los endpoints disponibles con sus descripciones, parámetros y modelos.

##  Base de datos embebida H2

Para desarrollo y pruebas, se usa la base de datos H2 embebida. Puedes acceder a la consola H2 en:

<http://localhost:8080/h2-console>
```bash
- JDBC URL: jdbc:h2:mem:testdb  
- User: sa  
- Password: (vacío)  
```

##  Colección Postman

Incluyo una colección Postman para facilitar pruebas manuales de la API. La puedes importar en Postman:

Blossom eCommerce API.postman_collection.json

## Configuración

Puedes modificar las propiedades de la aplicación en:

src/main/resources/application.properties

Aquí puedes configurar:

- Puerto del servidor  

- Configuración de la base de datos  

- Propiedades de seguridad  

- Otros parámetros  

##  Diagrama de base de datos

Aquí está el diagrama ER (Entidad-Relación) de la base de datos para que entiendas la estructura y relaciones.

<img width="1536" height="1024" alt="Blossom DB ER diagram" src="https://github.com/user-attachments/assets/25f3e9c6-61a1-4f70-8aec-2996ecd49f21" />

## Autenticación

La API utiliza JWT para proteger los endpoints.

Endpoints públicos:

POST /auth/register → Crear nuevo usuario

POST /auth/login → Obtener token JWT

Endpoints protegidos:

Todos los demás (/products, /orders, /users, etc.) requieren el token en el encabezado:
```bash
Authorization: Bearer <tu_token_jwt>
```
🧪 Endpoints destacados
```bash
Método	Endpoint	Descripción
POST	/auth/register	        Registro de usuario
POST	/auth/login	            Login y obtención de JWT
GET	    /products	            Obtener todos los productos
POST	/orders	                Crear nueva orden
POST	/payments/process	    Procesar pago manual
GET	    /users/profile	        Obtener perfil de usuario por email
GET	    /users/order-history	Historial de órdenes del usuario
```
🛠️ Próximas mejoras

Agregar soporte para base de datos externa (MySQL / PostgreSQL)

Agregar roles y permisos más granulares

Despliegue en Docker o plataformas cloud

## Contribuciones

¡Las contribuciones son bienvenidas! Puedes abrir issues o pull requests para mejoras o correcciones.

## **Contacto**

Si tienes dudas o comentarios, contáctame en:

- Email: <andresfelipe77@gmailcom>  

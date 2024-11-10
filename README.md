# Proyecto Parameta - README

Este proyecto utiliza una arquitectura basada en microservicios con una base de datos MySQL gestionada mediante Docker Compose. Se incluyen servicios REST y SOAP para cubrir diferentes necesidades de integración.

**Nota:** Este proyecto utiliza **JDK 17**. Asegúrate de tenerlo instalado antes de compilar o ejecutar los microservicios.

---

## 🛠️ Requisitos previos

Asegúrate de tener instaladas las siguientes herramientas:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Apache Maven](https://maven.apache.org/install.html)

---

## 🚀 Pasos para iniciar el proyecto

### 1. **Montar la base de datos con Docker Compose**

1. Clona el repositorio de la librería `commons-library`, donde se encuentra el archivo `docker-compose.yml`:
   ```bash
   git clone https://github.com/test-parameta/commons-library.git
   cd commons-library
   ```

2. Asegúrate de que el archivo `docker-compose.yml` incluya la siguiente configuración:

   ```yaml
   services:
     mysql-db:
       image: mysql:8.0
       container_name: mysql-db
       environment:
         MYSQL_ROOT_PASSWORD: 12345 # Cambia esta contraseña por seguridad
         MYSQL_DATABASE: bd-prueba  # Nombre de la base de datos inicial
         MYSQL_USER: user           # Usuario de la base de datos
         MYSQL_PASSWORD: 12345      # Contraseña del usuario
       ports:
         - "3306:3306" # Mapea el puerto 3306 del contenedor al host
       volumes:
         - mysql_data:/var/lib/mysql
         - ./database.sql:/docker-entrypoint-initdb.d/database.sql # Montar el archivo SQL
       networks:
         - my_network

   volumes:
     mysql_data:
       driver: local

   networks:
     my_network:
       driver: bridge
   ```

3. Levanta el contenedor de la base de datos:
   ```bash
   docker-compose up -d
   ```

- **Notas importantes:**
   - Este contenedor ejecutará automáticamente el script `database.sql`, ubicado también en el repositorio `commons-library`, para inicializar la base de datos con la estructura y datos necesarios.
   - La base de datos debe estar levantada antes de ejecutar los microservicios.

---

### 2. **Compilar y levantar los servicios**

#### **Opción 1: Usar dependencias directamente (recomendado)**

Los microservicios `empleado-rest` y `empleado-soap` automáticamente descargarán la librería `commons-library` al ejecutarse, por lo que no es necesario clonar y compilar esta librería de forma manual.

#### **Opción 2: Clonar y compilar manualmente (opcional)**

Si deseas descargar y compilar la librería `commons-library` manualmente:

1. Clona el repositorio de la librería:
   ```bash
   git clone https://github.com/test-parameta/commons-library.git
   cd commons-library
   ```

2. Configura el archivo `settings.xml` en el directorio `~/.m2/` con las siguientes credenciales para acceder a las librerías alojadas en GitHub Packages:

   ```xml
   <settings>
       <servers>
           <server>
               <id>github</id>
               <username>USER</username>
               <password>TOKEN</password>
           </server>
       </servers>
   </settings>
   ```

Por razones de seguridad y para cumplir con las políticas de GitHub, las credenciales necesarias (token personal y usuario) están protegidas y se encuentran comprimidas en un archivo .rar. Este archivo está disponible dentro del proyecto commons-library.

3. Compila la librería:
   ```bash
   mvn clean install
   ```

---

### 3. **Clonar y compilar los microservicios**

#### **Microservicio `empleado-soap`**

1. Clona el repositorio:
   ```bash
   git clone https://github.com/test-parameta/empleado-soap.git
   cd empleado-soap
   ```

2. Compila el proyecto:
   ```bash
   mvn clean install
   ```

#### **Microservicio `empleado-rest`**

1. Clona el repositorio:
   ```bash
   git clone https://github.com/test-parameta/empleado-rest.git
   cd empleado-rest
   ```

2. Compila el proyecto:
   ```bash
   mvn clean install
   ```

---

### 4. **Levantar los servicios**

1. Asegúrate de que los servicios `empleado-soap` y `empleado-rest` estén corriendo para que la aplicación funcione correctamente.

2. Usa los siguientes comandos para levantar los servicios SOAP y REST en diferentes terminales:

   - **Para SOAP:**
     ```bash
     mvn spring-boot:run
     ```

   - **Para REST:**
     ```bash
     mvn spring-boot:run
     ```

---

## 📂 Endpoints

### **API REST**
Puedes acceder a la documentación Swagger de la API REST en:
- [http://localhost:8001/api/v1/doc/swagger-ui/index.htm](http://localhost:8001/api/v1/doc/swagger-ui/index.htm)

### **Servicios SOAP**
Puedes acceder al archivo WSDL para los servicios SOAP en:
- [http://localhost:8002/ws/empleado-soap.wsdl](http://localhost:8002/ws/empleado-soap.wsdl)

---

## 📝 Notas adicionales

- **Base de datos:** La base de datos debe estar levantada con Docker Compose antes de ejecutar los microservicios. El archivo `docker-compose.yml` se encuentra en el repositorio `commons-library`.
- **Librería `commons-library`:** No es necesario descargarla manualmente, ya que los microservicios la gestionan como dependencia. Sin embargo, si lo deseas, puedes compilarla manualmente siguiendo los pasos anteriores.
- **Servicios SOAP y REST:** Ambos servicios deben estar corriendo simultáneamente para que el sistema funcione correctamente.

---

## 📦 Repositorios del proyecto

- **Microservicio `empleado-rest`:** [https://github.com/test-parameta/empleado-rest.git](https://github.com/test-parameta/empleado-rest.git)
- **Microservicio `empleado-soap`:** [https://github.com/test-parameta/empleado-soap.git](https://github.com/test-parameta/empleado-soap.git)
- **Librería `commons-library`:** [https://github.com/test-parameta/commons-library.git](https://github.com/test-parameta/commons-library.git)

---

¡Gracias por usar el proyecto Parameta! Si tienes preguntas o encuentras algún problema, no dudes en crear un *issue*. 🚀
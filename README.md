# TBD Proyecto Voluntariado GRUPO 3

Sistema de voluntariado sobre emergencias para el laboratorio de la asignatura ***Taller de base de datos***.

## Base de Datos

## postgreSQL

Para la base de datos se utiliza postgreSQL, se debe configurar en el backend en la ruta `Backend/src/main/resources/application.properties`.En el caso que el usuario postgres tenga contraseña está se debe incluir en la parte `database.password=Contraseña`. Además en la consola de la base de datos se deben ingresar los siguientes comandos:

GRANT USAGE ON schema public To postgres;
grant all privileges on all tables in schema public to postgres;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO postgres;
grant all privileges on all tables to postgres;

Para crear la base de datos, debe correr el script `database/createDB.sql` en PostgreSQL y ajustar los valores de configuración del backend en la misma ruta entregada anteriormente `Backend/src/main/resources/application.properties`. La base de datos recién creada se puede poblar utilizando el script `database/insert.sql`, además el dump de la base de datos se puede encontrar en `database/dump.sql`.

## MongoDB
Se debe tener instalada la base de datos MongoDB `https://docs.mongodb.com/manual/installation/` y MongoDb Compass `https://www.mongodb.com/try/download/compass` para facilitar su uso.
Una vez instalada y ejecutando la base de datps, se debe ingresar por la terminal:
`load("/Users/macbookair/Desktop/TBD_LAB1/MongoDB/mongoDB.js")`
Modificando la ruta del archivo `mongoDB.js` según corresponda.

## Backend

Para el backend se utiliza un proyecto creado mediante spring boot localizado en la carpeta `Backend/`.
Con el archivo application.properties ya configurado en `Backend/src/main/resources/application.properties`, ***se encontrará la API REST configurada para correr en http://localhost:8080***.
En algunos computadores no ejecuta de manera correcta el proyecto sin antes dar permisos al archivo `Backend/gradlew`, por lo que se le debe dar permisos con:

```
chmod +x gradlew

```

Luego de tener los permisos ya concebidos, se procede a ejecutar el backend con:

```
./gradlew bootrun

```

Con esto ejecutado por consola, el backend estará corriendo en `http://localhost:8080`

## Documentación de la API REST

Como el backend estará corriendo en `http://localhost:8080`, seguido de esto se debe agregar a la URL lo estipulado en cada punto para poder obtener lo que indica la documentación.

**Voluntario**

`POST /voluntarios/create`   *Crea un voluntario.*

`GET /voluntarios/`   *Devuelve todos los voluntarios.*

`GET /voluntarios/3`   *Devuelve el voluntario 3.*

`PUT /voluntarios/3`   *Actualiza el voluntario 3.*

`DELETE /voluntarios/3`   *Elimina el voluntario 3.*


**Institucion**

`POST /instituciones/create`   *Crea un Institucion.*

`GET /instituciones/`   *Devuelve todos las instituciones.*

`GET /instituciones/3`   *Devuelve la Institucion 3.*

`PUT /instituciones/3`   *Actualiza la Institucion 3.*

`DELETE /instituciones/3`   *Elimina la Institucion 3.*


**Habilidad**

`POST /habilidades/create`  *Crea una Habilidad.*

`GET /habilidades/`  *Devuelve todas las habilidades.*

`GET /habilidades/3`  *Devuelve la Habilidad 3.*

`PUT /habilidades/3`  *Actualiza la Habilidad 3.*

`DELETE /habilidades/3`  *Elimina la Habilidad 3.*


**Estado_tareas**

`POST /estado_tareas/create`   *Crea un estado_tarea.*

`GET /estado_tareas/`   *Devuelve todos los estado_tareas.*

`GET /estado_tareas/3`   *Devuelve el estado_tarea 3.*

`PUT /estado_tareas/3`   *Actualiza el estado_tarea 3.*

`DELETE /estado_tareas/3`   *Elimina el estado_tarea 3.*


**Emergencia**

`POST /emergencias/create`   *Crea una emergencia.*

`GET /emergencias/`   *Devuelve todas las emergencias.*

`GET /emergencias/3`   *Devuelve la emergencia 3.*

`PUT /emergencias/3`   *Actualiza la emergencia 3.*

`DELETE /emergencias/3`   *Elimina la emergencia 3.*


**Tarea**

`POST /tareas/create`   *Crea una tarea.*

`GET /tareas/`   *Devuelve todas las tareas.*

`GET /tareas/3`   *Devuelve la tarea 3.*

`PUT /tareas/3`   *Actualiza la tarea 3.*

`DELETE /tareas/3`   *Elimina la tarea 3.*


**Vol_habilidad**

`POST /vol_habilidades/create`   *Crea una vol_habilidad.*

`GET /vol_habilidades/`   *Devuelve todas las vol_habilidades.*

`GET /vol_habilidades/3`   *Devuelve la vol_habilidad 3.*

`PUT /vol_habilidades/3`   *Actualiza la vol_habilidad 3.*

`DELETE /vol_habilidades/3`   *Elimina la vol_habilidad 3.*


**Eme_habilidad**

`POST /eme_habilidades/create`   *Crea una eme_habilidad.*

`GET /eme_habilidades/`   *Devuelve todas las eme_habilidades.*

`GET /eme_habilidades/3`   *Devuelve la eme_habilidad 3.*

`PUT /eme_habilidades/3`   *Actualiza la eme_habilidad 3.*

`DELETE /eme_habilidades/3`   *Elimina la eme_habilidad 3.*


**Tarea_habilidad**

`POST /tarea_habilidades/create`   *Crea una tarea_habilidad.*

`GET /tarea_habilidades/`   *Devuelve todas las tarea_habilidades.*

`GET /tarea_habilidades/3`   *Devuelve la tarea_habilidad 3.*

`PUT /tarea_habilidades/3`   *Actualiza la tarea_habilidad 3.*

`DELETE /tarea_habilidades/3`   *Elimina la tarea_habilidad 3.*


**Ranking**

`POST /rankings/create`   *Crea un ranking.*

`GET /rankings/`   *Devuelve todos los rankings.*

`GET /rankings/3`   *Devuelve el ranking 3.*

`PUT /rankings/3`   *Actualiza el ranking 3.*

`DELETE /rankings/3`   *Elimina el ranking 3.*


## Frontend

El frontend se desarrolló con `Nuxt.js`
Para ejecutar los comandos del frontend, se debe encontrar en la carpeta `Frontend/`, estando en la carpeta se deben instalar las dependencias de **npm** con el siguiente comando en la terminal.

```
npm install

```
Para poder utilizar las funciones del mapa y poder visualizar el mapa se debe ejecutar el siguiente comando:

```
npm install leaflet vue2-leaflet--save

```

Luego de tener instaladas las dependencias se corre el front con:

```
npm run dev

```

Finalmente el frontend estará corriendo en `http://localhost:3000` de manera predeterminada, sin embargo, si esta se encuentra ocupada, se ecoge un puerto de manera aleatoria.

## MongoDB

Cabe descatar que el atributo id, es _id y es del tipo int32, como se visualiza en el archivo `mongoDB.js`

Para probar las vistas de mongo debe modificar en el backend tareaMongoRepository y EmergenciaMongoRepository la línea 27 y 28 respectivamente, por el puerto utilizado en su base de datos.

## Error
Una solución si las funcionalidades requeridas no funcionan es actualizar el
archivo `router.js` especificamente la path de editarTarea y nuevaTarea debido 
a que algunas veces el /:id se quita. Este archivo se encuentra en la carpeta `frontend/.nuxt/router.js`

ejemplo de como se debería ver:

 path: "/editarTarea/:id"

 path: "/nuevaTarea/:id"

 path: "/mapa/:id"
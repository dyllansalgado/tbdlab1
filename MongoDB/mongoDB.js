
conn = new Mongo("localhost:27017");// se define el usuario y el puerto a utilizar.
db = conn.getDB("TBD-LAB2");//se define el nombre de la base de datos.
db.dropDatabase();

db.createCollection("Emergencia");//Se crea la colección 'Emergencia' para la base de datos.
db.createCollection("Tarea");//Se crea la colección 'Tarea' para la base de datos.

db.Emergencia.insertOne({
    "_id": 0,
    "titulo": "Tsumani",
    "direccion": "Chiloe",
    "descripcion": "Salida del mar por terremoto 8.5",
    "id_institucion": 1,
    "activo": true,
    "latitud": -70.64505046292976,
    "longitud": -33.439188052276740,
    "deleted": false
})

db.Emergencia.insertOne({
    "_id": 1,
    "titulo": "Terremoto",
    "direccion": "Santiago",
    "descripcion": "Miles de casas destruidas",
    "id_institucion": 2,
    "activo": true,
    "latitud": -70.707351,
    "longitud": -33.402820,
    "deleted": false
})

db.Emergencia.insertOne( {
    "_id": 2,
    "titulo": "Aluvion",
    "direccion": "San Pedro de ATacama",
    "descripcion": "Movilizacion Propia.",
    "id_institucion": "",
    "activo": -70.64505046292976,
    "latitud": -33.439188052276740,
    "longitud": 0,
    "deleted": 1
})

db.Emergencia.insertOne({
    "_id": 3,
    "titulo": "Erupcion volcanica",
    "direccion": "Villarica",
    "descripcion": "",
    "id_institucion": "",
    "activo": -70.64505046292976,
    "latitud": -33.439188052276740,
    "longitud": 0,
    "deleted": 1
})


db.Tarea.insertOne( {
    "_id": 0,
    "titulo": "Limpiar",
    "descripcion": "Sacar Escombros",
    "id_estado": 1,
    "id_emergencia": 0,
    "cant_voluntarios": 20,
    "latitud": -70.665461,
    "longitud": -33.436614, 
    "deleted": false
})

db.Tarea.insertOne( {
    "_id": 1,
    "titulo": "Ordenar suministros",
    "descripcion": "Repartir las donaciones para que sean entregadas a los damnificados",
    "id_estado": 1,
    "id_emergencia": 1,
    "cant_voluntarios": 10,
    "latitud": -70.620839,
    "longitud": -33.444834,
    "deleted": false
})

db.Tarea.insertOne({
    "_id": 2,
    "titulo": "Guiar refugiados",
    "descripcion": "Asignar lugares de refugio a los afectados por la emergencia",
    "id_estado": 1,
    "id_emergencia": 2,
    "cant_voluntarios": 15,
    "latitud": -70.620839,
    "longitud": -33.444834,
    "deleted": false
})
db.Tarea.insertOne( {
    "_id": 3,
    "titulo": "Cuidar y alimentar mascotas",
    "descripcion": "Cuidar a las mascotas que esten afectadas por la emergencia",
    "id_estado": 1,
    "id_emergencia": 3,
    "cant_voluntarios": 5,
    "latitud": -70.620839,
    "longitud": -33.444834,
    "deleted": false
})
# 4. Desarrolla un componente de acceso a datos que
# permita guardar y recuperar información de usuarios
# tanto desde una base de datos documental (MongoDB)
# como desde ficheros JSON. El componente deberá exponer
# métodos comunes para insertar y consultar usuarios,
# independientemente del sistema de almacenamiento.

import logging
import json
import pymongo

# Configuración de logging
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s",
    handlers=[
        logging.FileHandler("JsonVSMongo.log"),  # Logs guardados en un archivo
        logging.StreamHandler(), # Logs también en consola
    ]
)

class MongoDBAndJSONHandler:
    def __init__(self, mongo_uri, mongo_db, json_file = None):
        if json_file:
            self.json_file = json_file
            logging.info("Usamos archivos JSON")
        else:
            self.json_file = None
            self.mongo_uri = mongo_uri
            self.mongo_db = mongo_db
            self.mongo_client = None
            self.mongo_collection = None
            self._connect_mongodb()

    def _connect_mongodb(self):
        try:
            self.mongo_client = pymongo.MongoClient(self.mongo_uri)
            self.mongo_collection = self.mongo_client[self.mongo_db]['usuarios']
            logging.info("Conexión a MongoDB establecida")
        except Exception as e:
            logging.error(f"Error al conectar a MongoDB: {e}")

    def insert_user(self, user_data):
        try:
            if self.json_file: 
                # Insertar en JSON
                try:# 1. LEER el archivo con 'r'
                    with open(self.json_file, 'r') as f:
                        data = json.load(f)
                except FileNotFoundError:
                    data = []  # Si no existe el archivo, crear lista vacía
                # 2. MODIFICAR los datos en memoria
                data.append(user_data)
                # 3. ESCRIBIR todo de nuevo con 'w'
                with open(self.json_file, 'w') as f:
                    json.dump(data, f, indent=4)
                
                logging.info("Usuario insertado en JSON")
            else:
                # Insertar en MongoDB
                if self.mongo_collection:
                    self.mongo_collection.insert_one(user_data)
                    logging.info("Usuario insertado en MongoDB")
                else:
                    logging.warning("No se pudo insertar en MongoDB: conexión no establecida")
                
        except Exception as e:
            logging.error(f"Error al insertar usuario: {e}")

    def get_users(self):
        users = []
        try:
            if self.json_file:
                # Obtener usuarios de JSON
                with open(self.json_file, 'r') as f:
                    data = json.load(f)
                    users.append(data)
                    logging.info("Usuarios recuperados de JSON")
            else:
                # Obtener usuarios de MongoDB
                if self.mongo_collection:
                    users.append(list(self.mongo_collection.find({}, {'_id': 0})))
                    logging.info("Usuarios recuperados de MongoDB")
                else:
                    logging.warning("No se pudo recuperar de MongoDB: conexión no establecida")

        except Exception as e:
            logging.error(f"Error al recuperar usuarios: {e}")
        
        return users
    

if __name__ == "__main__":
    # Ejemplo de uso con JSON
    json_handler = MongoDBAndJSONHandler(mongo_uri=None, mongo_db=None, json_file="usuarios.json")
    json_handler.insert_user({"id": 1, "nombre": "Alice", "email": "alice@example.com"})
    json_handler.insert_user({"id": 2, "nombre": "Bob", "email": "bob@example.com"})
    usuarios_json = json_handler.get_users()
    print("Usuarios desde JSON:", usuarios_json)

    # Ejemplo de uso con MongoDB
    mongo_handler = MongoDBAndJSONHandler(mongo_uri="mongodb://localhost:27017", mongo_db="2dam")
    mongo_handler.insert_user({"id": 1, "nombre": "Charlie", "email": "charlie@example.com"})
    usuarios_mongo = mongo_handler.get_users()
    print("Usuarios desde MongoDB:", usuarios_mongo)
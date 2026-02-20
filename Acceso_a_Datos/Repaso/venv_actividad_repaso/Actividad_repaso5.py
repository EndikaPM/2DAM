# 5. Desarrolla un componente de acceso a datos en Python
# que permita importar información desde una base de
# datos relacional y exportarla a una base de datos
# documental (MongoDB). El componente deberá permitir
# seleccionar los registros a transferir mediante un criterio
# de filtrado, gestionar posibles errores durante el proceso y
# mostrar mensajes de estado mediante logging.

import pymysql
import logging
import pymongo

# Configuración de logging
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s",
    handlers=[
        logging.FileHandler("JsonVSMongo.log"),
        logging.StreamHandler(),
    ]
)

class ImportSqlToMongo: 
    def __init__(self, mongo_uri, mongo_db):
        self.conexion = None
        self._conectarMongo(mongo_uri, mongo_db)
    
    def conectarSql(self):
        try:
            db = pymysql.connect(
                host="localhost",
                user="usuario",
                password="usuario",
                database="2dam"
            )
            self.conexion = db
            logging.info("Conexión a MySQL establecida")
            return db
        except Exception as e:
            logging.error(f"Error al conectar a MySQL: {e}")
    
    def _conectarMongo(self, mongo_uri, mongo_db):
        self.mongo_uri = mongo_uri
        self.mongo_db = mongo_db
        self.mongo_client = None
        self.mongo_collection = None
        self._conectarMongoDB()  
    
    def _conectarMongoDB(self):
        try:
            self.mongo_client = pymongo.MongoClient(self.mongo_uri)
            self.mongo_collection = self.mongo_client[self.mongo_db]['usuarios']
            logging.info("Conexión a MongoDB establecida")
        except Exception as e:
            logging.error(f"Error al conectar a MongoDB: {e}")
    
    def selectdata(self, filtro, campo):
        try:
            # SQL 
            sql_select = f"SELECT * FROM usuario WHERE {campo} = %s"
            cursor = self.conexion.cursor()
            cursor.execute(sql_select, (filtro,)) 
            datos = cursor.fetchall() 
            cursor.close()
            logging.info(f"Datos seleccionados correctamente: {len(datos)} registros")
            return datos
        except Exception as e:
            logging.error(f"Error al seleccionar datos de SQL: {e}")
            return []
    
    def insertData(self, data):
        try:
            if self.mongo_collection:
                # Convertir tuplas de MySQL a diccionarios
                documentos = []
                for row in data:
                    doc = {
                        "id": row[0],
                        "nombre": row[1],
                        "email": row[2],
                        "curso": row[3]
                    }
                    documentos.append(doc)
                
                self.mongo_collection.insert_many(documentos)
                logging.info(f"Datos insertados en MongoDB: {len(documentos)} registros")
            else:
                logging.warning("No hay datos para insertar o conexión no establecida")
        except Exception as e:
            logging.error(f"Error al insertar datos en MongoDB: {e}")

if __name__ == "__main__":
    mongo_uri = "mongodb://localhost:27017/"
    mongo_db = "2dam"
    
    importador = ImportSqlToMongo(mongo_uri, mongo_db)
    importador.conectarSql()
    
    # Solicitar filtro al usuario
    campo = input("Introduce el campo para filtrar (nombre, email, curso): ")
    valor = input(f"Introduce el valor para {campo}: ")
    
    datos = importador.selectdata(valor, campo)
    importador.insertData(datos)
# 2. Partiendo de una base de datos relacional existente con
# una tabla de productos, crea un modelo ORM en Python.
# Implementa un programa que permita insertar nuevos
# productos y consultar los que superen un determinado
# precio usando exclusivamente el ORM.

from peewee import MySQLDatabase, Model, IntegerField, CharField, FloatField
import logging

#importamos loogging
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s",
    handlers=[
        logging.FileHandler("databasemanager.log"),  # Logs guardados en un archivo
        logging.StreamHandler(),                    # Logs también en consola
    ]
)


#Conexon
db = MySQLDatabase(
    '2dam',
    user="usuario",
    password="usuario",
    host="localhost",
    port=3306
)


db.connect()
logging.info("Conexión a la base de datos establecida")

# Definición del modelo ORM
class Producto(Model): 
    id = IntegerField(primary_key=True)
    nombre = CharField(max_length=50)
    precio = FloatField()
    class Meta:
        database = db
        table_name = "productos"

db.create_tables([Producto])
logging.info("Tabla 'Producto' creada o ya exixte")

class handlerProducto:
    def insertar_productos(self, datos):
        try:
            with db.atomic():
                for d in datos:
                    Producto.create(id=d[0], nombre=d[1], precio=d[2])
                logging.info(f"Producto {datos[1]} insertado en la base de datos")
        except Exception as e:
            logging.error(f"Error al insertar producto: {e}")

    def consultar_producto(self, precio_condicion):
        try:
            productos_mator_15 =Producto.select().where(Producto.precio > precio_condicion)
            for f in productos_mator_15:
                print(f"nobre={f.nombre}, precio={f.precio}, id={f.id}")
            logging.info(f"Producto con precio mayor a {precio_condicion} encontrado")
        except Exception as e:
            logging.error(f"Error al consultar producto: {e}")

if __name__ == "__main__":
    handler = handlerProducto() 

    handler.insertar_productos([(1, "Producto A", 10.99), 
                                (2, "Producto D", 8.50),
                                (2, "Producto B", 9.99), 
                                (2, "Producto E", 20.50), 
                                (3, "Producto C", 15.99), 
                                (2, "Producto F", 14.99)])  

    handler.consultar_producto(15.00)

    db.close()
    logging.info("Conexión a la base de datos cerrada")
# 3. Implementa una aplicación en Python que almacene
# objetos de tipo Pedido en una base de datos orientada a
# objetos. Cada pedido deberá estar relacionado con un
# cliente previamente almacenado mediante ORM en una
# base de datos relacional. El programa deberá permitir
# consultar todos los pedidos de un cliente concreto.

import logging
import ZODB
import ZODB.FileStorage
import transaction
from peewee import MySQLDatabase, Model, IntegerField, CharField

# Configuración de logging
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s",
    handlers=[
        logging.FileHandler("databasemanager_objetos.log"),  # Logs guardados en un archivo
        logging.StreamHandler(),   
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
class Proveedor(Model): 
    id = IntegerField(primary_key=True)
    nombre = CharField(max_length=50)
    direccion = CharField(max_length=50)
    class Meta:
        database = db
        table_name = "proveedores"

db.create_tables([Proveedor])
logging.info("Tabla 'Proveedor' creada o ya exixte")

class handlerProveedor:
    def consultar_proveedor(self, id):
        try:
            proveedor = Proveedor.get(Proveedor.id == id)
            return proveedor
        except Exception as e:
            logging.error(f"Error al consultar proveedor: {e}")
            return None
        
class Pedido:
    def __init__(self, id, producto, cantidad, proveedor_id):
        self.id = id
        self.producto = producto
        self.cantidad = cantidad
        self.proveedor_id = proveedor_id    

class handlerPedido:
    def __init__(self):
        self.storage = ZODB.FileStorage.FileStorage('pedidos.fs')
        self.db = ZODB.DB(self.storage)
        self.connection = self.db.open()
        self.root = self.connection.root

    def añadir_pedido(self, pedido):
        try:
            root = self.root()
            if 'pedidos' not in root:
                root['pedidos'] = []
            root['pedidos'].append(pedido)
            transaction.commit()
            logging.info(f"Pedido {pedido.id} añadido a la base de datos orientada a objetos")
        except Exception as e:
            logging.error(f"Error al añadir pedido: {e}")

    def consultar_pedidos_por_proveedor(self, proveedor_id):
        try:
            root = self.root()
            pedidos = [pedido for pedido in root.get('pedidos', []) if pedido.proveedor_id == proveedor_id]
            return pedidos
        except Exception as e:
            logging.error(f"Error al consultar pedidos: {e}")
            return []
        
if __name__ == "__main__":
    handler_proveedor = handlerProveedor()
    handler_pedido = handlerPedido()

    proveedor = handler_proveedor.consultar_proveedor(1)
    
    # Si el proveedor no existe, lo creamos
    if proveedor is None:
        proveedor = Proveedor.create(id=1, nombre="Proveedor A", direccion="Calle Principal 123")
        logging.info(f"Proveedor {proveedor.id} creado")

    pedido1 = Pedido(1, "Producto A", 10, proveedor.id)
    pedido2 = Pedido(2, "Producto B", 5, proveedor.id)
    handler_pedido.añadir_pedido(pedido1)
    handler_pedido.añadir_pedido(pedido2)

    pedidos_del_proveedor = handler_pedido.consultar_pedidos_por_proveedor(proveedor.id)
    for pedido in pedidos_del_proveedor:
        print(f"Pedido ID: {pedido.id}, Producto: {pedido.producto}, Cantidad: {pedido.cantidad}, Proveedor ID: {pedido.proveedor_id}")
import mysql.connector
from mysql.connector import Error
conexion = None
try:

    conexion = mysql.connector.connect(
        host="localhost",
        user="usuario", 
        password="usuario", 
        database="2dam" 
    )
    if conexion.is_connected():
        cursor = conexion.cursor()
        print("Iniciando transacción...")
        sql_insert = """INSERT INTO restaurantes (nombre, tipo_comida, ubicacion, valoraciones)
        VALUES (%s, %s, %s, %s)"""
        datos_herramienta = ("5estrellas", "lomo", "sevilla", "muy buena")
        cursor.execute(sql_insert, datos_herramienta)
    conexion.commit()
    print("Transacción exitosa: Registro insertado correctamente.")
except Error as e:
    print(f"Error en la transacción: {e}")
    if conexion:
        conexion.rollback()
        print("Se realizó rollback.")
finally:
    if conexion and conexion.is_connected():
        cursor.close()
        conexion.close()
        print("Conexión cerrada.")
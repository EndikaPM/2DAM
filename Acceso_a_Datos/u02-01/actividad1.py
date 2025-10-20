"""Haz la misma actividad anterior, pero con otro conector, denominado “`PyMySQL”. Debes abrir la
conexión a base de datos, cerrarla en caso de éxito y gestionar los errores. Además, en caso de error,
también debe cerrar la conexión con la base de datos."""
import pymysql
from pymysql import Error

conexion = pymysql.connect

try:
    conexion (
        host='localhost',
        user='usuario',
        password='usuario',
        database='2dam'
    )
    print("Conexión a la base de datos exitosa")
except Error as e:
    print(f"Error de conexión: {e}")
finally:
    if conexion:
        conexion.close()
        print("Conexión cerrada")
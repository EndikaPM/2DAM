import pymysql
from pymysql import Error

def llamar_bd():
    
    try:
        conexion = pymysql.connect (
            host='localhost',
            user='usuario',
            password='usuario',
            database='2dam'
        )
        print("Conexión a la base de datos exitosa")
    except Error as e:
        print(f"Error de conexión: {e}")
    
    cursor = conexion.cursor()
    cursor.execute("SELECT * FROM restaurante LIMIT 5")
    for i in range(0,5):
        print(cursor.fetchone())
    cursor.close()

llamar_bd()
llamar_bd()
import pymysql
from pymysql import Error

try :
    connection = pymysql.connect(
        host='localhost',
        user='usuario',
        passwd='usuario',
        database='2dam')

    print("Conexión realizada con éxito!")
except Error as e:
    print(f"Error al realizar la conexión {e}")

cursor = connection.cursor() 

#MODIFICAR REGRISTROS EXISTENTES,
cursor.execute("UPDATE restaurante SET tipo_comida = %s WHERE nombre = %s", ('espagetis', 'Restaurante 1'))
connection.commit()

print(cursor.rowcount, "registro(s) actualizado(s)")

#ELIMINAR REGISTROS,
cursor.execute("DELETE FROM restaurante WHERE id > %s", (1,))
connection.commit()

print(cursor.rowcount, "registro(s) eliminado(s)")
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

cursor.execute("""CREATE TABLE IF NOT EXISTS cliente(
                id INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(50),
                email VARCHAR(150),
                telefono char(9),
                id_restaurante INT,
                CONSTRAINT fk_cliente FOREIGN KEY (id_restaurante) REFERENCES restaurante(id)
                )""")

cursor.close()
connection.close()
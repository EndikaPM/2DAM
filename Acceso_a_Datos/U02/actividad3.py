# insert_mysql_connector_restaurante.py
import mysql.connector
import random
import time
from datetime import time as dtime

tipos_comida = ['Italiana', 'China', 'Mexicana', 'Española', 'Japonesa']
ubicaciones = ['Madrid', 'Barcelona', 'Sevilla', 'Valencia', 'Bilbao']
valoraciones = ['Buena', 'Excelente', 'Regular', 'Mala']

conn = mysql.connector.connect(
    host="localhost",
    user="usuario",
    password="usuario",
    database="2dam"
)
cursor = conn.cursor()

start_time = time.time()

for i in range(10000):
    nombre = f"Restaurante {i+1}"
    tipo = random.choice(tipos_comida)
    ubicacion = random.choice(ubicaciones)
    hora = dtime(random.randint(10, 23), random.choice([0, 15, 30, 45]))  # ejemplo: 14:30
    valoracion = random.choice(valoraciones)

    cursor.execute(
        "INSERT INTO restaurante (nombre, tipo_comida, ubicacion, horario, valoraciones) VALUES (%s, %s, %s, %s, %s)",
        (nombre, tipo, ubicacion, hora, valoracion)
    )

conn.commit()
end_time = time.time()

print(f"Tiempo de inserción con mysql-connector: {end_time - start_time} segundos")

cursor.close()
conn.close()


# insert_pymysql_restaurante.py
import pymysql
import random
import time
from datetime import time as dtime

tipos_comida = ['Italiana', 'China', 'Mexicana', 'Española', 'Japonesa']
ubicaciones = ['Madrid', 'Barcelona', 'Sevilla', 'Valencia', 'Bilbao']
valoraciones = ['Buena', 'Excelente', 'Regular', 'Mala']

conn = pymysql.connect(
    host="localhost",
    user="usuario",
    password="usuario",
    database="2dam"
)
cursor = conn.cursor()

start_time = time.time()

for i in range(10000):
    nombre = f"Restaurante {i+1}"
    tipo = random.choice(tipos_comida)
    ubicacion = random.choice(ubicaciones)
    hora = dtime(random.randint(10, 23), random.choice([0, 15, 30, 45]))
    valoracion = random.choice(valoraciones)

    cursor.execute(
        "INSERT INTO restaurante (nombre, tipo_comida, ubicacion, horario, valoraciones) VALUES (%s, %s, %s, %s, %s)",
        (nombre, tipo, ubicacion, hora.strftime('%H:%M:%S'), valoracion)
    )

conn.commit()
end_time = time.time()

print(f"Tiempo de inserción con pymysql: {end_time - start_time} segundos")

cursor.close()
conn.close()

# select_mysql_connector_restaurante.py
import mysql.connector
import time

conn = mysql.connector.connect(
    host="localhost",
    user="usuario",
    password="usuario",
    database="2dam"
)
cursor = conn.cursor()

start_time = time.time()

for _ in range(10000):
    cursor.execute("SELECT * FROM restaurante")
    _ = cursor.fetchall()

end_time = time.time()

print(f"Tiempo de consulta con mysql-connector: {end_time - start_time} segundos")

cursor.close()
conn.close()

# select_pymysql_restaurante.py
import pymysql
import time

conn = pymysql.connect(
    host="localhost",
    user="usuario",
    password="usuario",
    database="2dam"
)
cursor = conn.cursor()

start_time = time.time()

for _ in range(10000):
    cursor.execute("SELECT * FROM restaurante")
    _ = cursor.fetchall()

end_time = time.time()

print(f"Tiempo de consulta con pymysql: {end_time - start_time} segundos")

cursor.close()
conn.close()

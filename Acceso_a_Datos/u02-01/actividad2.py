import pymysql
from pymysql import Error


conexion = pymysql.connect(
    host='localhost',
    user='usuario',
    password='usuario',
    database='2dam'
)

cursor = conexion.cursor()

cursor.execute("""
    CREATE TABLE IF NOT EXISTS restaurante(
        id INT AUTO_INCREMENT PRIMARY KEY,
        nombre VARCHAR(50),
        tipo_comida VARCHAR(50),
        ubicacion VARCHAR(50),
        horario TIME,
        valoraciones VARCHAR(50)
    )
""")
cursor.execute(
    "INSERT INTO restaurante (nombre, tipo_comida, ubicacion, horario, valoraciones) VALUES (%s, %s, %s, %s, %s)",
    ("Prueba1", "espagetis", "sevilla", "15:00:00", "Muy buena comida")
)

conexion.commit()

cursor.execute("SELECT * FROM restaurante")

for fila in cursor.fetchall():
    print(fila)

conexion.close()
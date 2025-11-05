import mysql.connector
# Conexión a la base de datos MySQL
conexion = mysql.connector.connect(
    host="localhost",
    user="usuario", # tu usuario MySQL
    password="usuario", # tu contraseña MySQL
    database="2dam" # la base de datos donde está la tabla Herramientas
    )

# Crear un cursor para interactuar con la base de datos
cursor = conexion.cursor()
# Ejecutar una consulta para obtener las 5 instancias de la tabla Herramientas
cursor.execute("SELECT * FROM restaurante LIMIT 5")
# Usar fetchone para obtener la primera fila
print("Usando fetchone():")
primera_fila = cursor.fetchone()
print(primera_fila)
# Usar fetchmany para obtener las siguientes 2 filas
print("\nUsando fetchmany(2):")
siguientes_dos_filas = cursor.fetchmany(2)
for fila in siguientes_dos_filas:
    print(fila)
# Usar fetchall para obtener las filas restantes
print("\nUsando fetchall():")
resto_filas = cursor.fetchall()
for fila in resto_filas:
    print(fila)
# Cerrar el cursor y la conexión
cursor.close()
conexion.close()
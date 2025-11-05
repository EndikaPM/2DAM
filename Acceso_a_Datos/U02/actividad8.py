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
        print("LLamamos a el procedimiento")
        cursor.callproc('obtener_datos')
        for res in cursor.stored_results():
            datos = res.fetchall()
            print(datos)
except Error as e:
    print(f"Error en la transacción: {e}")
finally:
    if conexion and conexion.is_connected():
        cursor.close()
        conexion.close()
        print("Conexión cerrada.")




#DELIMITER //
#BEGIN
#SELECT COUNT(id) AS total_reseñas FROM restaurante;
#SELECT hoarario FROM restaurante WHERE tipo_comida = 'China';
#SELECT nombre FROM restaurante WHERE valoraciones = 'Excelente';
#END
#DELIMITER ;
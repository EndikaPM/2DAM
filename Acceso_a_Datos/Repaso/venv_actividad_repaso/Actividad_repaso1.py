# 1. Diseña un programa en Python que lea un fichero CSV
# con información de alumnos (id, nombre, email, curso) y
# cargue esos datos en una base de datos relacional. El
# programa deberá crear la tabla si no existe, insertar los
# datos y mostrar por pantalla el número total de registros
# insertados.
from pathlib import Path
import csv
import pymysql
import logging

# Configuración de logging
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s",
    handlers=[
        logging.FileHandler("databasemanager.log"),  # Logs guardados en un archivo
        logging.StreamHandler(),                    # Logs también en consola
    ]
)

contador = 0

def read_csv(file_path):
    try:
        with open(file_path, mode='r') as f:
            lectura = csv.DictReader(f)
            rows = []

            for row in lectura:
                rows.append(row)

            return rows
    except Exception as e:
        logging.error(f"Error al leer el csv: {e}")



class mysql:
    def __init__(self):
        self.conexion = None
    def conectar(self):
        db = pymysql.connect(
        host="localhost",
        user="usuario",
        password="usuario",
        database="2dam"
        )
        self.conexion = db
        return db
    def añadir_datos(self, datos):
        try:
            sql_insert = """INSERT INTO usuario (id, nombre, email, curso)
            VALUES (%s, %s, %s, %s)"""
            cursor = self.conexion.cursor()
            for d in datos:
                values = (d['id'], d['nombre'], d['email'], d['curso'])
                cursor.execute(sql_insert, values)
                global contador
                contador += 1
            self.conexion.commit()
            print(f"\nSe han insertado {contador} registros correctamente")
            logging.info(f"Insertados {contador} registros en la base de datos")
            cursor.close()
        except Exception as e:
            logging.error(f"Error al insertar en SQL: {e}")

    def create_table(self):
        try:
            sql_create = """CREATE TABLE IF NOT EXISTS usuario (
                            id INT,
                            nombre VARCHAR(50),
                            email VARCHAR(50),
                            curso VARCHAR(20)
                            )"""
            cursor = self.conexion.cursor()
            cursor.execute(sql_create)
            self.conexion.commit()
            cursor.close()
            logging.info("Tabla creada/verificada correctamente")
        except Exception as e:
            logging.error(f"Error al crear la tabla: {e}")

# Main
if __name__ == "__main__":
    try:
        db_manager = mysql()
        db_manager.conectar()
        logging.info("Conexión a la base de datos establecida")
        
        db_manager.create_table()
        
        datos = read_csv("info_alumno.csv")
        
        if datos:
            db_manager.añadir_datos(datos)
        else:
            logging.warning("No se encontraron datos en el CSV")
            
        db_manager.conexion.close()
        logging.info("Conexión cerrada correctamente")
    except Exception as e:
        logging.error(f"Error en la ejecución principal: {e}")
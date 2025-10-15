from pathlib import Path
"""
class FileManager:
    def __init__(self, path):
        self.path = Path(path)
    def create_directory(self):
        if not self.path.exists():
            self.path.mkdir()
            print(f'Directorio {self.path} creado.')
        else:
            print(f'El directorio {self.path} ya existe.')
    def list_files(self):
        if self.path.exists() and self.path.is_dir():
            return list(self.path.iterdir())
        return []
    def delete_directory(self):
        if self.path.exists() and self.path.is_dir():
            self.path.rmdir()
            print(f'Directorio {self.path} eliminado.')
        else:
            print(f"El nombre no existe")

#Actividad 1 de clase: añade a la clase anterior un método que sirva para borrar directorios. Pide al
#usuario que pulse una tecla entre la creación y el borrado del directorio.

selection = int(input("Selecione la opcion que desea ejecutar\n" \
"1. crear un direcctorio\n" \
"2. Listar directorios \n" \
"3. Eliminar un directorio\n" \
"0. salir"))
while(selection != 0):
    if(selection == 1):
        nombre = input("dime el nombre del directorio.")
        fileManager = FileManager(nombre)
        fileManager.create_directory
    elif(selection == 2):
        print(fileManager.list_files)
    elif(selection == 3):
        nombre = input("dime el nombre del directorio.")
        fileManager.delete_directory
    elif(selection == 0):
        print("Cerrendo programa...")
    else:
        print("Selcione una opcion valida")
"""
#-------------------------------------------------------------------------------------------------------------------------------------
"""
class FileHandler:
    def read_file(self, file_path, mode='r'):
        try:
            with open(file_path, mode) as f:
                content = f.read()
            return content
        except Exception as e:
            print(f"Error leyendo el archivo: {e}")
    def write_file(self, file_path, content, mode='w'):
        try:
            with open(file_path, mode) as f:
                f.write(content)
        except Exception as e:
            print(f"Error escribiendo en el archivo: {e}")
"""
"""
Actividad 2 de clase: El programa debe usar un archivo que se llame “12345678.txt”, siendo el nombre
del fichero tu DNI. El programa debe escribir en dicho archivo una cadena de texto que sea tu fecha
de nacimiento y debe leerla luego del fichero. Por último el programa ha de mostrar por pantalla lo
que haya leido del fichero."""
"""
ruta = Path("15413448.txt")


fileHandler = FileHandler()
fileHandler.write_file(ruta, "17/09/1989","w")
print(fileHandler.read_file(ruta,"r"))
"""
import json

class JSONFileHandler:
    def read_json(self, file_path):
        try:
            with open(file_path, 'r') as f:
                return json.load(f)
        except Exception as e:
            print(f"Error leyendo JSON: {e}")
    def write_json(self, file_path, dato):
        try:
            with open(file_path, 'a') as f:
                json.dump(dato,f)
        except Exception as e:
            print(f"Error escribiendo JSON: {e}")

"""
Actividad 3 de clase: amplía la clase anterior para escribir un JSON en el fichero de data.json.
Dicho JSON ha de tener dos claves: una para tu DNI (por ejemplo 12345678) y otra para tu fecha
de nacimiento (por ejemplo 12/06/76). Usa en Python un dato tipo diccionario para construir el
JSON.
"""
"""
ruta = Path("Datos.json")

datos = {"DNI" : "15413448", "FechaCumple":"17-09-1989"}

jsonHandler = JSONFileHandler()
jsonHandler.write_json(ruta, datos)

print("JSON:\n", jsonHandler.read_json(ruta))
"""
import csv
import json
import os

class ConversorArchivo:
    def csv_a_json(self, archivo_csv, archivo_json):

        rutaCaperta = os.path.dirname(os.path.abspath(file))
        archivo_csv = os.path.join(rutaCaperta, archivo_csv)
        archivo_json = os.path.join(rutaCaperta, archivo_json)

        try:
            with open(archivo_csv, 'r') as f:
                lector = csv.DictReader(f)
                filas = list(lector)

            with open(archivo_json, 'w') as f:
                json.dump(filas, f, indent=4, ensure_ascii=False)

            print(f"Conversión de {archivo_csv} a {archivo_json} completada.")
        except Exception as e:
            print(f"Error en la conversión: {e}")


    def json_a_csv(self, archivo_json, archivo_csv):

        rutaCaperta = os.path.dirname(os.path.abspath(file))
        archivo_csv = os.path.join(rutaCaperta, archivo_csv)
        archivo_json = os.path.join(rutaCaperta, archivo_json)

        try:
            with open(archivo_json, 'r') as f:
                datos = json.load(f)

            with open(archivo_csv, 'w', newline='') as f:
                if len(datos) > 0:
                    campos = datos[0].keys()
                    escritor = csv.DictWriter(f, fieldnames=campos)
                    escritor.writeheader()
                    escritor.writerows(datos)

            print(f"Conversión de {archivo_json} a {archivo_csv} completada.")
        except Exception as e:
            print(f"Error en la conversión: {e}")


conversor = ConversorArchivo()
conversor.csv_a_json('datos.csv', 'datos_convertido.json')
conversor.json_a_csv('datos_convertido.json', 'datos_reconvertido.csv')


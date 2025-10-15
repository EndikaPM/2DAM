import pandas as pd
import numpy as np

df = pd.read_csv("PrecioOilDataset.csv");

df['Volume'] = df["Volume"].str.replace(",", "")#recuerda añadir str porque
                                                #sino no actua directamente en el string si no en el Objeto buscando , direntamente
df['Volume'] = df["Volume"].astype(float)

print("El numero de filas y columnas de mi dataFrame ",df.shape);
print("El numero de Filas ", df.shape[0]);
print("El numero de Columnas ", df.shape[1]);
print("Imprimimos las primeras 5 filas \n", df.head())#sin parametros las primera 5 puedo añadr las que quiera
print("El comando info ", df.info())#informacion general 
print("El comando describe \n", df.describe())#Te hace la medisa, maximo, minimo, los percentiles y la desviacion

datosOil = df[["Production Date", "FIPS Code", "Commodity", "Volume"]]
print(datosOil.head())

#print(df.dtypes); para ver todos los tipos de datos de tu dataset

df["precio_barril"] = df["Volume"] * 1.20 
print(df.head())

mas_1000000_volumen = df[df["Volume"] > 1000000]
print(mas_1000000_volumen.head())

df_noNull = df.dropna()
print(f"Antes de eliminar nulos {df.shape[0]}")
print(f"Despues de eliminar nulos {df_noNull.shape[0]}")

valor_max = np.finfo(float).max #es como una variable estatica en java para el maximo
print(df.head())
df["State"] = df["State"].fillna("Borrar")
print(df.head())

promedio_extracion_crudo = df.groupby("State")[["Volume"]].mean()
print(f"Promedio de extraccion {promedio_extracion_crudo}")
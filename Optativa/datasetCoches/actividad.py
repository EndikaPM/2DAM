import pandas as pd
from sklearn.model_selection import train_test_split
"""Actividad 2 parte 1 Outleiers"""
print("------------------------------Limites-------------------------------------------------------------")
df = pd.read_csv("tema2/carDataset.csv");


# #Calcular el rango interquartil(IQR)
q1 = df["mpg"].quantile(0.25)#Primer cuartil
q3 = df["mpg"].quantile(0.75)#Tercer cuartil
print(q1)
print(q3)

iqr = q3 -q1 # Rango interquiartil

# #Definicion de limites arriva y abajoç
lower_bound = q1 - 1.5 * iqr
upper_bound = q3 + 1.5 * iqr

print(f"Limite inferior : {lower_bound}, limite superior: {upper_bound}" )


# FILTRADO DE VALORES DENTRO DE LOS LÍMITES
df_filtered = df[(df["mpg"] >= lower_bound) & (df["mpg"] <= upper_bound)]


# COMPARACIÓN DE FILAS ANTES Y DESPUÉS
print(f"\nFilas antes: {df.shape[0]} \nFilas después: {df_filtered.shape[0]}\n")

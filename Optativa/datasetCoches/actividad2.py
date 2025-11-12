"""Actividad 2 parte 2 Normalizacion"""
# Calcular la media y la desviación estándar de las columnas
import pandas as pd
from sklearn.model_selection import train_test_split

df = pd.read_csv("tema2/carDataset.csv")

mean_mpg = df["mpg"].mean()
std_mpg = df["mpg"].std()

print(f" Media mpg:{mean_mpg}")
print(f" Desviación estándar mpg:{std_mpg}")

# # Estandarizar las columnas
df["mpg_std"] = (df["mpg"] - mean_mpg) / std_mpg

# # Mostrar los resultados
print(df[["mpg", "mpg_std"]].head())
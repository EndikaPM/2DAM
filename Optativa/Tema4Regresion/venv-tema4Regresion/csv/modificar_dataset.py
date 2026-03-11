import pandas as pd
import numpy as np

# Cargar el dataset
df = pd.read_csv('dataset_de_partida.csv')

# Establecer semilla para reproducibilidad
np.random.seed(42)

# Columnas seleccionadas
columnas_modificar = ['age', 'bmi', 'children', 'smoker']
columnas_numericas = ['age', 'bmi', 'children']

# 1. Insertar 4% de valores nulos aleatoriamente
for col in columnas_modificar:
    # Calcular el número de valores nulos a insertar (4%)
    n_nulls = int(len(df) * 0.04)
    # Seleccionar índices aleatorios
    null_indices = np.random.choice(df.index, size=n_nulls, replace=False)
    # Asignar NaN a esos índices
    df.loc[null_indices, col] = np.nan

# 2. Insertar 3% de valores negativos en columnas numéricas
for col in columnas_numericas:
    # Calcular el número de valores negativos a insertar (3%)
    n_negatives = int(len(df) * 0.03)
    # Seleccionar índices aleatorios (excluyendo los que ya son NaN)
    valid_indices = df[df[col].notna()].index
    negative_indices = np.random.choice(valid_indices, size=min(n_negatives, len(valid_indices)), replace=False)
    # Convertir esos valores a negativos
    df.loc[negative_indices, col] = -abs(df.loc[negative_indices, col])

# Guardar el dataset modificado
df.to_csv('dataset_modificado.csv', index=False)

print("Dataset modificado guardado como 'csv/dataset_modificado.csv'")
print(f"\nResumen de modificaciones:")
print(f"- Valores nulos insertados por columna:")
for col in columnas_modificar:
    print(f"  {col}: {df[col].isna().sum()} ({df[col].isna().sum()/len(df)*100:.2f}%)")
print(f"\n- Valores negativos insertados:")
for col in columnas_numericas:
    print(f"  {col}: {(df[col] < 0).sum()} ({(df[col] < 0).sum()/len(df)*100:.2f}%)")

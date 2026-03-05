# 1. Regresión lineal múltiple con visualización 3D
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.metrics import mean_squared_error
from sklearn.linear_model import LinearRegression

# Cargar datos
df = pd.read_csv("csv/dataset_de_partida.csv")

# Convertir todas las columnas a numérico
for col in df.columns:
    df[col] = pd.to_numeric(df[col], errors='coerce')

# Seleccionar las columnas necesarias (4 predictoras + mpg)
df = df[['cylinders', 'displacement', 'horsepower', 'weight', 'mpg']].dropna()

print(f"Filas después de limpieza: {len(df)}")

# ===== MODELO 1: cylinders + displacement =====
print("\n" + "="*50)
print("MODELO 1: cylinders + displacement → mpg")
print("="*50)

X1 = df[['cylinders', 'displacement']].values
y = df['mpg'].values

model1 = LinearRegression()
model1.fit(X1, y)

print(f"Intercepto: {model1.intercept_:.4f}")
print(f"Coeficiente cylinders: {model1.coef_[0]:.4f}")
print(f"Coeficiente displacement: {model1.coef_[1]:.4f}")

# ===== MODELO 2: horsepower + weight =====
print("\n" + "="*50)
print("MODELO 2: horsepower + weight → mpg")
print("="*50)

X2 = df[['horsepower', 'weight']].values

model2 = LinearRegression()
model2.fit(X2, y)

print(f"Intercepto: {model2.intercept_:.4f}")
print(f"Coeficiente horsepower: {model2.coef_[0]:.4f}")
print(f"Coeficiente weight: {model2.coef_[1]:.4f}")

# ===== VISUALIZACIÓN: DOS GRÁFICOS 3D =====
fig = plt.figure(figsize=(16, 7))

# --- GRÁFICO 1: cylinders + displacement → mpg ---
ax1 = fig.add_subplot(121, projection='3d')

# Puntos reales
ax1.scatter(X1[:, 0], X1[:, 1], y, color='blue', s=30, alpha=0.6, label='Datos reales')

# Crear malla para el plano de regresión
x1_range = np.linspace(X1[:, 0].min(), X1[:, 0].max(), 20)
x2_range = np.linspace(X1[:, 1].min(), X1[:, 1].max(), 20)
x1_grid, x2_grid = np.meshgrid(x1_range, x2_range)

# Calcular predicciones en la malla
y_grid1 = model1.intercept_ + model1.coef_[0] * x1_grid + model1.coef_[1] * x2_grid

# Dibujar plano de regresión
ax1.plot_surface(x1_grid, x2_grid, y_grid1, color='red', alpha=0.4, label='Plano de regresión')

# Etiquetas
ax1.set_xlabel('Cylinders', fontsize=11, labelpad=10)
ax1.set_ylabel('Displacement', fontsize=11, labelpad=10)
ax1.set_zlabel('MPG', fontsize=11, labelpad=10)
ax1.set_title('Modelo 1: Cylinders + Displacement → MPG', fontsize=13, fontweight='bold', pad=15)
ax1.view_init(elev=20, azim=45)

# --- GRÁFICO 2: horsepower + weight → mpg ---
ax2 = fig.add_subplot(122, projection='3d')

# Puntos reales
ax2.scatter(X2[:, 0], X2[:, 1], y, color='green', s=30, alpha=0.6, label='Datos reales')

# Crear malla para el plano de regresión
x1_range2 = np.linspace(X2[:, 0].min(), X2[:, 0].max(), 20)
x2_range2 = np.linspace(X2[:, 1].min(), X2[:, 1].max(), 20)
x1_grid2, x2_grid2 = np.meshgrid(x1_range2, x2_range2)

# Calcular predicciones en la malla
y_grid2 = model2.intercept_ + model2.coef_[0] * x1_grid2 + model2.coef_[1] * x2_grid2

# Dibujar plano de regresión
ax2.plot_surface(x1_grid2, x2_grid2, y_grid2, color='orange', alpha=0.4, label='Plano de regresión')

# Etiquetas
ax2.set_xlabel('Horsepower', fontsize=11, labelpad=10)
ax2.set_ylabel('Weight', fontsize=11, labelpad=10)
ax2.set_zlabel('MPG', fontsize=11, labelpad=10)
ax2.set_title('Modelo 2: Horsepower + Weight → MPG', fontsize=13, fontweight='bold', pad=15)
ax2.view_init(elev=20, azim=45)

plt.tight_layout()
plt.show()

# ===== TABLA COMPARATIVA DE RESULTADOS =====
print("\n" + "="*50)
print("COMPARACIÓN DE MODELOS")
print("="*50)


y_pred1 = model1.predict(X1)
y_pred2 = model2.predict(X2)

rmse_1 = np.sqrt(mean_squared_error(y, y_pred1))

rmse_2 = np.sqrt(mean_squared_error(y, y_pred2))

print(f"\nModelo 1 (cylinders + displacement):")
print(f"  RMSE: {rmse_1:.4f}")

print(f"\nModelo 2 (horsepower + weight):")
print(f"  RMSE: {rmse_2:.4f}")
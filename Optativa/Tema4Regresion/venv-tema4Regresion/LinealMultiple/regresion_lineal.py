import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error
from sklearn.model_selection import train_test_split

nombre_iteracion = "Iteración 1 - Regresión Lineal Múltiple"
print("=" * 60)
print(f"  {nombre_iteracion}")
print("=" * 60)

# Cargar datos
df = pd.read_csv('csv/dataset_de_partida.csv')

print(f"Filas iniciales: {len(df)}")

# Seleccionar solo las columnas necesarias (4 predictoras + charges)
df = df[['age', 'bmi', 'children', 'smoker', 'charges']]

# Eliminar valores nulos (la Regresión Lineal no acepta valores nulos)
df = df.dropna()
print(f"Filas después de eliminar nulos: {len(df)}")

# Convertir smoker a numérico (yes=1, no=0)
df['smoker'] = df['smoker'].map({'yes': 1, 'no': 0})

# Variables predictoras y variable objetivo
X = df[['age', 'bmi', 'children', 'smoker']].values
y = df['charges'].values

# Dividir en entrenamiento (80%) y prueba (20%)
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Crear y ajustar el modelo
model = LinearRegression()
model.fit(X_train, y_train)

# Predicciones
y_pred_train = model.predict(X_train)
y_pred_test = model.predict(X_test)

# Mostrar RMSE y coeficientes
rmse_train = np.sqrt(mean_squared_error(y_train, y_pred_train))
rmse_test = np.sqrt(mean_squared_error(y_test, y_pred_test))

print(f"\nRMSE Entrenamiento: ${rmse_train:,.2f}")
print(f"RMSE Prueba: ${rmse_test:,.2f}")
print(f"\nIntercepto (w₀): ${model.intercept_:,.2f}")
print("Coeficientes:")
feature_names = ['age', 'bmi', 'children', 'smoker']
for name, coef in zip(feature_names, model.coef_):
    print(f"  {name}: {coef:.4f}")
print("=" * 60)


# ===== GRÁFICA ÚNICA: ENTRENAMIENTO Y PRUEBA JUNTOS =====
fig, ax = plt.subplots(figsize=(12, 7))

# Scatter de entrenamiento
ax.scatter(y_train, y_pred_train, color='steelblue', alpha=0.5, s=30, label=f'Entrenamiento (RMSE: ${rmse_train:,.2f})')

# Scatter de prueba
ax.scatter(y_test, y_pred_test, color='green', alpha=0.6, s=30, label=f'Prueba (RMSE: ${rmse_test:,.2f})')

# Línea de ajuste perfecto
min_val = min(y_train.min(), y_test.min(), y_pred_train.min(), y_pred_test.min())
max_val = max(y_train.max(), y_test.max(), y_pred_train.max(), y_pred_test.max())
ax.plot([min_val, max_val], [min_val, max_val], color='red', linewidth=2, linestyle='--', label='Ajuste perfecto')

ax.set_title(f'{nombre_iteracion}\nValores Reales vs Predichos (Entrenamiento y Prueba)', fontsize=14, fontweight='bold')
ax.set_xlabel('Charges real ($)', fontsize=12)
ax.set_ylabel('Charges predicho ($)', fontsize=12)
ax.legend(fontsize=10)
ax.grid(True, alpha=0.3)
plt.tight_layout()
plt.show()
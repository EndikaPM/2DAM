import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.linear_model import Ridge
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error

nombre_iteracion = "Iteración 2 - Regresión Ridge (L2)"
print("=" * 60)
print(f"  {nombre_iteracion}")
print("=" * 60)

# Cargar datos
df = pd.read_csv('csv/dataset_de_partida.csv')

print(f"Filas iniciales: {len(df)}")

# Seleccionar solo las columnas necesarias (4 predictoras + charges)
df = df[['age', 'bmi', 'children', 'smoker', 'charges']]

# Eliminar valores nulos
df = df.dropna()
print(f"Filas después de eliminar nulos: {len(df)}")

# Convertir smoker a numérico (yes=1, no=0)
df['smoker'] = df['smoker'].map({'yes': 1, 'no': 0})

# Variables predictoras y variable objetivo
X = df[['age', 'bmi', 'children', 'smoker']].values
y = df['charges'].values

# Separar en conjunto de entrenamiento y prueba
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Crear y entrenar el modelo Ridge
ridge = Ridge(alpha=9.0)  # Parámetro de regularización λ
ridge.fit(X_train, y_train)

# Obtener predicciones
y_pred_train = ridge.predict(X_train)
y_pred_test = ridge.predict(X_test)

# Calcular RMSE
rmse_train = np.sqrt(mean_squared_error(y_train, y_pred_train))
rmse_test = np.sqrt(mean_squared_error(y_test, y_pred_test))

# Obtener coeficientes
coeficientes = ridge.coef_

# Mostrar resultados
print(f"\nRMSE Entrenamiento: ${rmse_train:,.2f}")
print(f"RMSE Prueba: ${rmse_test:,.2f}")
print(f"\nIntercepto: ${ridge.intercept_:,.2f}")
print("Coeficientes:")
feature_names = ['age', 'bmi', 'children', 'smoker']
for name, coef in zip(feature_names, coeficientes):
    print(f"  {name}: {coef:.4f}")
print("=" * 60)

# Mostrar algunos pares de puntos (primeros 10 del conjunto de prueba)
print("\nPrimeros 10 registros del conjunto de PRUEBA:")
print("Charges_real → Charges_predicho")
for i in range(min(10, len(y_test))):
    print(f"${y_test[i]:,.2f} → ${y_pred_test[i]:,.2f}")

# ===== CREAR LA FIGURA CON DOS SUBPLOTS =====
fig, axes = plt.subplots(1, 2, figsize=(14, 6))

# Subplot 1: Dispersión de los datos reales vs predicción
axes[0].scatter(y_test, y_pred_test, color='blue', alpha=0.6, s=30, label="Datos reales vs predicción")
axes[0].plot([min(y_test), max(y_test)], [min(y_test), max(y_test)], 
            color='red', linestyle='dashed', linewidth=2, label="Línea ideal")
axes[0].set_xlabel("Valores reales - Charges ($)", fontsize=11)
axes[0].set_ylabel("Predicciones - Charges ($)", fontsize=11)
axes[0].set_title(f"Regresión Ridge: Predicción vs Realidad\nRMSE Prueba: ${rmse_test:,.2f}", 
                fontsize=12, fontweight='bold')
axes[0].legend()
axes[0].grid(True, alpha=0.3)

# Subplot 2: Coeficientes del modelo Ridge
axes[1].bar(range(len(feature_names)), coeficientes, color='steelblue', edgecolor='black', linewidth=1.2)
axes[1].set_xlabel("Variables predictoras", fontsize=11)
axes[1].set_ylabel("Valor del coeficiente", fontsize=11)
axes[1].set_title("Coeficientes en Regresión Ridge", fontsize=12, fontweight='bold')
axes[1].axhline(0, color='black', linewidth=0.8)
axes[1].set_xticks(range(len(feature_names)))
axes[1].set_xticklabels(feature_names)
axes[1].grid(True, alpha=0.3, axis='y')

# Ajustar diseño y mostrar el gráfico
plt.tight_layout()
plt.show()
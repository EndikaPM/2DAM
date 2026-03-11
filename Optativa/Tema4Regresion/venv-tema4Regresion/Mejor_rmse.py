import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from xgboost import XGBRegressor
from sklearn.model_selection import train_test_split, GridSearchCV
from sklearn.metrics import mean_squared_error

nombre_iteracion = "XGBoost Optimizado - Búsqueda del Mejor Modelo"
print("=" * 70)
print(f"  {nombre_iteracion}")
print("=" * 70)

# ===== CARGAR Y LIMPIAR DATOS =====
df = pd.read_csv('csv/dataset_de_partida.csv')
print(f"Filas iniciales: {len(df)}")

df = df[['age', 'bmi', 'children', 'smoker', 'charges']].copy()

# Eliminar valores negativos (erróneos)
df = df[(df['age'] >= 0) & (df['bmi'] >= 0) & (df['children'] >= 0)].copy()

# Interpolación para nulos
df[['age', 'bmi', 'children']] = df[['age', 'bmi', 'children']].interpolate(method='linear').bfill().ffill()

if df['smoker'].isnull().sum() > 0:
    moda_smoker = df['smoker'].mode()[0] if len(df['smoker'].mode()) > 0 else 'no'
    df['smoker'] = df['smoker'].fillna(moda_smoker)

# NO eliminamos outliers (XGBoost los maneja bien)

df['smoker'] = df['smoker'].map({'yes': 1, 'no': 0})

if df.isnull().sum().sum() > 0:
    df = df.dropna()

print(f"Filas después de limpieza: {len(df)}")

X = df[['age', 'bmi', 'children', 'smoker']].values
y = df['charges'].values

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# ===== BÚSQUEDA DE HIPERPARÁMETROS CON GRID SEARCH =====
print("\n" + "=" * 70)
print("OPTIMIZANDO HIPERPARÁMETROS CON GRID SEARCH...")
print("=" * 70)

param_grid = {
    'n_estimators': [100, 200, 300, 500],
    'max_depth': [3, 5, 7, 10],
    'learning_rate': [0.01, 0.05, 0.1, 0.2],
    'min_child_weight': [1, 3, 5],
    'subsample': [0.8, 0.9, 1.0],
    'colsample_bytree': [0.8, 0.9, 1.0]
}

xgb_base = XGBRegressor(random_state=42, n_jobs=-1)

grid_search = GridSearchCV(
    estimator=xgb_base,
    param_grid=param_grid,
    cv=5,
    scoring='neg_mean_squared_error',
    verbose=1,
    n_jobs=-1
)

grid_search.fit(X_train, y_train)

print("\n" + "=" * 70)
print("MEJORES HIPERPARÁMETROS ENCONTRADOS:")
print("=" * 70)
for param, value in grid_search.best_params_.items():
    print(f"  {param}: {value}")

# ===== ENTRENAR MODELO FINAL CON MEJORES PARÁMETROS =====
best_model = grid_search.best_estimator_

y_pred_train = best_model.predict(X_train)
y_pred_test = best_model.predict(X_test)

rmse_train = np.sqrt(mean_squared_error(y_train, y_pred_train))
rmse_test = np.sqrt(mean_squared_error(y_test, y_pred_test))

print("\n" + "=" * 70)
print("RESULTADOS DEL MODELO OPTIMIZADO")
print("=" * 70)
print(f"RMSE Entrenamiento: ${rmse_train:,.2f}")
print(f"RMSE Prueba: ${rmse_test:,.2f}")
print(f"Gap: ${abs(rmse_train - rmse_test):,.2f}")

# Importancia de variables
feature_importance = best_model.feature_importances_
feature_names = ['age', 'bmi', 'children', 'smoker']

print("\nImportancia de las variables:")
for name, importance in zip(feature_names, feature_importance):
    print(f"  {name}: {importance:.4f} ({importance*100:.2f}%)")

print("=" * 70)

# ===== COMPARACIÓN CON OTROS MODELOS =====
print("\n" + "=" * 70)
print("COMPARACIÓN CON MODELOS ANTERIORES")
print("=" * 70)

from sklearn.linear_model import LinearRegression, Ridge
from sklearn.ensemble import RandomForestRegressor

# Linear Regression
lr = LinearRegression()
lr.fit(X_train, y_train)
rmse_lr = np.sqrt(mean_squared_error(y_test, lr.predict(X_test)))

# Ridge
ridge = Ridge(alpha=5.0)
ridge.fit(X_train, y_train)
rmse_ridge = np.sqrt(mean_squared_error(y_test, ridge.predict(X_test)))

# Random Forest
rf = RandomForestRegressor(n_estimators=300, max_depth=15, random_state=42)
rf.fit(X_train, y_train)
rmse_rf = np.sqrt(mean_squared_error(y_test, rf.predict(X_test)))

print(f"\nLinear Regression:     RMSE = ${rmse_lr:,.2f}")
print(f"Ridge (α=5.0):         RMSE = ${rmse_ridge:,.2f}")
print(f"Random Forest:         RMSE = ${rmse_rf:,.2f}")
print(f"XGBoost Optimizado:    RMSE = ${rmse_test:,.2f} ⭐")

mejora_vs_linear = rmse_lr - rmse_test
mejora_vs_ridge = rmse_ridge - rmse_test
mejora_vs_rf = rmse_rf - rmse_test

print(f"\nMejora vs Linear:      ${mejora_vs_linear:+,.2f}")
print(f"Mejora vs Ridge:       ${mejora_vs_ridge:+,.2f}")
print(f"Mejora vs Random Forest: ${mejora_vs_rf:+,.2f}")

# ===== VISUALIZACIÓN =====
fig, axes = plt.subplots(2, 2, figsize=(16, 12))

# Subplot 1: Predicción vs Realidad
axes[0, 0].scatter(y_test, y_pred_test, color='purple', alpha=0.6, s=30, label='Predicciones XGBoost')
axes[0, 0].plot([min(y_test), max(y_test)], [min(y_test), max(y_test)], 
                'r--', linewidth=2, label='Línea ideal')
axes[0, 0].set_xlabel('Charges Real ($)', fontsize=11)
axes[0, 0].set_ylabel('Charges Predicho ($)', fontsize=11)
axes[0, 0].set_title(f'XGBoost Optimizado: Predicción vs Realidad\nRMSE: ${rmse_test:,.2f}', 
                    fontsize=12, fontweight='bold')
axes[0, 0].legend()
axes[0, 0].grid(True, alpha=0.3)

# Subplot 2: Importancia de variables
axes[0, 1].barh(feature_names, feature_importance, color='darkgreen', edgecolor='black', linewidth=1.2)
axes[0, 1].set_xlabel('Importancia', fontsize=11)
axes[0, 1].set_ylabel('Variables', fontsize=11)
axes[0, 1].set_title('Importancia de Variables (XGBoost)', fontsize=12, fontweight='bold')
axes[0, 1].grid(True, alpha=0.3, axis='x')

# Subplot 3: Comparación de modelos
modelos = ['Linear\nRegression', 'Ridge\n(α=5.0)', 'Random\nForest', 'XGBoost\nOptimizado']
rmses = [rmse_lr, rmse_ridge, rmse_rf, rmse_test]
colores = ['steelblue', 'orange', 'green', 'purple']

axes[1, 0].bar(modelos, rmses, color=colores, edgecolor='black', linewidth=1.5)
axes[1, 0].set_ylabel('RMSE Prueba ($)', fontsize=11)
axes[1, 0].set_title('Comparación de Modelos', fontsize=12, fontweight='bold')
axes[1, 0].grid(True, alpha=0.3, axis='y')
for i, v in enumerate(rmses):
    axes[1, 0].text(i, v + 100, f'${v:,.0f}', ha='center', fontweight='bold', fontsize=10)

# Subplot 4: Residuos
residuos = y_test - y_pred_test
axes[1, 1].scatter(y_pred_test, residuos, color='purple', alpha=0.6, s=30)
axes[1, 1].axhline(y=0, color='red', linestyle='--', linewidth=2)
axes[1, 1].set_xlabel('Predicciones ($)', fontsize=11)
axes[1, 1].set_ylabel('Residuos ($)', fontsize=11)
axes[1, 1].set_title('Análisis de Residuos', fontsize=12, fontweight='bold')
axes[1, 1].grid(True, alpha=0.3)

plt.tight_layout()
plt.show()

print("\n" + "=" * 70)
print("PROCESO COMPLETADO ✅")
print("=" * 70)
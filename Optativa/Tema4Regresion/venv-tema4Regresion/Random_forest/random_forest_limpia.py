import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.ensemble import RandomForestRegressor
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error

nombre_iteracion = "Iteración 3 - Random Forest Regressor con preprocesamiento"
print("=" * 60)
print(f"  {nombre_iteracion}")
print("=" * 60)

# Cargar datos
df = pd.read_csv('csv/dataset_de_partida.csv')

print(f"Filas iniciales: {len(df)}")

# Seleccionar solo las columnas necesarias (4 predictoras + charges)
df = df[['age', 'bmi', 'children', 'smoker', 'charges']].copy()

# ===== 1. ELIMINAR VALORES NEGATIVOS (ERRÓNEOS) =====
print("\n--- PASO 1: Eliminar valores negativos ---")
print(f"Negativos en age: {(df['age'] < 0).sum()}")
print(f"Negativos en bmi: {(df['bmi'] < 0).sum()}")
print(f"Negativos en children: {(df['children'] < 0).sum()}")

df = df[(df['age'] >= 0) & (df['bmi'] >= 0) & (df['children'] >= 0)].copy()
print(f"Filas después de eliminar negativos: {len(df)}")

# ===== 2. IMPUTAR VALORES NULOS CON INTERPOLACIÓN =====
print("\n--- PASO 2: Imputar valores nulos ---")
print(f"Nulos antes de imputar:")
print(df.isnull().sum())

# Interpolación lineal para variables numéricas
df_interpolado = df.copy()
df_interpolado[['age', 'bmi', 'children']] = df_interpolado[['age', 'bmi', 'children']].interpolate(method='linear')
df_interpolado[['age', 'bmi', 'children']] = df_interpolado[['age', 'bmi', 'children']].bfill().ffill()

# Moda para smoker
if df_interpolado['smoker'].isnull().sum() > 0:
    moda_smoker = df_interpolado['smoker'].mode()[0] if len(df_interpolado['smoker'].mode()) > 0 else 'no'
    df_interpolado['smoker'] = df_interpolado['smoker'].fillna(moda_smoker)

df = df_interpolado.copy()

print(f"\nNulos después de imputar:")
print(df.isnull().sum())
print(f"Filas después de imputar: {len(df)}")

# ===== 3. ELIMINAR OUTLIERS CON IQR (solo age y bmi) =====
print("\n--- PASO 3: Eliminar outliers con IQR ---")

def eliminar_outliers_iqr(dataframe, columna):
    Q1 = dataframe[columna].quantile(0.25)
    Q3 = dataframe[columna].quantile(0.75)
    IQR = Q3 - Q1
    limite_inferior = Q1 - 1.5 * IQR
    limite_superior = Q3 + 1.5 * IQR
    
    outliers_count = ((dataframe[columna] < limite_inferior) | 
                    (dataframe[columna] > limite_superior)).sum()
    
    print(f"\n{columna}:")
    print(f"  Q1 = {Q1:.2f}, Q3 = {Q3:.2f}, IQR = {IQR:.2f}")
    print(f"  Límite inferior = {limite_inferior:.2f}")
    print(f"  Límite superior = {limite_superior:.2f}")
    print(f"  Outliers detectados: {outliers_count}")
    
    dataframe_filtrado = dataframe[(dataframe[columna] >= limite_inferior) & 
                        (dataframe[columna] <= limite_superior)].copy()
    
    return dataframe_filtrado

df = eliminar_outliers_iqr(df, 'age')
print(f"Filas después de eliminar outliers en age: {len(df)}")

df = eliminar_outliers_iqr(df, 'bmi')
print(f"Filas después de eliminar outliers en bmi: {len(df)}")

print(f"\n{'='*60}")
print(f"RESUMEN FINAL DE PREPROCESAMIENTO:")
print(f"Filas finales: {len(df)}")
print(f"{'='*60}")

# ===== 4. PREPARAR DATOS PARA EL MODELO =====

# Convertir smoker a numérico (yes=1, no=0)
df['smoker'] = df['smoker'].map({'yes': 1, 'no': 0})

# Verificar nulos finales
print(f"\nVerificación final de nulos:")
print(df.isnull().sum())

if df.isnull().sum().sum() > 0:
    print(f"\nADVERTENCIA: Quedan {df.isnull().sum().sum()} nulos. Eliminando filas...")
    df = df.dropna()
    print(f"Filas después de eliminar nulos restantes: {len(df)}")

# Variables predictoras y variable objetivo
X = df[['age', 'bmi', 'children', 'smoker']].values
y = df['charges'].values

# Dividir en conjuntos de entrenamiento y prueba
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# ===== 5. CREAR Y ENTRENAR EL MODELO RANDOM FOREST =====
print("\n--- MODELO RANDOM FOREST ---")

rf = RandomForestRegressor(n_estimators=600, random_state=42)
rf.fit(X_train, y_train)

# Predicciones
y_pred_train = rf.predict(X_train)
y_pred_test = rf.predict(X_test)

# Evaluación del modelo
rmse_train = np.sqrt(mean_squared_error(y_train, y_pred_train))
rmse_test = np.sqrt(mean_squared_error(y_test, y_pred_test))

print(f"\nRMSE Entrenamiento: ${rmse_train:,.2f}")
print(f"RMSE Prueba: ${rmse_test:,.2f}")
print(f"Diferencia (Gap): ${abs(rmse_train - rmse_test):,.2f}")

# Importancia de las características
feature_importance = rf.feature_importances_
feature_names = ['age', 'bmi', 'children', 'smoker']

print("\nImportancia de las variables:")
for name, importance in zip(feature_names, feature_importance):
    print(f"  {name}: {importance:.4f} ({importance*100:.2f}%)")
print("=" * 60)

# Mostrar algunos pares de puntos (primeros 10 del conjunto de prueba)
print("\nPrimeros 10 registros del conjunto de PRUEBA:")
print("Charges_real → Charges_predicho")
for i in range(min(10, len(y_test))):
    print(f"${y_test[i]:,.2f} → ${y_pred_test[i]:,.2f}")

# ===== 6. VISUALIZACIÓN =====
fig, axes = plt.subplots(1, 2, figsize=(14, 6))

# Subplot 1: Predicción vs Realidad
axes[0].scatter(y_test, y_pred_test, color='blue', alpha=0.6, s=30, label='Predicciones')
axes[0].plot([min(y_test), max(y_test)], [min(y_test), max(y_test)], 
            color='red', linestyle='dashed', linewidth=2, label='Línea ideal')
axes[0].set_xlabel('Valores reales - Charges ($)', fontsize=11)
axes[0].set_ylabel('Predicciones - Charges ($)', fontsize=11)
axes[0].set_title(f'Random Forest: Predicción vs Realidad\nRMSE Prueba: ${rmse_test:,.2f}', 
                fontsize=12, fontweight='bold')
axes[0].legend()
axes[0].grid(True, alpha=0.3)

# Subplot 2: Importancia de las variables
axes[1].barh(feature_names, feature_importance, color='forestgreen', edgecolor='black', linewidth=1.2)
axes[1].set_xlabel('Importancia', fontsize=11)
axes[1].set_ylabel('Variables predictoras', fontsize=11)
axes[1].set_title('Importancia de las Variables en Random Forest', fontsize=12, fontweight='bold')
axes[1].grid(True, alpha=0.3, axis='x')

plt.tight_layout()
plt.show()

# ===== 7. ESTADÍSTICAS DESCRIPTIVAS FINALES =====
print("\n--- ESTADÍSTICAS DESCRIPTIVAS DESPUÉS DEL PREPROCESAMIENTO ---")
print(df[['age', 'bmi', 'children', 'charges']].describe())
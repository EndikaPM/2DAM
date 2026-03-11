import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error
from sklearn.model_selection import train_test_split

nombre_iteracion = "Iteración 1 - Regresión Lineal Múltiple (con preprocesamiento)"
print("=" * 60)
print(f"  {nombre_iteracion}")
print("=" * 60)

# Cargar datos
df = pd.read_csv('csv/dataset_de_partida.csv')

print(f"Filas iniciales: {len(df)}")

# Seleccionar solo las columnas necesarias (4 predictoras + charges)
df = df[['age', 'bmi', 'children', 'smoker', 'charges']].copy()

# ===== 1. ELIMINAR VALORES NEGATIVOS =====
print("\n--- PASO 1: Eliminar valores negativos ---")
print(f"Negativos en age: {(df['age'] < 0).sum()}")
print(f"Negativos en bmi: {(df['bmi'] < 0).sum()}")
print(f"Negativos en children: {(df['children'] < 0).sum()}")

# Eliminar filas con valores negativos en age, bmi o children
df = df[(df['age'] >= 0) & (df['bmi'] >= 0) & (df['children'] >= 0)].copy()
print(f"Filas después de eliminar negativos: {len(df)}")

# ===== 2. IMPUTAR VALORES NULOS =====
print("\n--- PASO 2: Imputar valores nulos ---")
print(f"Nulos antes de imputar:")
print(df.isnull().sum())

# Interpolación lineal para variables numéricas (media entre anterior y posterior)
df_interpolado = df.copy()
df_interpolado[['age', 'bmi', 'children']] = df_interpolado[['age', 'bmi', 'children']].interpolate(method='linear')

# Si quedan nulos al principio o final (interpolate no los cubre), rellenar con bfill y ffill
df_interpolado[['age', 'bmi', 'children']] = df_interpolado[['age', 'bmi', 'children']].bfill().ffill()

# Imputar smoker con la moda (valor más frecuente)
if df_interpolado['smoker'].isnull().sum() > 0:
    moda_smoker = df_interpolado['smoker'].mode()[0] if len(df_interpolado['smoker'].mode()) > 0 else 'no'
    df_interpolado['smoker'] = df_interpolado['smoker'].fillna(moda_smoker)

# Actualizar df con los datos interpolados
df = df_interpolado.copy()

print(f"\nNulos después de imputar:")
print(df.isnull().sum())
print(f"Filas después de imputar: {len(df)}")

# ===== 3. ELIMINAR OUTLIERS CON IQR (solo en age y bmi) =====
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
    
    # Filtrar datos dentro del rango
    dataframe_filtrado = dataframe[(dataframe[columna] >= limite_inferior) & 
                        (dataframe[columna] <= limite_superior)].copy()
    
    return dataframe_filtrado

# Aplicar eliminación de outliers para age
df = eliminar_outliers_iqr(df, 'age')
print(f"Filas después de eliminar outliers en age: {len(df)}")

# Aplicar eliminación de outliers para bmi
df = eliminar_outliers_iqr(df, 'bmi')
print(f"Filas después de eliminar outliers en bmi: {len(df)}")

print(f"\n{'='*60}")
print(f"RESUMEN FINAL DE PREPROCESAMIENTO:")
print(f"Filas finales: {len(df)}")
print(f"{'='*60}")

# ===== 4. PREPARAR DATOS PARA EL MODELO =====

# Convertir smoker a numérico (yes=1, no=0)
df['smoker'] = df['smoker'].map({'yes': 1, 'no': 0})

# Verificar que no hay nulos después de la conversión
print(f"\nVerificación final de nulos:")
print(df.isnull().sum())

# Si aún hay nulos, eliminar esas filas
if df.isnull().sum().sum() > 0:
    print(f"\nADVERTENCIA: Quedan {df.isnull().sum().sum()} nulos. Eliminando filas...")
    df = df.dropna()
    print(f"Filas después de eliminar nulos restantes: {len(df)}")

# Variables predictoras y variable objetivo
X = df[['age', 'bmi', 'children', 'smoker']].values
y = df['charges'].values

# Dividir en entrenamiento (80%) y prueba (20%)
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# ===== 5. CREAR Y AJUSTAR EL MODELO =====
print("\n--- MODELO DE REGRESIÓN LINEAL MÚLTIPLE ---")

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
print(f"Diferencia (Gap): ${abs(rmse_train - rmse_test):,.2f}")
print(f"\nIntercepto (w₀): ${model.intercept_:,.2f}")
print("Coeficientes:")
feature_names = ['age', 'bmi', 'children', 'smoker']
for name, coef in zip(feature_names, model.coef_):
    print(f"  {name}: {coef:.4f}")
print("=" * 60)

# ===== 6. GRÁFICA ÚNICA: ENTRENAMIENTO Y PRUEBA JUNTOS =====
fig, ax = plt.subplots(figsize=(12, 7))

# Scatter de entrenamiento
ax.scatter(y_train, y_pred_train, color='steelblue', alpha=0.5, s=30, 
        label=f'Entrenamiento (RMSE: ${rmse_train:,.2f})')

# Scatter de prueba
ax.scatter(y_test, y_pred_test, color='green', alpha=0.6, s=30, 
        label=f'Prueba (RMSE: ${rmse_test:,.2f})')

# Línea de ajuste perfecto
min_val = min(y_train.min(), y_test.min(), y_pred_train.min(), y_pred_test.min())
max_val = max(y_train.max(), y_test.max(), y_pred_train.max(), y_pred_test.max())
ax.plot([min_val, max_val], [min_val, max_val], color='red', linewidth=2, 
        linestyle='--', label='Ajuste perfecto')

ax.set_title(f'{nombre_iteracion}\nValores Reales vs Predichos (Entrenamiento y Prueba)', 
            fontsize=14, fontweight='bold')
ax.set_xlabel('Charges real ($)', fontsize=12)
ax.set_ylabel('Charges predicho ($)', fontsize=12)
ax.legend(fontsize=10)
ax.grid(True, alpha=0.3)
plt.tight_layout()
plt.show()

# ===== 7. ESTADÍSTICAS DESCRIPTIVAS FINALES =====
print("\n--- ESTADÍSTICAS DESCRIPTIVAS DESPUÉS DEL PREPROCESAMIENTO ---")
print(df[['age', 'bmi', 'children', 'charges']].describe())
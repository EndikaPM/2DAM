import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("venv_mathplotlib/PrecioOilDataset.csv")

# Limpiar y convertir volumen
df['Volume'] = df["Volume"].str.replace(",", "").astype(int)

# Filtrar por fecha específica
fecha_especifica = '01/01/2015'
df_fecha = df[df['Production Date'] == fecha_especifica]

# Contar cuántos registros hay por tipo de Land Class
conteo = df_fecha['Land Class'].value_counts()

# Crear gráfico
conteo.plot(kind='bar', title=f'Registros por tipo de Land Class ({fecha_especifica})', figsize=(10,6))
plt.xlabel('Land Class')
plt.ylabel('Número de registros')
plt.show()

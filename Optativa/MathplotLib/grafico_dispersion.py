import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("venv_mathplotlib/PrecioOilDataset.csv")

df['Volume'] = df["Volume"].str.replace(",", "")
df['Volume'] = df["Volume"].astype(int)

# Limpiar los datos (convertir Volumen a número y fecha)
df["Production Date"] = pd.to_datetime(df["Production Date"], errors="coerce")

# Gráfico de dispersión
plt.figure(figsize=(10,6))
plt.scatter(df["Production Date"], df["Volume"], alpha=0.5)
plt.title("Dispersión de Volumen de Producción a lo largo del tiempo")
plt.xlabel("Fecha de Producción")
plt.ylabel("Volumen")
plt.grid(True, linestyle='--', alpha=0.7)
plt.tight_layout()
plt.show()

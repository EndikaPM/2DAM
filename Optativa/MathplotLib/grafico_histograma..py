import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("venv_mathplotlib/PrecioOilDataset.csv")
df['Volume'] = df["Volume"].str.replace(",", "").astype(int)

fecha_especifica = '01/01/2015'
df_fecha = df[df['Production Date'] == fecha_especifica]


df_fecha.plot(
    kind='hist',
    bins=20,
    title='Distribución de volumen de producción',
    figsize=(10, 6)
)

plt.xlabel('Volumen')
plt.ylabel('Frecuencia')
plt.show()  

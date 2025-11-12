"""Actividad realizada por parejas y presentada al resto de la clase. En esta actividad vas a
usar el mismo dataset que usasteis en la unidad didáctica anterior y vais a probar todos
los tipos de gráficos presentados en el apartado 8 (de líneas, de dispersión, de barras,
histograma y diagrama de cajas). Cada gráfico irá acompañado de su código y de un texto
que indique cómo se interpreta el mismo acorde a los datos que están presentes en el
dataset."""
import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("venv_mathplotlib/PrecioOilDataset.csv")

df['Volume'] = df["Volume"].str.replace(",", "")
df['Volume'] = df["Volume"].astype(int)

# Crear solo la figura
fig = plt.figure()
print(type(fig))

# Agrupar por fecha y calcular el total de camas ocupadas en planta
produccion_planta = df.groupby('Production Date')['Volume'].sum()
# Crear un gráfico de líneas
produccion_planta.plot(title='Produccion mensual', figsize=(10, 4))
plt.show()
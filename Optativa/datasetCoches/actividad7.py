import pandas as pd

df = pd.read_csv("tema2/carDataset.csv")

df_pais = pd.DataFrame({
    "origin": [1, 2, 3],
    "region": ["USA", "Europa", "Japon"],
    "continente": ["America", "Europa", "Asia"],
    "divisa": ["Dollar", "Euro", "Yen"]
})

df_com = pd.merge(df, df_pais, on='origin', how='left')

print(df_com.head())
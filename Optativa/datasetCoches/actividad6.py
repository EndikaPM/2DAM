import pandas as pd
from sklearn.model_selection import train_test_split

df = pd.read_csv("tema2/carDataset.csv")

def clasificar_pais(row):
    if row["origin"] == 1:
        return "USA"
    elif row["origin"] == 2:
        return "Europa"
    else:
        return "Japon"

df["Tipo_Motor"] = df.apply(clasificar_pais, axis=1)
print(df[["Tipo_Motor", "origin", "car_name"]].head())
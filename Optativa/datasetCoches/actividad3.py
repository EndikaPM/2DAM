import pandas as pd

df = pd.read_csv("tema2/carDataset.csv")

def check_weight(weight):
    
    if weight < 2500:
        return "ligero"
    elif weight >2500 and weight < 3500:
        return "medio"
    else:
        return "pesado"

df["weight_category"] = df["weight"].apply(check_weight)

# MUESTRA UN EJEMPLO CON DATOS
print("Datos con la nueva columna 'Peso':\n",
        f"{df[["weight_category", "weight"]].head()}")    

# APLICAR ONE-HOT ENCODING A LA NUEVA COLUMNA
df_encoded = pd.get_dummies(df, columns = ["weight_category"], drop_first = False)


print("\nDespués de aplicar One-Hot Encoding:\n",
        f"{df_encoded[["weight", "weight_category_ligero", "weight_category_medio", "weight_category_pesado"]].head()}\n")
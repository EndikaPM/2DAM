import pandas as pd
from sklearn.model_selection import train_test_split

df = pd.read_csv("tema2/carDataset.csv")


# Variables predictoras
X = df[["cylinders","displacement","horsepower","weight","acceleration","model_year","origin"]]
# Variable objetivo
y = df["mpg"]

X = pd.get_dummies(df, columns=["cylinders","displacement","horsepower","weight","acceleration","model_year","origin"], drop_first=False)

print("Entrenamiento",X.head())
print("Prediccion",y.head())

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Mostrar resultados
print("Tamaño del conjunto de entrenamiento:", X_train.shape[0])
print("Tamaño del conjunto de prueba:", X_test.shape[0])
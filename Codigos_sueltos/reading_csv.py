import pandas as pd

data = pd.read_csv(r"../Constantes/data.csv")
print(data.columns)
pd.set_option("display.max.columns", None)
# print(data.head())
# print(data.tail())
# print(data["Departamento o Distrito "].unique())
# print(data.groupby("Departamento o Distrito ").count())
# print(data.describe())
print(data.groupby('Sexo').count()["ID de caso"])

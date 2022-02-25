"""
Descripción y  requerimiento:

Como consultor técnico de la WHO (World Health Organization) se le ha solicitado que organice
la información necesaria para realizaruna gráfica qué: por continente, muestre la evolución del
promedio de la razón entre el número total de casos de COVID-19 y el número total de camas de
hospital disponibles a través del tiempo.

Para probar esta tarea, usted cuenta con el archivo de datos owid-covid-data.csv. Escriba una
función que reciba como parámetro una cadena con la ruta dónde se encuentra guardado el archivo,
incluyendo la extensión, y lo lea desde esta misma. A partir de estos datos, construya un
dataframe sobre el cual, al utilizar el método df.plot() se obtenga la
gráfica esperada

Valide que la extensión del nombre del archivo sea de tipo csv(coma separated values). En caso
contrario, retornela siguiente cadena: “Extensión  inválida.”. Utilice un  bloque try except para
leer el  archivo.  En  caso de  error, retornela siguiente cadena: “Error al leer el archivo de
datos.”

Tenga en cuenta las siguientes consideraciones:

    • La fecha debe ser el índice principal del dataframe (No es una columna) y debe transformarse
    aun objeto tipo fechade pandas
    • El archivo de datos originalen sus registros NO cuenta con la información correspondiente al
    total de númerode casos ni de camas de hospital disponibles ni la razón entre estos. Usted debe
    realizar el cálculo correspondiente en función de las demás columnas que sí se encuentran en el
    archivo original.
    • Recuerde que su función no debe imprimir la gráfica sino retornar un diccionario.


"""

import pandas as pd


def caso_who(ruta_archivo_csv: str) -> dict:
    if ruta_archivo_csv[-4:len(ruta_archivo_csv)] != ".csv":
        return "Extensión inválida."
    try:
        covid_data = pd.read_csv(ruta_archivo_csv, na_values=["?"])
        covid_data.dropna(subset=["continent"], inplace=True)

        covid_data["total_cases"] = (covid_data["total_cases_per_million"] * covid_data["population"]) / 1_000_000
        covid_data["beds"] = (covid_data["hospital_beds_per_thousand"] * covid_data["population"]) / 1_000
        available = pd.Series(covid_data["total_cases"] / covid_data["beds"])

        df_answer = pd.DataFrame({"date": pd.to_datetime(pd.Series(covid_data["date"])),
                                  "continent": covid_data["continent"],
                                  "available": available})
        df_answer = df_answer.groupby(by=["date", "continent"])["available"].mean().reset_index()
        df_answer = df_answer.pivot(index="date", columns="continent", values="available")
        return df_answer.to_dict()
    except:
        return "Error al leer el archivo de datos."


print(caso_who("owid-covid-data.csv"))

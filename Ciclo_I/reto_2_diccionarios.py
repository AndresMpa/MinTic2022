"""
Descripción: Usted trabaja en una entidad financiera que cuenta con la siguiente información en base a la que
realizan la evaluación de nuevas solicitudes de crédito:

Nombre              Abreviación     Tipo    Descripción
id_prestamo         N/A             str     código único alfanumérico que identifica el prestamo
casado              N/A             str     Aplicante es casado (Si / No)
dependientes        N/A             int/str Cantidad de personas dependientes del aplicante(0 / 1 / 2/ ‘3+’)
educacion           N/A             str     Nivel de educación de la persona (Graduado / No Graduado)
independiente       N/A             str     Aplicante es independiente (Si / No)
ingreso_deudor      i_d             float   Ingreso del aplicante
ingreso_codeudor    i_c             float   Ingreso del codeudor
cantidad_prestamo   c_p             float   Cantidad decrédito solicitada
plazo_prestamo      p_p             int     Plazo del crédito
historia_credito    N/A             int     Aplicante cuenta con historia crediticia favorable (1 / 0)
tipo_propiedad      N/A             str     Urbana / Rural / Semi Urbana

Recientemente, su empleadoradquirió un modelo basado en árboles de decisión para poder realizarmás fácilmente
una primera revisión de estas solicitudes. Utilizando python, escribauna funciónque reciba como parámetro un
diccionarioen el cuál las llaves son los nombres de las variables mencionadas anteriormente. Retorne un nuevo
diccionariocon las llaves “id_prestamo” y “aprobado” dónde esta última tenga como valor una variable booleana
representando la salida del árbol de decisión. Es decir, informando si el préstamo debe ser aprobadoono.
"""


from Constantes import constantes_ciclo_1 as cts


def crear_diccionario(template: dict) -> dict:
    nuevo_diccionarios = template.copy()
    for iterator in template.keys():
        nuevo_diccionarios.update({iterator: input("Add data to " + iterator + ": ")})
    return nuevo_diccionarios


def prestamo(informacion: dict) -> dict:
    prestamos_por_id = {
        "id_prestamo": informacion["id_prestamo"],
        "aprobado": False
    }

    dependientes = 0

    if isinstance(informacion["dependientes"], str):
        dependientes = informacion["dependientes"][:1]
    else:
        dependientes = informacion["dependientes"]

    if informacion["historia_credito"] == 1:
        if informacion["ingreso_codeudor"] > 0 and informacion["ingreso_deudor"] / 9 > informacion["cantidad_prestamo"]:
            prestamos_por_id.update({"aprobado": True})
        else:
            if int(dependientes) > 2 and informacion["independiente"].upper() == "SI":
                if informacion["ingreso_codeudor"] / 12 > informacion["cantidad_prestamo"]:
                    prestamos_por_id.update({"aprobado": True})
                else:
                    prestamos_por_id.update({"aprobado": False})
            else:
                if informacion["cantidad_prestamo"] < 200:
                    prestamos_por_id.update({"aprobado": True})
                else:
                    prestamos_por_id.update({"aprobado": False})
    else:
        if informacion["independiente"].upper() == "SI":
            if informacion["casado"] == "NO" and int(dependientes) <= 1:
                if informacion["ingreso_deudor"] / 10 > informacion["cantidad_prestamo"] or informacion["ingreso_codeudor"] / 10 > informacion["cantidad_prestamo"]:
                    if informacion["cantidad_prestamo"] < 180:
                        prestamos_por_id.update({"aprobado": True})
                    else:
                        prestamos_por_id.update({"aprobado": False})
                else:
                    prestamos_por_id.update({"aprobado": False})
            else:
                prestamos_por_id.update({"aprobado": False})
        else:
            if not (informacion["tipo_propiedad"].upper() == "SEMIURBANO") and int(dependientes) < 2:
                if informacion["educacion"].upper() == "GRADUADO":
                    if informacion["ingreso_deudor"] / 11 > informacion["cantidad_prestamo"] and informacion["ingreso_codeudor"] / 11 > informacion["cantidad_prestamo"]:
                        prestamos_por_id.update({"aprobado": True})
                    else:
                        prestamos_por_id.update({"aprobado": False})
                else:
                    prestamos_por_id.update({"aprobado": False})
            else:
                prestamos_por_id.update({"aprobado": False})
    return prestamos_por_id


# TEST

print(prestamo(cts.reto_2_test_1))

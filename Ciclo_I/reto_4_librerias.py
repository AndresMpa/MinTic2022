"""
Descripción:
Usted trabaja en una universidad que desea calcular los promedios ponderados por facultad para un muestreo aleatorio de
la información de algunos de sus estudiantes. Esta información ha sido organizada un diccionario de Python donde las
claves son los códigos únicos de los estudiantes seleccionados. Estos códigos llevan la siguiente estructura:
{año de ingreso}{periodo de ingreso}{5 numeros adicionales}.

Adicionalmente, cada uno de los elementos de la lista materiases un nuevo diccionario que contiene la información de las
materias.

Finalmente, por cuestiones de privacidad, es necesario informaralos estudiantes que su información ha sido pre
seleccionada para la realización de este informe. Se sabe que el correo institucional de cualquier estudiante tiene la
siguiente estructura:

    •Si el estudiante tiene dos nombres:
        -   {primera letra del primer nombre}
        -   {primera letra del segundo nombre}
        -   {primer apellido}
        -   {dos últimos números del documento}

    •Si el estudiante tiene un solo nombre:
        -   {primera letra del primer nombre}
        -   {primera letra del primer apellido}
        -   {segundo apellido}
        -   {dos últimos números del documento}

Requerimiento:
Escriba una función que reciba un diccionario que contiene la información previamente especificada, así como una
variable booleana contando_externos. Retorneuna tupla en la que el primer elemento es un diccionario cuyas claves sean
los nombres de las facultades, ordenados alfabéticamente, y los valores sean el promedio ponderado de las materias
correspondientes. El segundo elemento de la tupla debe ser una lista con los correos institucionales de todos los
estudiantes utilizados para el cálculo del promedio. Para el cálculo del promedio tenga en cuenta:

    a) Solamente las notas para las que el estudiante NO retiró la materia.

    b) Si el argumento contando_externos es False, no considere aquellas notas donde el programa de la materia no
    coincida con el programa del estudiante ni aquellos cuyo periodo de ingreso sea ‘curso de verano’, representado
    como un 05 en el código del estudiante

Validaciones:
Utilice un bloque try-exceptpara el cálculo del promedio. En caso de error, retorne “Error numérico.”
"""

from Constantes import constantes_ciclo_1 as cts


def ajuste_de_texto(text: str) -> str:
    text = text.lower()
    text = text.replace("á", "a")
    text = text.replace("é", "e")
    text = text.replace("í", "i")
    text = text.replace("ó", "o")
    text = text.replace("ú", "u")
    text = text.replace("ñ", "n")
    return text


def generar_correo(nombre: str, apellido: str, identificacion: str) -> str:
    nombre = ajuste_de_texto(nombre)
    apellido = ajuste_de_texto(apellido)

    if nombre.find(" ") > 0:
        correo = nombre[0] + nombre[nombre.find(" ") + 1] + "." + apellido[apellido.index(" ") + 1:len(apellido)]
    else:
        correo = nombre[0] + apellido[apellido.index(" ") + 1] + "." + apellido[0:apellido.index(",")]

    return correo + identificacion[-2:len(identificacion)]


def promedio_de_notas_del_estudiante(notas: list) -> dict:
    notas.sort()
    promedios = {}
    for item in notas:
        promedios.update({item[0]: 0})

    for item in promedios:
        divisor = 0
        for iterator in notas:
            if iterator[0] == item:
                promedios[item] += iterator[1] * iterator[2]
                divisor += iterator[2]
        promedios[item] /= divisor

    for item in promedios:
        promedios[item] = round(promedios[item], 2)

    return promedios


def promedio_facultades(info: dict, contando_externos: bool = True) -> tuple:
    try:
        correos = []
        promedio_de_los_estudiantes = []

        for llaves in info.keys():
            usado = False
            nombres = info[llaves]["nombres"]
            apellidos = info[llaves]["apellidos"]
            documentos = info[llaves]["documento"]
            programa = info[llaves]["programa"]
            materias = info[llaves]["materias"]

            for item in materias:
                if item["retirada"] == "No" and item["creditos"] > 0:
                    if contando_externos:
                        usado = True
                        promedio_de_los_estudiantes.append([item["facultad"], item["nota"], item["creditos"]])
                    elif not contando_externos and programa == item["codigo"][0:item["codigo"].index("-")]:
                        usado = True
                        promedio_de_los_estudiantes.append([item["facultad"], item["nota"], item["creditos"]])

            if usado:
                correos.append(generar_correo(nombres, apellidos, str(documentos)))

        correos.sort()
        promedio_de_los_estudiantes = promedio_de_notas_del_estudiante(promedio_de_los_estudiantes)

        return tuple([promedio_de_los_estudiantes, correos])
    except:
        return "Error numérico."


"""
CONSIDERACIONES IMPORTANTES PARA LOS CORREOS ELECTRÓNICOS:
* No debe tener duplicados
Debe estar completamente en minúsculas
No debe tener acentos

EL PROMEDIO DE LA FACULTAD: 
* Debe reportarse redondeado a dos decimales
* No debe considerar las materias retiradas
* Si contando_externos es False, no debe considerar materias electivas ni vacacionales
"""

# Prueba 1:
print(promedio_facultades(cts.reto_4_test_1))
# Expected return:

"""
({'Arquitectura': 3.81, 'Diseño': 3.58, 'Ingenieria': 3.63, 'Medicina': 3.08}, ['cp.lopez35', 'ds.guitierrez32', 
'gg.alvarez37', 'jj.lopez21', 'jj.lopez95', 'jn.ramirez71', 'mg.romero51', 'sc.fernandez43', 'sc.gomez00', 
'sr.cordoba25'])
"""

# Prueba 2:
print(promedio_facultades(cts.reto_4_test_2, False))
# Expected return:

"""
({'Arquitectura': 3.84, 'Diseño': 3.37, 'Historia del Arte': 3.66, 'Ingenieria': 3.88, 'Medicina':
3.45}, ['aj.romero88', 'cn.gomez49', 'cp.diaz17', 'cv.guitierrez49', 'cv.lopez69', 'jf.sanchez46',
'jm.fernandez20', 'jp.cordoba36', 'lc.perez11', 'mj.lopez07', 'np.alvarez22', 'pn.jimenez77', 'sg.moreno12',
'sn.alvarez97'])
"""

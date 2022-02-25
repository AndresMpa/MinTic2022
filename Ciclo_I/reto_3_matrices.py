"""
Descripción.
Usted trabaja en una compañía que a diario debe realizar el proceso de entrega de sus productos. Para lograr
este propósito, un camión se carga completamente al inicio del día, saliendo del depósito(home) y recorriendo múltiples
puntos de la ciudad para finalmente volver al punto inicial. El director logístico del proyecto está interesado en
realizar mejoras a la ruta actual con el fin de reducir la distancia total recorrida, y por tanto, el combustible
gastado durante la totalidad de la entrega. Para esto, a usted le han solicitado que realice la siguiente
implementación:

Requerimiento.
Escriba una función (ruteo) que reciba cómo parámetros: Un diccionario(distancias) en el cuál para una llave (i,  j)
el  valor  correspondiente  es  la distancia que  hay entre  el lugar i y  el  lugar j.Y una  lista (ruta_inicial)
la cual contiene el  orden  de  visita  de  los distintos puntos  de  la  ciudad según la  ruta actual. Con  esta
información,  realice  el procedimiento que se enuncia a continuación: En cada iteración usted debe evaluar todos
los posibles intercambios de dos paradas en la ruta para finalmente actualizarla con aquel intercambio que genere la
mayor reducción en la distancia total recorrida. Una vez actualizada la ruta,  debe pasar  a  la siguiente iteración
y repetir  completamente  este  proceso  hasta  que  no  se  encuentren  mejoras adicionales (criterio de  parada).
Entonces,  retorne  un  diccionario  con  las  llaves “ruta” y “distancia” dónde, el  valor correspondiente a la
primera llave sea una cadena que codifique la ruta final encontrada con las paradas separadas por guiones y el valor
correspondiente a la segunda llave seaun entero con la distancia total asociada a la ruta reportada.

Note qué
Debido a la configuración vial, la distancia que se debe recorrer para ir en automóvil de un lugar i a un lugar j
no necesariamente será la misma distancia que se debe recorrer para ir del lugar j al lugar i (ida y vuelta no son
equivalentes). Independientemente de esto, la ruta siempre debe iniciar y terminar el depósito.

Ejemplo  comparaciones.
Suponga  que  para  una  iteración cualquiera usted cuenta con  la  ruta [‘H’,’A’,’B’,’C’,’D’,’H’]. Entonces, todas las
posibles parejas de intercambio que se deberian evaluar son las siguientes: (‘A’, ‘B’); (‘A’,’C’); (‘A’;’D’);
(‘B’, ‘C’); (‘B’, ‘D’), (‘C’, ‘D’). Si por ejemplo, usted encontrase que entre estos, el mejor intercambio corresponde a
la pareja (‘B’, ‘D’), la ruta actualizada para la siguiente iteración deberá ser[‘H’, ‘A’, ‘D’, ‘C’, ‘B’, ‘H’] y deberá
nuevamente encontrar y evaluar todas las posibles parejaslas cuales en este caso serían: (‘A’, ‘D’); (‘A’, ‘C’);
(‘A’, ‘B’); (‘D’, ‘C’); (‘D’, ‘B’); (‘C’, ‘B’). Pensar en el orden de recorrido o enunciación de las parejas de este
ejemplo le puede ayudar a identificar cómo plantear las comparaciones de forma genérica en su código.

Validación.
Para  este  reto,  usted  debe verificar  que todas  las  distancias del  diccionario  que  entra  por  parámetro  sean
positivas  ó  cero  en  el  caso  de las  distancias  entre  dos  lugares  iguales.  En  cualquier  otro  caso retorne
una cadena reportando el siguiente mensaje de error “Por favor revisarlos datos de entrada".

Output:

Var 1:
{'ruta': 'H-A-F-B-D-C-E-H', 'distancia': 458}
Var 2:
{'ruta': 'H-D-A-B-C-E-H', 'distancia': 393}
"""

from Constantes import constantes_ciclo_1 as cts


def validar_matriz(datos: dict) -> bool:
    for punto_inicial, punto_objetivo in datos:
        if not isinstance(datos[(punto_inicial, punto_objetivo)], int):
            return False
        if datos[(punto_inicial, punto_objetivo)] < 0:
            return False
        if punto_inicial == punto_objetivo and datos[(punto_inicial, punto_objetivo)] != 0:
            return False
    return True


def calcular_costo_por_ruta(pesos: dict, ruta: list) -> int:
    costo_de_la_ruta = 0
    for item in range(0, len(ruta) - 1):
        costo_de_la_ruta += pesos[(ruta[item], ruta[item + 1])]
    return costo_de_la_ruta


def intercambio_de_posiciones(posicion_inicio: str, posicion_destino: str, ruta_a_generar: list) -> list:
    a = ruta_a_generar.index(posicion_inicio)
    b = ruta_a_generar.index(posicion_destino)
    ruta_a_generar[a] = posicion_destino
    ruta_a_generar[b] = posicion_inicio
    return ruta_a_generar


def ruteo(distancias: dict, ruta_inicial: list) -> dict:
    mejor_ruta = {'ruta': '', 'distancia': 0}
    ruta_generada = ruta_inicial.copy()
    mejoras_en_la_ruta = False

    if not validar_matriz(distancias):
        return "Por favor revisar los datos de entrada."

    menor_costo_conocido = calcular_costo_por_ruta(distancias, ruta_inicial)

    while not mejoras_en_la_ruta:
        for posicion_actual in range(1, len(ruta_inicial) - 2):
            for siguiente_posicion in ruta_generada[posicion_actual + 1: -1]:

                posible_mejor_ruta = intercambio_de_posiciones(ruta_generada[posicion_actual], siguiente_posicion,
                                                               ruta_generada.copy())
                costo_de_la_posible_mejor_ruta = calcular_costo_por_ruta(distancias, posible_mejor_ruta)

                if menor_costo_conocido > costo_de_la_posible_mejor_ruta:
                    menor_costo_conocido = costo_de_la_posible_mejor_ruta
                    ruta_a_evaluar = posible_mejor_ruta.copy()
                    mejoras_en_la_ruta = True
        if mejoras_en_la_ruta:
            ruta_generada = ruta_a_evaluar.copy()
            mejoras_en_la_ruta = False
        else:
            mejoras_en_la_ruta = True

    mejor_ruta['ruta'] = "-".join(ruta_generada)
    mejor_ruta['distancia'] = calcular_costo_por_ruta(distancias, ruta_generada)

    return mejor_ruta


print(ruteo(cts.reto_3_test_1, ['H', 'A', 'B', 'C', 'D', 'E', 'F', 'H']))
print(ruteo(cts.reto_3_test_2, ['H', 'B', 'E', 'A', 'C', 'D', 'H']))

"""
PROBLEMA #0
En un famoso laboratorio químico se realizan una serie de experimentos
para los que se requiere analizar, en tiempo real, un conjunto de
reacciones. No obstante, la naturaleza volátil de algunas de estas
hace que, una vez dadas, se cuente solamente con un corto intervalo de
tiempo durante el cual es posible extraer información util antes de que
esta se disipe.Debido a esto, es indispensable qué, independientemente
de cuando inicie cada una de las reacciones individuales, todas deben
finalizar al mismo tiempo. Afortunadamente, el laboratorio conoce una
aproximacion muy precisa de la duración de cada una de sus reacciones.

Considerando el contexto anterior, el laboratorio le ha solicitado que
diseñe una solución informatica qué les facilite realizar la programaciom
de sus experimentos. Para esto:

REQUERIMIENTOS
Escriba una función que reciba como parametros: el código alfanumerico
que identifica una reaccion, asi como la hora y minuto en la que esta
debe finaizar, ademas de la duracion esperada de la misma (en minutos,
horas y segundos) y retorne un mensaje, indicandole a los tecincos en
que momento deben iniciar la reaccion para que este lista exactamente
cuando es requerida

Note que: El laboratorio cierra a la media noche (23:59) y todos los
experimentos se realizan antes del cierre, a partir de reacciones que
iniciaron el mismo dia operacional

Las especificaciones tecnicas del requerimiento se anuncian a
continuacion

#TEST

Nombre:             Tiop    Descripcion
codigo              str     Codigo alfanumerico que identifica la reaccion
hora_terminacion    str     Hora requerida para finalizacion de la reaccion
minuto_terminacion  int     Minuto requerido para la finalizacion de la reaccion
duracion_horas      int     Estimacion de las horas que dura la reaccion
duracion_minutos    int     Estimacion de los minutos que dura la reaccion
duracion_segundos   int     Estimacion de los segundos que dura la reaccion

#OUTPUT

Tipo de retorno     Descripcion
str                 Texto con la siguiente estructura: "La reacción {código} debe
                    iniciarse a las {hh} horas, {mm} minutos con {ss} segundos para
                    que esté lista en en el momento requerido"

"""


def inicio_reaccion(codigo: str, hora_terminacion: int, minuto_terminacion: int, duracion_horas: int, duracion_minutos: int, duracion_segundos: int) -> str:
    seg_reaccion = duracion_horas * 60 * 60 + duracion_minutos * 60 + duracion_segundos
    seg_terminacion = hora_terminacion * 60 * 60 + minuto_terminacion * 60
    seg_inicio = seg_terminacion - seg_reaccion
    hora_inicio = seg_inicio // 3600
    minuto_inicio = (seg_inicio - hora_inicio * 3600) // 60
    seg_iniciar = seg_inicio - hora_inicio * 3600 - minuto_inicio * 60

    return "La reacción {0} debe iniciarse a las {1} horas, {2} minutos con {3} segundos para que esté lista en el momento requerido".format(codigo, hora_inicio, minuto_inicio, seg_iniciar)


print(inicio_reaccion("HHAA01", 16, 30, 4, 11, 23))
print(inicio_reaccion("IQ200", 16, 30, 7, 24, 58))

# ChallengeMidas
# Anotaciones
No existen separaciones de capas
No se usan interfaces. Estas nos ayudan a entender qué hace el programa
Excepciones muy genéricas. Podemos crear excepciones más específicas
No se maneja bien la conexion a la base de datos. No se cierran las conexiones ni se informa de errores al conectar
Se definen variables que no se usan
Poca claridad sobre lo que se guarda en la BD
Se hace un trim del mensaje pero no se asigna a una variable
Se hace un trim de un nulo y esto arroja una excepcion
Valida si el size es = 0, pero existe un metodo .isEmpty que se encarga de ello
Crea un File y luego valida si existe. Por otro no checkea si existe la propiedad o captura el error en caso que no exista
En 3 if hace lo mismo. Se puede crear un método con menos código
Lo que contatena no lo usa, luego loggea solo el mensaje
Define en el constructor de la clase, lo mismo que pasa por parametros en el método
El metodo no sigue las convenciones de nomenclatura (LogMessage)
Mal uso de variables estaticas, no es correcto asignar las mismas mediante constructores
Demasiado parametros en un método
Variables inentendibles, muy cortas, hay que seguir el codigo para adivinar que significan o para que se usan

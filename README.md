<p align="center"><img src="https://i.imgur.com/UFKokoo.png"></p>

---



# Facultad de Humanidades

Repositorio para proyecto integrador de Mil Programadores



## Pantalla de Bienvenida

---

![](https://i.imgur.com/9q0npHA.jpeg)

## Características

----

- Para el Estudiante:
  
  - Registro de nuevo usuario con contraseña
  
  - Registro de nuevo alumno
  
  - Inscripción a carrera
  
  - Inscripción a materia de las carreras
  
  - Creación de certificado de Alumno Regular y Estado Curricular en formato `pdf`
  
  - Baja de inscripción a `Mesa de Examen`
  
  - Ver el Historial Académico

- Para el Administrador:
  
  - Crear una carrera
  
  - Crera una materia asociada a la carrera
  
  - Crear mesas de examen con una fecha establecida
  
  - Generar el listado de resultados de exámenes
  
  - 

## Problemática a tratar

La Decana de la **Facultad** se comunicó con nuestro equipo para comentarnos las necesidades actuales de la unidad académica. Su principal interés es actualizar el sistema para que los alumnos puedan autogestionar sus trámites de manera eficaz y rápida.



## Instalación

----

1. Clonar el repositorio desde `VS Code` o `Eclipse`

`git clone git@github.com:milProgramadoresSaltenos/1c_mp_CV2_Grupo_4.git`

2. En el directorio raíz del proyecto, crear el archivo `JBDCConnection.properties` con el siguiente formato:

```properties
db.driver.class=com.mysql.jdbc.Driver
db.conn.url=jdbc:mysql://localhost/universidad
db.username=
db.password=
```

3. Colocar el usuario y contraseña sin comillas en las líneas correspondientes

4. Restaurar la copia de seguridad de la base de datos con el archivo `base-universidad.sql`. El backup se realizó con DBeaver.

5. El sistema está listo para su uso.



## Diseño de la Base de Datos

![](https://i.imgur.com/tXaRJ4x.png)

### 

| Nombre de tabla   | Función                                                                                                                                                                                                     |
| ----------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Usuarios          | Contiene el correo electrónico del usuario, y si este es un administrador. El correo se usa como identificador para loguear a la cuenta ya existente                                                        |
| Credenciales      | Almacena las contraseñas encriptadas con el algoritmo `Scrypt`. Estas se referencian a la cuenta de usuario correspondiente                                                                                 |
| Alumnos           | Contiene los detalles personales, como ser, nombre, apellido y libreta universitaria. Está vinculada a la tabla Usuarios con su id como referencia                                                          |
| Materias          | Contiene los nombres de las materias existentes en la facultad                                                                                                                                              |
| Carreras          | Contiene los nombres de las carreras existentes en la facultad                                                                                                                                              |
| detallesMaterias  | Contiene la inscripción de un alumno a una materia con su id como clave foránea                                                                                                                             |
| alumnosXcarreras  | Contiene la inscripción de un alumno a una carrera con su id como clave foránea                                                                                                                             |
| materiasXcarreras | Guarda las materias que corresponden a una carrera usando los ids de ambas tablas para representar la relación                                                                                              |
| mesas_examen      | Contiene el id de la materia de carrera y la fecha en que se rinde esta mesa                                                                                                                                |
| examenes          | Contiene el id de la mesa de examen, el id del alumno, y la nota a registrar en el examen rendido. Si el valor de la nota es 0 y pasó la fecha de la mesa, esto se considera como un ausente para el alumno |

## Mapa de la Aplicación

### Usuario Administrador

#### Inicio sesión

![](https://i.imgur.com/S1QvN3N.png)

Se comprueba que el correo tenga formato válido, y los campos no estén vacíos. La información se trae de las tablas Credenciales y Usuario.

#### Pantalla Inicial de Administrador

![](https://i.imgur.com/pSMq9Yp.png)

#### Pantalla Carreras

![](https://i.imgur.com/OzvGE6O.png)

Aquí aparecen todas las carreras cargadas en la base de datos.

##### Crear Nueva carrera

![](https://i.imgur.com/sH793s1.png)

Se solicita un string para crear una carrera.

#### Pantalla de Detalles para una Carrera

![](https://i.imgur.com/sKWhgGT.png)  

Se muestran todas las materias contenidas en la carrera seleccionada

##### Pantalla para Crear Nueva Materia

![](https://i.imgur.com/ZDgPJRL.png)

Se solicita un string para crear una materia.

##### Pantalla para Cargar una Materia Existente

![](https://i.imgur.com/6d1EfrP.png)

Se muestran todas las materias existentes en la base de datos para incluir en la carrera seleccionada.

#### Pantalla Historial Académico

![](https://i.imgur.com/ZEhoNVN.png)

Se muestran los resultados de los exámenes de todos los alumnos del sistema.

#### Pantalla de Mesas de Examen

![](https://i.imgur.com/pmhS7KR.png)

Se muestran las mesas creadas para la carrera seleccionada en el menú superior.

#### Pantalla para Crear una Mesa de Examen

![](https://i.imgur.com/N03yoc3.png)

Selecciono la carrera y la materia para la mesa a crear. Se debe introducir una fecha válida con el formato mostrado en el campo precargado.

----

### Usuario Alumno

#### Pantalla de Registro de Nuevo Usuario

![](https://i.imgur.com/hGFtKMp.png)

Se comprueba que el correo no exista, y que se repita bien la contraseña.

#### Pantalla de Registro de Nuevo Alumno

![](https://i.imgur.com/RzxdIaQ.png)

La Libreta Universitaria se genera automáticamente desde la última registrada en la base de datos. Si el usuario cancela este paso, se procede a eliminar la cuenta de usuario del paso anterio.



#### Pantalla de Inicio del Alumno

![](https://i.imgur.com/Rm26gRP.png)

Se toma el nombre del alumno de la base, la fecha es una constante en la pantalla MainWindow para simular el paso de los días y controlar el flujo de los exámenes.

#### Pantalla de Carreras de Alumno

![](https://i.imgur.com/qc89l9x.png)

Aquí aparecen las carreras a las que está inscripto el alumno.



#### Pantalla de Inscripción a una Carrera

![](https://i.imgur.com/63uBja2.png)

Al seleccionar una carrera, y apretar el botón Inscribir, se agrega la inscripción de este alumno a la base de datos.

#### Pantalla de Carreras actualizada

![](https://i.imgur.com/MWJxqfL.png)

Al aceptar el mensaje anterior, la tabla se vuelve a cargar con las carreras inscriptas.

#### Pantalla de Inscripción a Materia

![](https://i.imgur.com/LJsuT17.png)

Aquí nos inscribimos a la materia de la carrera seleccionada.

#### Pantalla de Exámenes de Alumno

![](https://i.imgur.com/FayupYq.png)

En esta pantalla el alumno ve las mesas de exámenes compatibles con sus materias y carreras inscriptas.

#### Pantalla de Inscripción a Examen con Éxito

#### ![](https://i.imgur.com/cF8lQgn.png)

Una vez inscripto, se agrega una fila a la tabla de exámenes.

#### Pantalla Mis Exámenes de Alumno

![](https://i.imgur.com/q14uyKD.png)

En esta pantalla el alumno ve sus exámenes inscriptos y los puede rendir o cancelar.

#### Pantalla de Reportes del Alumno

![](https://i.imgur.com/Rat8ydB.png)

Aquí se puede ver sus notas de exámenes pasados, así como crear archivos con estado curricular y constancia de alumno regular.

#### Pantalla de Constancia de Alumno Regular

![](https://i.imgur.com/PeDegiR.png)

Aquí el alumno ingresa a quién desea presentar el certificado, y de una de las carreras en las que está inscripto. El certificado cuenta con sus datos de alumno así como la fecha del día actual. Al presionar el botón Crear, se le solicita escribir un nombre de archivo como formato `nombre.pdf` y allí se guardará el mismo.

#### Pantalla para Selección de Ubicación y Nombre de Archivo

![](https://i.imgur.com/RLYdSvF.png)

#### Pantalla de Confirmación de Creación del Certificado

![](https://i.imgur.com/BknFKof.png)

#### Pantalla de Confirmación de Estado Curricular

![](https://i.imgur.com/KvCtP06.png)

#### Ejemplos de Archivo de Salida

##### Constancia de Alumno Regular

![](https://i.imgur.com/O5hUFz6.png)

##### Certificado de Estado Curricular

![](https://i.imgur.com/O2k2TSP.png)













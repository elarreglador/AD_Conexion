# Descripción general

Este programa permite la conexión a bases de datos de tipo SQLite y HSQLDB. Ofrece un menú interactivo para que el usuario seleccione la base de datos a la que desea conectarse y, a continuación, muestra información sobre la misma y sus tablas.

## Requisitos previos

Tener instalado Java en su sistema.
Tener los drivers JDBC para SQLite y/o HSQLDB configurados en su proyecto.
Ejecución

Compile el código fuente utilizando un compilador Java.
Ejecute el archivo compilado (java Main).

## Uso

El programa presenta un menú principal que permite al usuario elegir entre:

Conectarse a una base de datos SQLite.
Conectarse a una base de datos HSQLDB.
Salir de la aplicación.

Una vez seleccionada la base de datos, el programa mostrará información general sobre la misma (nombre, driver, URL, usuario) y posteriormente listará sus tablas con detalles de cada columna (nombre, tipo de dato, permite nulos).

## Funciones del código

main: punto de entrada del programa, gestiona el menú principal y la interacción con el usuario.

conectaBD(String tipoBD, File archivo): establece la conexión a la base de datos según el tipo y la ubicación del archivo.

infoBD(Connection con): obtiene y muestra información general sobre la base de datos conectada.

infoTablas(Connection con): recupera y muestra información de las tablas de la base de datos conectada, incluyendo detalles de cada columna.

creaTablas(Connection con, String nombreTabla): Función comentada - Crea una tabla en la base de datos conectada, esta comentada pero se puede descomentar si usamos una BD nueva

## Observaciones

El código verifica el tipo de base de datos a la que se intenta conectar (SQLite o HSQLDB) y adapta la consulta para crear tablas en consecuencia.
La función creaTablas se encuentra actualmente deshabilitada en el código fuente.

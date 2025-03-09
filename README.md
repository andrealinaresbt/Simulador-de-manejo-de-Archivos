# Simulador-de-manejo-de-Archivos
Proyecto 2 SO
Proyecto 2: Simulador Virtual de Sistema de Archivos con Gestión de Permisos y Asignación de Bloques
Planteamiento del Problema
El objetivo de este proyecto es que los estudiantes desarrollen un simulador de sistema de archivos, en el que puedan comprender y aplicar conceptos fundamentales como la gestión de archivos y directorios, la asignación de bloques de almacenamiento, la administración de permisos y la fragmentación del espacio en disco.
Para ello, los estudiantes deberán implementar un sistema de archivos simulado en Java utilizando NetBeans, con una interfaz gráfica que represente visualmente la estructura jerárquica de directorios y archivos mediante un JTree, así como la distribución de bloques en una Simulación de un Disco (SD), y una tabla de asignación de archivos.
El sistema debe operar en dos modos de usuario:
Modo Administrador: Permite realizar todas las operaciones, incluyendo crear, modificar y eliminar archivos y directorios y visualizar información completa del SD.


Modo Usuario: Restringe las acciones a solo lectura, impidiendo modificar archivos o la estructura del sistema de archivos.
Los archivos creados deberán tener un tamaño en bloques, los cuales serán asignados utilizando el método de asignación encadenada, en donde cada archivo se representa como una lista enlazada de bloques en el SD.
Además, el sistema deberá actualizarse en tiempo real cada vez que se realice una operación CRUD (Crear, Leer, Actualizar, Eliminar), reflejando los cambios en la estructura de directorios, en el estado del disco SD y en la tabla de asignación de archivos.

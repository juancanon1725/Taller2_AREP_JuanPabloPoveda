# Aplicaciones Distribuidas (AREP Taller 1)

Juan Pablo Poveda Cañón

Este proyecto es a una aplicación de red que cuenta con un gestor de solicitudes HTTP y un almacenamiento en caché para guardar respuestas previas.

### Funcionalidad
* Desde cualquier browser, digitamos la direccion http://localhost:35000, y nos llevará a nuestro buscardor de peliculas, donde podremos encontrar a información de la pelicula.
* El servidor comienza escuchando en el puerto 35000 para las conexiones entrantes de los clientes.
  
[![Sin-t-tulo.png](https://i.postimg.cc/LszN498c/Sin-t-tulo.png)](https://postimg.cc/rdwSQLWj)

##  Patrones de Diseño

* Modelo Cliente-Servidor: Esta arquitectura establece la separación de responsabilidades entre el cliente y el servidor. El cliente envía solicitudes al servidor, y este último las procesa y devuelve las respuestas correspondientes.

* Patrón Singleton: Este patrón se implementa en la clase Cache para asegurar que solo haya una instancia de la caché en toda la aplicación. Esto simplifica el almacenamiento de datos y permite que diferentes partes del código accedan a la misma instancia de caché.

## Buenas Prácticas
* Pruebas Unitarias: Garantizan que el código funcione correctamente y ayuda a detectar errores.
  
[![Sin-t-tulo2.png](https://i.postimg.cc/9fHZXs0K/Sin-t-tulo2.png)](https://postimg.cc/pmkpqGDJ)

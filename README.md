# AREP TALLER 2

Juan Pablo Poveda Cañón

## Arquitectura

El uso de la arquitectura cliente-servidor implica que la aplicación está estructurada de manera que hay dos componentes principales: el cliente y el servidor. 

#### Como cliente:
* Los clientes pueden acceder a diferentes rutas en el servidor web mediante URLs, como "/index.html" o "/script.js".
* También pueden solicitar recursos específicos, como imágenes ("/imagenJulia.jpg") o enviar solicitudes personalizadas ("/title?name=...") para obtener información adicional.

#### Como servidor:
* La clase HttpServer escucha en un puerto específico (en este caso, el puerto 35000) y está configurada para aceptar conexiones entrantes de los clientes.
* Cuando una conexión entrante es aceptada, el servidor crea flujos de entrada y salida para establecer la comunicación con el cliente.
* La función principal del servidor es gestionar las solicitudes, procesarlas y enviar las respuestas correspondientes al cliente, cumpliendo así con el modelo cliente-servidor.

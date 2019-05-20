# Albums Application

API REST Application made with Java 8 & Spring Boot

### Instrucciones de uso
Para lenvantar la aplicación simplemente se debe clonar el proyecto de manera local, abrir la consola en el directorio del proyecto y ejecutar el siguiente comando:

`mvn spring-boot:run`

La aplicación se levantará en el puerto 8080 de manera que la ruta inicial será: **localhost:8080**
### Persistencia

El modelo de datos que maneja la aplicación es pequeño y sencillo. En un modelo relacional el modelo de la aplicación constaría en una única tabla con 4 columnas (idAlbum, idUsuario, permisoEscritura, permisoLectura). Casi cualquier motor hubiese sido de utilidad para cumplir con los fines prácticos del ejercicio. 

El almacenamiento de los datos actualmente se realiza en una base en memoria H2 a la cual se accede a través de JPA.
Esto se envolverá con una capa Spring MVC para acceder de forma remota.

Decidí utilizar esta base de datos en memoria con el fin de que el testeo de la aplicación se pudiese hacer de forma sencilla sin necesidad de acomplejizar la configuración del entorno de prueba. 

La solución de repositorio de Spring Data hace posible eludir detalles específicos del almacén de datos y, en cambio, resolver la mayoría de los problemas utilizando terminología específica del dominio.

Además, si en un futuro se desease incoporar una base de datos para persistir la informacion sería sencillo realizar la integración ya que solo se debe modificar la clase SharedAlbumDataRepository para que las querys se realicen sobre la nueva BBDD incorporada. 

### End Points
Todas las respuestas se encuentran en formato JSON.
##### Metodos GET
- Obtener todos los usuarios
    - `localhost:8080/users`
- Obtener todos los albumes
    - `localhost:8080/albums`
- Obtener albumes de un usuario especifico
    - `localhost:8080/albums?userId=<userIdValue>`
- Obtener todas las fotos
    - `localhost:8080/photos`
- Obtener todos los comentarios
    - `localhost:8080/comments`
- Obtener los comentarios que realizó un usuario especifico
    - `localhost:8080/comments?userId=<userIdValue>`
    - En este punto me gustaría aclarar que no detecté de qué manera se relaciona un comentario con un usuario en los datos obtenidos de la aplicación externa https://jsonplaceholder.typicode.com/.
    Un comentario posee el atributo 'email', al igual que un usuario. Por lo tanto, el filtro que apliqué es que el email del comentario coincida con el mail del usuario recibido por id en el request.
- Obtener los comentarios filtrando por el campo 'name' del comentario
    - `localhost:8080/comments?name=<nameValue>`
- Obtener los comentarios de un usuario especifico y filtrando por el campo 'name'
    - `localhost:8080/comments?userId=<userIdValue>&name=<nameValue>`
- Obtener todos los albumes que son compartidos 
    - `localhost:8080/sharedAlbums`
- Obtener un album compartido especifico
    - `localhost:8080/sharedAlbums/<albumId>`
- Obtener los usuarios que poseen un permiso especifico sobre un album compartido determinado
    - `localhost:8080/sharedAlbums/<albumId>/<permiso>`
    - Los valores validos para el permiso son: read/write
- Obtener toda la informacion relacionada a albumes compartidos (idAlbum, idUsuario, permisos)
    - `localhost:8080/sharedAlbumsData`
    
##### Metodos POST
Tanto para el alta como el update de registros de albumes compartidos se maneja el mismo formato:

`{ "albumId": <albumIdValue>,
  "userId": <userIdValue>,
  "read": true/false,
  "write": true/false
}`

Es decir, las siguientes solicitudes necesitan ese objeto en el body del request para poder ser llevadas a cabo correctamente.
- Guardar un nuevo registro de album compartido
    - `localhost:8080/sharedAlbums/save`
    - El body del request debe contener un objeto que debe respertar el formato indicado anteriormente.
- Guardar varios registros para un album compartido
    - `localhost:8080/sharedAlbums/saveList`
    - El body del request debe contener una lista de objetos que deben respertar el formato indicado anteriormente.
    
A través de los dos métodos POST es posible modificar los permisos de un usuario. Simplemente se debe enviar el objeto indicando el id del usuario, el id del album y sus nuevos permisos.

### Aclaraciones
- Para obtener un album especifico por id, el usuario deberá hacer un get a mi aplicación indicando el id del album. En un principio la app tomaba ese id y realizaba una consulta a la API externa usando el id como parametro. Sin embargo, cuando no existía ningún album con el id indicado, la aplicación externa respondía con un estado de error 404. Por lo tanto, para poder controlar y manipular los errores, opté por obtener la lista entera de albumes provenientes de la API externa y buscar el registro que coincida con el id consultado. Ya que en caso de no encontrar ningún registro, pueda retornar una excepción controlada. 
- La misma decisión anterior aplica para buscar usuarios.
- Los controller presentan interfaces por si fuese necesario agregar una nueva implementación de los request. Solo se debe agregar una clase que implemente la interfaz. Spring posee herramientas que permiten inyectar una implementación específica de una interfaz. Además, usar la interfaz para definir los métodos http hace que los endpoints sean más legibles y entendibles.  
- Al querer dar de alta un nuevo registro para manejar los permisos de un usuario sobre un album compartido se realizan tres validaciones:
	- 1) Que el objeto este correctamente definido. Es decir, ninguno de sus atributos puede ser null.
	- 2) Que exista el album indicado.
	- 3) Que exista el usuario indicado.
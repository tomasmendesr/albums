https://spring.io/guides/tutorials/rest/
https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories
https://reflectoring.io/spring-boot-data-jpa-test/

# Albums Application

API REST Application made with Java 8 & Spring Boot

### Instrucciones de uso
Para lenvantar la aplicación simplemente se debe clonar el proyecto de manera local, abrir la consola en el directorio del proyecto y ejecutar el siguiente comando:

`mvn spring-boot:run`

La aplicación se levantará en el puerto 8080 de manera que la ruta inicial será: **localhost:8080**
### Persistencia

El modelo de datos que maneja la aplicación es pequeño y sencillo, por lo que la elección de una base de datos no es una decisión crítica. Casi cualquier motor hubiese sido de utilidad para cumplir con los fines prácticos del ejercicio. De haber tenido que utilizar alguna hubiese optado por MongoDB, ya que me permite escalar facilmente y además modificar mi modelo de manera dinámica sin necesidad de realizar modificaciones. 

El almacenamiento de los datos se realiza en una base en memoria H2 a la cual se accede a través de JPA.
Esto se envolverá con una capa Spring MVC para acceder de forma remota.
Se decidió utilizar esta base de datos en memoria con el fin de que el testeo de la aplicación se pudiese hacer de forma sencilla sin necesidad de acomplejizar la configuración del entorno de prueba.

La solución de repositorio de Spring Data hace posible eludir detalles específicos del almacén de datos y, en cambio, resolver la mayoría de los problemas utilizando terminología específica del dominio.

### End Points
Todas las respuestas se encuentran en formato JSON.
##### Metodos GET
- Obtener todos usuarios
    - `localhost:8080/users`
- Obtener todos albumes
    - `localhost:8080/albums`
- Obtener albumes de un usuario especifico
    - `localhost:8080/albums?userId=<userIdValue>`
- Obtener todas las fotos
    - `localhost:8080/photos`
- Obtener todos los comentarios
    - `localhost:8080/comments`
- Obtener los comentarios de un usuario especifico
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
    
A través de los dos métodos POST es posible modificar permisos de un usuario. Simplemente se debe enviar el objeto indicando el id del usuario, el id del album y sus nuevos permisos.

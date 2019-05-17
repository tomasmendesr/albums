# Albums

API REST Application made with Java 8 & Spring Boot

# Persistencia

El modelo de datos que maneja la aplicación es pequeño y sencillo, por lo que la elección de una base de datos no es una decisión crítica. Casi cualquier motor hubiese sido de utilidad para cumplir con los fines prácticos del ejercicio. De haber tenido que utilizar alguna hubiese optado por MongoDB, ya que me permite escalar facilmente y además modificar mi modelo de manera dinámica sin necesidad de realizar modificaciones. 

El almacenamiento de los datos se realiza en una base en memoria H2 a la cual se accede a través de JPA.
Esto se envolverá con una capa Spring MVC para acceder de forma remota.
Se decidió utilizar esta base de datos en memoria con el fin de que el testeo de la aplicación se pudiese hacer de forma sencilla sin necesidad de acomplejizar la configuración del entorno de prueba.

La solución de repositorio de Spring Data hace posible eludir detalles específicos del almacén de datos y, en cambio, resolver la mayoría de los problemas utilizando terminología específica del dominio.


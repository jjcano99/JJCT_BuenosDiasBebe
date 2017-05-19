# JJCT_BuenosDiasBebe

Esta práctica ilustra cómo enviar objetos mediante POST con formato JSON al servidor .Net/C#, que contestará remitiendo 
otro objeto codificado en JSON hacia la app.

Básicamente, lo que se hace es recibir un objeto de tipo "Despertar" desde el servidor, que incluye un texto, el nombre de una
imagen jpg y el nombre de una melodía en mp3. Lo que se envía al servidor es una Lista de objetos Despertar que recoge la
historia de los recibidos, para que el servidor lo procese y conteste con un Despertar no repetido.

El envío en formato POST y posterior recepción en la parte de la app del móvil se ve en el fichero JAVA "PeticionDespertar.java"

La parte del servidor se ve en el archivo c# "PeticionesWeb.asmx.cs"
